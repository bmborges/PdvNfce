/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.tef.paygo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class CmdTefPayGO {
    char chSeparador = '/';
    String  sTEFPath = "";
    
    private String sTefComando = "";
    private String sTefIdentificao = "";
    private String sTefVersaodaInterface = "";
    private String sTefRegistrodeCertificacao = "";
    private String sTefRegistroFinalizador = "";

    /**
     * @return the sTefComando
     */
    public String getsTefComando() {
        return sTefComando;
    }

    /**
     * @param sTefComando the sTefComando to set
     */
    public void setsTefComando(String sTefComando) {
        this.sTefComando = "000-000 = " + sTefComando;
    }

    /**
     * @return the sTefIdentificao
     */
    public String getsTefIdentificao() {
        return sTefIdentificao;
    }

    /**
     * @param sTefIdentificao the sTefIdentificao to set
     */
    public void setsTefIdentificao(String sTefIdentificao) {
        this.sTefIdentificao = "001-000 = " + sTefIdentificao;
    }

    /**
     * @return the sTefVersaodaInterface
     */
    public String getsTefVersaodaInterface() {
        return sTefVersaodaInterface;
    }

    /**
     * @param sTefVersaodaInterface the sTefVersaodaInterface to set
     */
    public void setsTefVersaodaInterface(String sTefVersaodaInterface) {
        this.sTefVersaodaInterface = "733-000 = " + sTefVersaodaInterface;
    }

    /**
     * @return the sTefRegistrodeCertificacao
     */
    public String getsTefRegistrodeCertificacao() {
        return sTefRegistrodeCertificacao;
    }

    /**
     * @param sTefRegistrodeCertificacao the sTefRegistrodeCertificacao to set
     */
    public void setsTefRegistrodeCertificacao(String sTefRegistrodeCertificacao) {
        this.sTefRegistrodeCertificacao = "738-000 = " + sTefRegistrodeCertificacao;
    }

    /**
     * @return the sTefRegistroFinalizador
     */
    public String getsTefRegistroFinalizador() {
        return sTefRegistroFinalizador;
    }

    /**
     * @param sTefRegistroFinalizador the sTefRegistroFinalizador to set
     */
    public void setsTefRegistroFinalizador(String sTefRegistroFinalizador) {
        this.sTefRegistroFinalizador = "999-999 = " + sTefRegistroFinalizador;
    }

    public CmdTefPayGO() {
        inicializaVariaveisPath();
        tefCriaArquivoREQ();
    }
    
    public void inicializaVariaveisPath() {
        chSeparador = (File.separator).charAt(0);
        sTEFPath = "C:" + chSeparador + "PAYGO" + chSeparador;
    } 
    
    public void tefCriaArquivoREQ() {

      File file = new File( sTEFPath + "REQ" + chSeparador + "IntPos.tmp" );

      try {   
        if(file.exists()){
          JOptionPane.showMessageDialog(null, "Arquivo ja Existe " +
                                      sTEFPath + "REQ" + chSeparador + "IntPos.tmp" + ": \n"
                                      , "Atencao:", JOptionPane.ERROR_MESSAGE);

        } else {
            file.createNewFile();
        }
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro na criacao do arquivo " +
                                      sTEFPath + "REQ" + chSeparador + "IntPos.tmp" + ": \n"
                                      + e.getMessage(), "Atencao:", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
      }

    }
     public boolean tefGravaOperacao(StringBuilder builder){
        boolean bReturn = false;
        while (!bReturn){
            try {
                BufferedWriter channel =
                new BufferedWriter(new FileWriter(sTEFPath + "REQ" + chSeparador + "IntPos.tmp"));

                channel.write(builder.toString());

                channel.close();

                try {
                    File src = new File(sTEFPath + "REQ" + chSeparador + "IntPos.tmp");
                    File dst = new File(sTEFPath + "REQ" + chSeparador + "IntPos.001");

                    boolean success = src.renameTo(dst);
                    if (!success) {
                      JOptionPane.showMessageDialog(null, "Problema na criacao do IntPos.001 ",
                                                    "ATEFJava:", JOptionPane.ERROR_MESSAGE);
                    }
                    src.delete(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro na copia de arquivos: \n" + ex.getMessage(),
                                                  "ATEFJava:", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bReturn;
    }

    public String tefVerificaIntPosSts() {
        String sRetorno = "";
        boolean iTEFExiste = false;    //-- 0 = false

        String sTEFLinha = "";

        for (int i = 0; i < 200; i++) {
          try {Thread.sleep(50);}
          catch (Exception ex) {ex.printStackTrace();}

          iTEFExiste = (new File(sTEFPath + "Resp" + chSeparador + "IntPos.sts")).exists();
          if (iTEFExiste) i = 200;
        }

        if (iTEFExiste) {
        }

        return sRetorno;
      }     
    
}
