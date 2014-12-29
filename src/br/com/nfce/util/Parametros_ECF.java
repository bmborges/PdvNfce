/*
 * Parametros_ECF.java
 * 
 * Created on 16/07/2007, 16:03:00
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nfce.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * @author Cleber Calixto de Moraes
 */
public class Parametros_ECF {
  //=== Definicao de Variaveis ========================================================================

  public String  stImpressora    =  "";
  public String  stPortaBalanca  =  "";
  public int     inVelBalanca    =  0;
  public String  stParamtxt      =  "";
  public String  stIdestabelecimento = "";
  public String  stCdc = "";
  
  public Parametros_ECF() {
    try {
      ler_Parametros();
    } catch(Exception e) {
      e.printStackTrace();
    }
  } //=== *** =========================================================================================
    public static void main(String[] args) {
            Parametros_ECF p = new Parametros_ECF();
    }
  private String retornaParametro(String s) {
    String sToken = "";
    String sRet = "";

    if (s == null || s.equals("")) return "";

    //-- retornar resultado --//
    StringTokenizer tokens = new StringTokenizer(s, ":");

    if (tokens.countTokens() < 2) return "";

    tokens.nextToken();
    sRet = tokens.nextToken().trim();

    return sRet;
  }

  public void ler_Parametros(){
    try {
      String arquivo = "ParamECF.txt";

      String curDir = System.getProperty("user.home");
      String curSep = System.getProperty("file.separator");

      BufferedReader br = new BufferedReader(new FileReader(curDir + curSep + arquivo));

      stImpressora   = retornaParametro(br.readLine());
      stPortaBalanca = retornaParametro(br.readLine());
      inVelBalanca   = Integer.parseInt( retornaParametro(br.readLine()) );
      stParamtxt     = retornaParametro(br.readLine());
      stIdestabelecimento =  retornaParametro(br.readLine());
      stCdc =  retornaParametro(br.readLine());

//      JOptionPane.showMessageDialog(null, "Parametro: " + curDir + curSep + arquivo +
//                                          "\nImpressora: " + stImpressora +
//                                          "\nPorta Balanca: " + stPortaBalanca +
//                                          "\nVel. Balanca: " + inVelBalanca +
//                                          "\nParametro: " + stParamtxt
//                                          , "Atencao:", JOptionPane.INFORMATION_MESSAGE);
//
//JOptionPane.showMessageDialog(null, "Velocidade Balança: " + curDir + curSep + arquivo + "\n  " + inVelBalanca, "Atencao:", JOptionPane.INFORMATION_MESSAGE);

      br.close();
    } catch (IOException e) {
      System.out.println("\nErro na leitura do de Parametros do ECF.\n");
      e.printStackTrace();
    }
  }  //== ler_Parametros ==============================================================================

}
