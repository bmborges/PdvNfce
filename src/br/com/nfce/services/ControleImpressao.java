package br.com.nfce.services;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.nfce.dao.vnd.VND_nfpedidoDAO;
import br.com.nfce.dao.vnd.VND_pedvendaDAO;
import br.com.nfce.util.BematechNFiscal;
import br.com.nfce.util.Database;
import br.com.nfce.util.Parametros_ECF;
import br.com.xml.nfe.TNfeProc;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.text.BadLocationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.xml.sax.SAXException;

/**
 *
 * @author supervisor
 */
public class ControleImpressao {
    
    static Timer t = new Timer();
    static TimerTask tt;
    static int tempo = 1000 * 30;
    VND_nfpedidoDAO nfpedido;
    private static PrintService impressora;
    java.sql.Connection conn = null;
    String curDir = System.getProperty("user.home");
    String curSep = System.getProperty("file.separator");
    String caminho = curDir + curSep + "NfeServices"+ curSep +"report"+ curSep;
    
    Parametros_ECF pe;
    
    
    public static void main(String[] args) throws SQLException, Exception {
        ControleImpressao i = new ControleImpressao();
        i.StartTimer();
               
    }
public void StartTimer() throws Exception{
        //main.CarregaJtxa(">>> TimerNfeRecepcao...: " + tempo,Color.BLACK);
        tt = new TimerTask(){
          public void run() {
                try {
                    HashMap pedido_hm = new HashMap();
                    pedido_hm = nfpedido.pesquisa_nfpedido_imp();
                    int cdpedido = Integer.parseInt(pedido_hm.get("cdpedido").toString());
                    if (cdpedido > 0){
                        PesquisaXML(cdpedido,null);
                    }
                } catch (Exception ex) {
                    System.err.println(">>>Timer Controle Impressão não foi executado..." + ex);
                }
          }
       };
       t.schedule(tt, 0, tempo);
    }
    
    public ControleImpressao() throws Exception{
      conn = Database.getConnection();

      if (conn == null) {
         throw new Exception(getClass().getName() + ": null connection passed.");
      }
      this.conn = conn;
      nfpedido = new VND_nfpedidoDAO();
      pe = new Parametros_ECF();
    }


    public void PesquisaXML(int cdpedido, String xml_nfe) throws SQLException, SAXException, JRException, FileNotFoundException, ParserConfigurationException, IOException, Exception{
        
        if (xml_nfe == null){
            String qry = "Select xml_nfe from vnd_nfpedido where cdpedido = ?";

            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setInt(1, cdpedido);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               xml_nfe = rs.getString("xml_nfe");
            }
            rs.close();
            stmt.close();
        }
        ImprimeBematech(xml_nfe,cdpedido);

    }
