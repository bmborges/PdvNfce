/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfce.util;

import br.com.xml.nfe.TNfeProc;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author bruno
 */
public class lerxmlnfe {
    
    public String[] ler_xml(String arquivo) throws JAXBException {
        // TODO code application logic here
        
        String retorno[] = new String[30];
        retorno[1] = ""; 
        int r = 0;


        //String sInstance

        File notaFile = new File(arquivo);



        JAXBContext context = JAXBContext.newInstance("br.com.xml.nfe");

	Unmarshaller unmarshaller = context.createUnmarshaller();
	//Marshaller   marshaller   = context.createMarshaller();
	//File notaFile = new File("C:/nfe2.xml");
	//TNFe nfe = unmarshaller.unmarshal( new StreamSource(notaFile), TNFe.class ).getValue();
        
        TNfeProc proc = unmarshaller.unmarshal( new StreamSource(notaFile), TNfeProc.class ).getValue();
	 
                
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getId());
        retorno[r] = proc.getNFe().getInfNFe().getId();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getIde().getNNF());
        retorno[r] = proc.getNFe().getInfNFe().getIde().getNNF();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getIde().getDEmi());
        retorno[r] = proc.getNFe().getInfNFe().getIde().getDEmi();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());
        retorno[r] = proc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getCNPJ());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getCNPJ();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getXNome());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getXNome();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF().toString();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getCEP());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getCEP();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getIE());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getIE();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr());
        retorno[r] = proc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getCNPJ());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getCNPJ();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getXNome());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getXNome();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getEnderDest().getXLgr());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getEnderDest().getXLgr();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getEnderDest().getNro());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getEnderDest().getNro();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getEnderDest().getXBairro());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getEnderDest().getXBairro();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getEnderDest().getXMun());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getEnderDest().getXMun();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getEnderDest().getUF());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getEnderDest().getUF().toString();
        r++;
        System.out.println("retorno["+r+"]: " + proc.getNFe().getInfNFe().getDest().getIE());
        retorno[r] = proc.getNFe().getInfNFe().getDest().getIE();
        r++;
        for (int i = 0; i < proc.getNFe().getInfNFe().getDet().size(); i++){
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getCProd());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getCEAN());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getXProd());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getNCM());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getCFOP());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getQCom());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getVUnCom());
            System.out.println(proc.getNFe().getInfNFe().getDet().get(i).getProd().getVProd());
        }
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVBC());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVICMS());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVBCST());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVST());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVProd());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVFrete());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVSeg());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVDesc());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVII());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVIPI()); 
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVPIS());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVCOFINS());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVOutro());
        System.out.println(proc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());
        System.out.println(proc.getNFe().getInfNFe().getTransp().getModFrete());
        System.out.println(proc.getNFe().getInfNFe().getCobr().getFat().getNFat());
        System.out.println(proc.getNFe().getInfNFe().getCobr().getFat().getVOrig());
        System.out.println(proc.getNFe().getInfNFe().getCobr().getFat().getVLiq());

        return retorno;
    }
    
    
    
    
}
