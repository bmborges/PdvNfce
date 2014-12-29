/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.util;

import com.sun.jna.Native;
import java.util.Date;

/**
 *
 * @author root
 */
public class TesteImpressaoBematech {
    
    
    public static void main(String[] args) {
        int iRetorno;
        BematechNFiscal cupom = BematechNFiscal.Instance;
        iRetorno = cupom.ConfiguraModeloImpressora(5);
        iRetorno = cupom.IniciaPorta("USB");
//        iRetorno = cupom.Le_Status();
//        iRetorno = cupom.VerificaPapelPresenter();
//        System.out.println(iRetorno);
//        iRetorno = cupom.ImprimeBitmap("C:\\Documents and Settings\\root\\NfeServices\\report\\Logotipo.bmp", 0);
//        iRetorno = cupom.ImprimeBmpEspecial("C:\\Documents and Settings\\root\\NfeServices\\report\\Logotipo.bmp", 30, 30, 0);
//        iRetorno = cupom.ImprimeBmpEspecial("C:\\Documents and Settings\\root\\NfeServices\\report\\qrcode.bmp", -1, -1, 0);
        
//        iRetorno = cupom.FormataTX(alinhaTexto("AGROVALE"), 3, 0, 0, 1, 1);
//        iRetorno = cupom.BematechTX("\n"+alinhaTexto("AGROVALE"));
        iRetorno = cupom.BematechTX("\n"+alinhaTexto("COOPERATIVA MISTA DO PRODUTORES RURAIS DO VALE DO PARANAIB"));
        iRetorno = cupom.BematechTX("\nCNPJ: 02.233.732/0001-57");  
        iRetorno = cupom.BematechTX("\n"+alinhaTexto("DANFE NFC-e Documento Auxiliar da Nota Fiscal Eletronica"));  
        iRetorno = cupom.BematechTX("\n"+alinhaTexto("Nao Permite aproveitamento de credito de ICMS"));  
//        iRetorno = cupom.BematechTX("\n*************************************");  
//        iRetorno = cupom.BematechTX("\n\nData: " + new Date());  
//        iRetorno = cupom.BematechTX("\t\tHora: " + new Date().getTime() + "\n");  
//        iRetorno = cupom.BematechTX("\nTestando a IMPRESSORA nao fiscal:");  
//        iRetorno = cupom.BematechTX("\nProduto:         Descrição:");  
        iRetorno = cupom.BematechTX("\n\n\n\n\n\n\n\n\n\n\n");
        iRetorno = cupom.AcionaGuilhotina(0);
        
        iRetorno = cupom.FechaPorta();
        
    }
    private static String alinhaTexto(String texto){
        String rTexto = "";
        
        int iTexto = texto.length();
        iTexto = iTexto / 2;
        int iEspaco = 25 - iTexto;
        for (int i = 0; i < iEspaco; i++) {
            rTexto += " ";
        }
        rTexto += texto;
        
        
        return rTexto;
    }
}
