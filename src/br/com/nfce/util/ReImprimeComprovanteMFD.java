/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author root
 */
public class ReImprimeComprovanteMFD {
    public static void main(String[] args) {
        
        int iRetorno = 0;
        BematechNFiscal cupom = BematechNFiscal.Instance;
        iRetorno = cupom.ConfiguraModeloImpressora(5);
        iRetorno = cupom.IniciaPorta("USB");
        iRetorno = cupom.AjustaLarguraPapel(80);
        iRetorno = cupom.SelecionaQualidadeImpressao(4);
        
        BufferedReader br = null;
 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("C:\\mfd_30122014.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
                                iRetorno = cupom.FormataTX("\n"+sCurrentLine,1,0,0,0,0);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
    
}
