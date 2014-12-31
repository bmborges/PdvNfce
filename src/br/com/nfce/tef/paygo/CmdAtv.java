/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.tef.paygo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class CmdAtv extends CmdTefPayGO{
    
    
    public CmdAtv() {
    }

    @Override
    public void setsTefComando(String sTefComando) {
        super.setsTefComando(sTefComando); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getsTefComando() {
        return super.getsTefComando(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsTefIdentificao(String sTefIdentificao) {
        super.setsTefIdentificao(sTefIdentificao); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getsTefIdentificao() {
        return super.getsTefIdentificao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsTefVersaodaInterface(String sTefVersaodaInterface) {
        super.setsTefVersaodaInterface(sTefVersaodaInterface); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getsTefVersaodaInterface() {
        return super.getsTefVersaodaInterface(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsTefRegistrodeCertificacao(String sTefRegistrodeCertificacao) {
        super.setsTefRegistrodeCertificacao(sTefRegistrodeCertificacao); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getsTefRegistrodeCertificacao() {
        return super.getsTefRegistrodeCertificacao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setsTefRegistroFinalizador(String sTefRegistroFinalizador) {
        super.setsTefRegistroFinalizador(sTefRegistroFinalizador); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getsTefRegistroFinalizador() {
        return super.getsTefRegistroFinalizador(); //To change body of generated methods, choose Tools | Templates.
    }
    
   public void GravaOperacao(){
        setsTefComando("ATV");
        setsTefIdentificao("1");
        setsTefRegistroFinalizador("0");
        
        StringBuilder builder = new StringBuilder();
        builder.append(getsTefComando()+"\r\n");
        builder.append(getsTefIdentificao()+"\r\n");
        builder.append(getsTefRegistroFinalizador()+"\r\n");
        
        boolean bOK = tefGravaOperacao(builder);
        String sRetorno; 
        if (bOK){
            sRetorno = tefVerificaIntPosSts();
        }
   } 
    
   
    
   
    
    
}
