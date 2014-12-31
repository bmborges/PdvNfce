/*
 * Cls_TEF_ImpTrasacao.java
 * 
 * Created on 12/09/2007, 09:13:27
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nfce.tef;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @author Cleber
 */
public class Tef_ImpTransacao {
  Tef_PayGo tef = new Tef_PayGo();
  
  private final int PRIMEIRA_VIA = 1;
  private final int SEGUNDA_VIA = 2;

  private final String RELAT_GERENC = "R";  //-- relatorio gerencial
  private final String CUP_N_FISC_VINC_GERENC = "V";  //-- cupom nao fiscal vinculado

  public Tef_ImpTransacao(Tef_PayGo clsTefDedicado) {
    this.tef = clsTefDedicado;
//    this.mainFrame = mf;
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////

  public boolean verifica_Impressora_Ativa() {
    boolean bRetorno = true;

    
    return bRetorno;
  } 
//-- verifica_Impressora_Ativa ------------------------------------------------------------------//

  /**
   *  ver se e necessario + 1 parametro para desativar a axec de verifica_Impressora_Ativa
   */
  private boolean imprimeComprovante(String sTipo, int iVia) {
    //System.out.println("imprimeComprovante....:>>" + sTipo + " Via : " + iVia + "<<");
    boolean bOK = false;
    String sLinha = "";

    boolean bNotaPromissoria = false;
    boolean bRetorno = true;
    td.sTEFRetorno = "0";
    //String sRetorno = "0";
    bOK = mainFrame.cmdEcf.pdv_UsaComprovanteNaoFiscalVinculado("             ");
    if (bOK) {
      bOK = mainFrame.cmdEcf.pdv_UsaComprovanteNaoFiscalVinculado("             ");
    }
    if (bOK) {
      try {
        BufferedReader channel_4 = new BufferedReader(new FileReader(td.sTEFKoneKPath + "TEF.Imp"));

        do {
          sLinha = channel_4.readLine(); //-------------------------------------------

          if (sLinha != null) {
            //-- Usa comprovante nao Fiscal Vinculado --//
            //-- imprime 1a via --//
            //aguarda_Impressora();
            //System.out.println("IMPRESSaO-->>> Usa Comprovante Fiscal" +
            //                   sLinha + "<<<--");
            if (sLinha.trim().equals("NOTA  PROMISSORIA")) {
              bNotaPromissoria = true;
            }

            if (iVia == 1) {
              if (!bNotaPromissoria) {
                bOK = mainFrame.cmdEcf.pdv_UsaComprovanteNaoFiscalVinculado(sLinha);
                if (bOK) {
                  mainFrame.geral_ContLinha++;
                }
              }
            } else {
              bOK = mainFrame.cmdEcf.pdv_UsaComprovanteNaoFiscalVinculado(sLinha);
              if (bOK) {
                mainFrame.geral_ContLinha++;
              }
            }

            if (!bOK) {
              //-- talvez tenha q retirar essa linha
              //bRetorno = verifica_Impressora_Ativa();
              bRetorno = false; //cleber  sem verifica_Impressora_Ativa  retorna false

              sLinha = null;
            }
          }
        } while (sLinha != null);

        channel_4.close();
      //System.out.println("Passei por Aqui>>>>>>>>>>>>>>>>>>>>>>");
      //System.out.println("Passei por Aqui>>>>>>>>>>>>>>>>>>>>>>");
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo \"TEF.Imp\": \n" + e.getMessage(), "Atencao:", JOptionPane.ERROR_MESSAGE);
        bRetorno = false; //-- retorno false para nao mandar fechar o Comprovante.

      }
    } else {
      bRetorno = false;
    }
    return bRetorno;
  } //-- imprimeComprovante -------------------------------------------------------------------------//