public void ImprimeBematech(String xml_nfe, int cdpedido) throws JAXBException{

        
        JAXBContext context = JAXBContext.newInstance("br.com.xml.nfe");

	Unmarshaller unmarshaller = context.createUnmarshaller();
        TNfeProc proc = unmarshaller.unmarshal( new StreamSource(new StringReader(xml_nfe)), TNfeProc.class ).getValue();

        int iRetorno;
        String sTexto;
        BematechNFiscal cupom = BematechNFiscal.Instance;
        iRetorno = cupom.ConfiguraModeloImpressora(5);
        iRetorno = cupom.IniciaPorta("USB");
        iRetorno = cupom.AjustaLarguraPapel(80);
        iRetorno = cupom.SelecionaQualidadeImpressao(4);
        
        iRetorno = cupom.FormataTX("\n" + proc.getNFe().getInfNFe().getEmit().getXNome(),2,0,0,0,1);
        
        sTexto = "CNPJ - " + proc.getNFe().getInfNFe().getEmit().getCNPJ();
        sTexto += " IE - " + proc.getNFe().getInfNFe().getEmit().getIE();
        iRetorno = cupom.BematechTX("\n" + centralizaTexto(sTexto));
        
        sTexto = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr();
        sTexto += ", " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro();
        sTexto += " " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro();
        sTexto += " / " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun();
        sTexto += " - " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF();
        
        iRetorno = cupom.FormataTX("\n" + centralizaTexto(sTexto),1,0,0,0,0);
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.FormataTX("\nDANFE NFC-e Documento Auxiliar da Nota Fiscal Eletronica para Consumidor Final",2,0,0,0,1);  
        iRetorno = cupom.FormataTX("\n"+centralizaTexto("Nao Permite aproveitamento de credito de ICMS"),2,0,0,0,1);  
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.BematechTX("\nItem Codigo  Descricao Qtde Un Vlr.Unit Vlr.Total");  
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        for (int i = 0; i < proc.getNFe().getInfNFe().getDet().size(); i++){
            String item = proc.getNFe().getInfNFe().getDet().get(i).getNItem();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getCProd();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getXProd();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getQCom();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getUCom();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getVUnCom();
            item += "  " + proc.getNFe().getInfNFe().getDet().get(i).getProd().getVProd();
            iRetorno = cupom.FormataTX("\n" + item,2,0,0,0,0);
        }
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.BematechTX("\nQtd Total de Itens"+proc.getNFe().getInfNFe().getDet().size());
        iRetorno = cupom.BematechTX("\nSubtotal R$"+proc.getNFe().getInfNFe().getTotal().getICMSTot().getVProd());
        iRetorno = cupom.BematechTX("\nDesconto R$"+proc.getNFe().getInfNFe().getTotal().getICMSTot().getVDesc());
        iRetorno = cupom.BematechTX("\nValor Total R$"+proc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());
        iRetorno = cupom.BematechTX("\nFORMA PAGAMENTO \tVALOR PAGO R$");
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.BematechTX("\nTributos Totais Incidentes");
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        
        
        sTexto = proc.getNFe().getInfNFe().getIde().getDEmi();
        sTexto = sTexto.replace("T", " ");
        String[] sATexto = sTexto.split(" ");
        String[] aData = sATexto[0].split("-");
//        sTexto = aData[2]+"/"+aData[1]+"/"+aData[0]+" "+sATexto[1];        
        sTexto = aData[2]+"/"+aData[1]+"/"+aData[0]; 
        
        String sTextoN = "Numero " + proc.getNFe().getInfNFe().getIde().getNNF();
        sTextoN += " Serie " + proc.getNFe().getInfNFe().getIde().getSerie();
        sTextoN += " " + sTexto;
        
        iRetorno = cupom.BematechTX(centralizaTexto(sTextoN));
        
        iRetorno = cupom.BematechTX("\nConsulte pela Chave de Acesso em http://www.sefaz");
        iRetorno = cupom.FormataTX("\n"+centralizaTexto("CHAVE DE ACESSO"),2,0,0,0,1);
        iRetorno = cupom.BematechTX("\n"+centralizaTexto(proc.getProtNFe().getInfProt().getChNFe()));
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.FormataTX("\n"+centralizaTexto("CONSUMIDOR"),2,0,0,0,1);
        iRetorno = cupom.BematechTX("\nCPF - "+proc.getNFe().getInfNFe().getDest().getCPF());
        iRetorno = cupom.BematechTX(" " + proc.getNFe().getInfNFe().getDest().getXNome());
        iRetorno = cupom.BematechTX("\n--------------------------------------------------");
        iRetorno = cupom.BematechTX("\n"+ centralizaTexto("Consulta via leitor de QR Code"));
        
        
        
        
        iRetorno = cupom.BematechTX("\n" + centralizaTexto("Protocolo de Autorizacao: " + proc.getProtNFe().getInfProt().getNProt()));
        
        sTexto = proc.getProtNFe().getInfProt().getDhRecbto().toString();
        sTexto = sTexto.replace("T", " ");
        sATexto = sTexto.split(" ");
        aData = sATexto[0].split("-");
        sTexto = aData[2]+"/"+aData[1]+"/"+aData[0]+" "+sATexto[1];
        iRetorno = cupom.BematechTX("\n" + centralizaTexto(sTexto));
        
        
        iRetorno = cupom.BematechTX("\n\n\n\n\n\n\n\n\n\n\n");
        iRetorno = cupom.AcionaGuilhotina(0);
        
        iRetorno = cupom.FechaPorta();        
        
        
//          VND_pedvendaDAO pedido_dao = new VND_pedvendaDAO();
//          String idnfe = pedido_dao.select_idnfe(cdpedido);
          
//          EnviarEmail e = new EnviarEmail(main);
//          e.Enviar(xml_nfe, pdf, idnfe,"env");
    
}
    
public String centralizaTexto(String texto){
    String sRetorno = "";
    int iQtdCaracteres = 23;
    int iQtdEspacos = 0;
    
    iQtdEspacos = iQtdCaracteres - (texto.length() / 2);
    
    for (int i = 0; i < iQtdEspacos; i++) {
        sRetorno += " ";
    }
    
    sRetorno += texto;
    
    return sRetorno;
}
    
}
