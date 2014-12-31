/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.tef;

import br.com.nfce.tef.paygo.CmdAtv;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class Tef_PayGo {


    public Tef_PayGo() {
    }
    

    public static void main(String[] args) throws Exception {
        Tef_PayGo pg = new Tef_PayGo();
        pg.GerenciaComando("ATV");
    }
   
    public void GerenciaComando(String comando) throws Exception {
        switch(comando.toUpperCase()){
            case "ATV":
                 ATV();
            case "CRT":
                throw new Exception("Comando não Configurado");
            case "ADM":
                throw new Exception("Comando não Configurado");
            case "CNC":
                throw new Exception("Comando não Configurado");
            case "CNF":
                throw new Exception("Comando não Configurado");
            case "NCN":
                throw new Exception("Comando não Configurado");
            case "CDP":
                throw new Exception("Comando não Configurado");
            default:
                throw new Exception("Comando não Configurado");
        }
    }
    

    public void ATV(){
        CmdAtv atv = new CmdAtv();
        atv.GravaOperacao();
        
    }
}