  private boolean imprimeComprovanteGerencial(String sTipo, int iVia) {
    boolean bOK    = false;
    boolean bBreak = false;   //-- interromper a leitura do texto pq a impressora deu erro --//
    String sLinha = "";
    boolean bNotaPromissoria = false;

    // Aqui Se for necessario, mandar imprimir estas 2 linhas antes

    bOK = mainFrame.cmdEcf.pdv_ImprimeRelatorioGerencial("            ");
    if (bOK) bOK = mainFrame.cmdEcf.pdv_ImprimeRelatorioGerencial("            ");
    if (bOK) {

      try {

        BufferedReader channel_4 = new BufferedReader(new FileReader(td.
            sTEFKoneKPath + "TEF.Imp"));
        do {
          sLinha = channel_4.readLine(); //-------------------------------------------
          if (sLinha != null) {
            //-- Usa comprovante nao Fiscal Vinculado --//
            //if (mainFrame.geral_sTipoECF.equals("Bematech")) {

            //if (mainFrame.pdvBematech != null) {
                if (sLinha.trim().equals("NOTA  PROMISSORIA"))
                  bNotaPromissoria = true;

                if (iVia == 1) {
                  if (!bNotaPromissoria) {
                    //System.out.println("Imprimi Esta Linha....:>>" + sLinha +  "<<");

                    bOK = mainFrame.cmdEcf.pdv_ImprimeRelatorioGerencial(sLinha);
                  }
                }
                else {
                  //System.out.println("Imprimi Esta Linha....:>>" + sLinha + "<<");
                  bOK = mainFrame.cmdEcf.pdv_ImprimeRelatorioGerencial(sLinha);
                }

                if (!bOK) {
                  //System.out.println("ERRO na Impressao....:>>" + sLinha + "<<");
                  sLinha = null;
                }
             //
          //}
          }
        }
        while (sLinha != null);
        channel_4.close();
      }
      catch (IOException e) {
        JOptionPane.showMessageDialog(null,
                                      "Erro na leitura do arquivo \"TEF.Imp\": \n"
                                      + e.getMessage(), "Atencao:",
                                      JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }
    }
    else bOK =  false;

    return bOK;
  } //-- imprimeComprovanteGerencial ----------------------------------------------------------------//

  /**
   * Procedimento principal da classe
   */

  public void TEFImprimeTransacao(String tipoComprov, String sNomeFormaPgto, String sVr, String sNrCpm) {
  //setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    boolean bContinua = false;
    boolean bOk       = false;

    mainFrame.tipoComprov = tipoComprov;

    String sLinha = "";
    String sRetorno = "0";

    boolean bRetorno = true;

    boolean bOK = false,bRepeat = true;
    boolean bBreak = false;   //-- interromper a leitura do texto pq a impressora deu erro --//

    td.sTEFRetorno = "0";
    if (tipoComprov.equals(CUP_N_FISC_VINC_GERENC)) { // "V" - Cupom Nao Fiscal Vinculado
        td.ksECFformaPagto = "Cartao";
        td.ksECFvalor = "";
        td.ksECFcupomAssociado = "";

        //System.out.println("nome da forma....: " + sNomeFormaPgto + "   valor...: " + sVr);
        mainFrame.cmdEcf.setPoucoPapelJaExibiu(false);  //-- na impressao, nao considerar a exib. de antes

        //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
        //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.  a b e r t u r a  .:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
        //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
        bRetorno = mainFrame.cmdEcf.pdv_AbreComprovanteNaoFiscalVinculado(sNomeFormaPgto, sVr, sNrCpm);
//System.out.println("\n" + bRetorno + " - abertura abertura abertura abertura abertura ");
//System.out.println(bRetorno + " - abertura abertura abertura abertura abertura ");
        
        mainFrame.cmdEcf.setPoucoPapelJaExibiu(false);  //-- na impressao, nao considerar a exib. de antes

        mainFrame.geral_ContLinha = 0;
      do {
	if (bRetorno) {
          //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
          //.:.:.:.:.:.:.:.:.:.:.:.:.  c o m p r o v a n t e  .:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
          //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
	  bRetorno = imprimeComprovante(tipoComprov, PRIMEIRA_VIA);
//System.out.println("\n" + bRetorno + " - comprovante comprovante comprovante comprovante ");
//System.out.println(bRetorno + " - comprovante comprovante comprovante comprovante ");

	  //if (bRetorno) {
	    //bRetorno = imprimeComprovante(tipoComprov, SEGUNDA_VIA);

	    if (bRetorno) {
	      //-- fechar comprovante nao Fiscal Vinculado --//

              //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
              //.:.:.:.:.:.:.:.:.:.:.:.:.:.  f e c h a m e n t o  .:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
              //.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.\\
              bRetorno = mainFrame.cmdEcf.pdv_fechaRelatorioGerencial();
//System.out.println("\n" + bRetorno + " - fechamento fechamento fechamento fechamento fechamento ");
//System.out.println(bRetorno + " - fechamento fechamento fechamento fechamento fechamento ");
              
              mainFrame.cmdEcf.setPoucoPapelJaExibiu(false);  //-- na impressao, nao considerar a exib. de antes
          }
	  //}
	}
        
        //dlldldldldldldl
	if (bRetorno) {
	  bRepeat = false;
	} else {
	  if (mainFrame.tipoComprov.equals(CUP_N_FISC_VINC_GERENC)) {  //--"V"
	    bRepeat = true;
	    bRetorno = true;
	  } else {
	    bRepeat = false;
	    td.sTEFRetorno = "1";
	  }
	}
      } while (bRepeat);
	
      // Se algum dos retornos foi diferente de zero. Imprimir Gerencial.
      if (!bRetorno) {
        td.sTEFRetorno = "1";
        //Donizeth - 13/01/2005 Nao partir direto pro gerencial. Evitar este transtorno
        //tipoComprov = "R";
        if (mainFrame.tipoComprov.equals(RELAT_GERENC)) { //--"R"
          bRetorno = mainFrame.cmdEcf.pdv_fechaRelatorioGerencial();
        }
      }
    } else {
      td.sTEFRetorno = "1";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    tipoComprov = mainFrame.tipoComprov ;

    if (tipoComprov.equals(RELAT_GERENC)) {  // "R" -Relatorio gerencial
      do {
	td.sTEFRetorno = "0";
	bOk = imprimeComprovanteGerencial(tipoComprov, PRIMEIRA_VIA); 
	//if (bOk) {
	  //bOk = imprimeComprovanteGerencial(sTipo, SEGUNDA_VIA); 
	  if (bOk) {
	    //-- fechar relatorio gerencial --//
	    bOK = mainFrame.cmdEcf.pdv_fechaRelatorioGerencial();
	  }
	//}
	// Se houve algum problema define sTEFRetorno = 1
	if (bOk) {
	  bRepeat = false;
	} else {
	  if (mainFrame.tipoComprov.equals(RELAT_GERENC)) { //--"R"
	    bRepeat = true;
	  } else {
	    bRepeat = false;
	    td.sTEFRetorno = "1";
	  }
	}
      } while (bRepeat);
    }
  } //-- TEFImprimeTransacao ------------------------------------------------------------------------//

}
