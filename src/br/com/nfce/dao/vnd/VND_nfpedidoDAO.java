/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nfce.dao.vnd;

import br.com.nfce.bean.vnd.VND_NfpedidoBean;
import br.com.nfce.util.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author supervisor
 */
public class VND_nfpedidoDAO {
    
      public String dadosadicionais = "";
      public String strColunas;
      java.sql.Connection conn = null;

      public VND_nfpedidoDAO() throws Exception{
         conn = Database.getConnection();

         if (conn == null) {
            throw new Exception(getClass().getName() + ": null connection passed.");
         }
            this.conn = conn;
         }


public String pesquisa_protocolo(int cdpedido) throws SQLException{
    String qry = "select protocolo from vnd_nfpedido ";
    qry += " where cdpedido = ?";
    
    PreparedStatement stmt = conn.prepareStatement(qry);
    stmt.setInt(1, cdpedido);
    
    ResultSet rs = stmt.executeQuery();

    //System.out.println(">>>Select....: " + stmt.toString());

    String retorno = "";
    
    if (rs.next()) {
       retorno = rs.getString("protocolo");
    }
    rs.close();
    stmt.close();

    return retorno;

}









public HashMap pesquisa_nfpedido_imp() throws SQLException, Exception{
    
   
    String qry = "select cdpedido, iduf, idnfe"
            + " from vnd_nfpedido nf"
            + " inner join vnd_pedvenda using (cdpedido)"
            + " inner join cdc_estabelecim e using (idestabelecimen)"
            + " inner join adm_estado es on (e.uf = es.uf )"
            + " where situacaonf in ('N') and status_nfe in (100)"
            + " order by cdpedido desc"
            + " limit 1";

    PreparedStatement stmt = conn.prepareStatement(qry);

    ResultSet rs = stmt.executeQuery();
    HashMap map = new HashMap();

    if (rs.next()) {
        map.put("cdpedido", rs.getInt(1));
        map.put("iduf", rs.getInt(2));
        map.put("idnfe", rs.getString(3));
    } else {
        map.put("cdpedido", 0);
    }
    rs.close();
    stmt.close();
    
    return map;

}






public String remove_acento(String xml) throws SQLException{
    String retorno = "";
    String qry = "select fu_remove_acento(?);";

    PreparedStatement stmt = conn.prepareStatement(qry);
    stmt.setString(1, xml);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        retorno = rs.getString(1);
    }
    
    return retorno;
}


public String getTotaltributos(int cdpedido) throws SQLException{
    String ret = "";

    String qry = "select sum(vtottrib) as vtottrib," +
            " round(((sum(vtottrib) / sum(subtotal)) * 100)::numeric,2) as perc" +
            " from vnd_itempedv where cdpedido = ?";


    PreparedStatement stmt = conn.prepareStatement(qry);
    stmt.setInt(1, cdpedido);
    ResultSet rs = stmt.executeQuery();

    if(rs.next()){
        ret = "Val.Aprox.Tributos R$ " + rs.getString("vtottrib") + " ("+ rs.getString("perc") +"%) Fonte: IBPT";
    }
    return ret;
}

private VND_NfpedidoBean populate (ResultSet rs) throws SQLException, Exception {
    try {
      VND_NfpedidoBean gs = new VND_NfpedidoBean();

      gs.setCdpedido( rs.getInt   ( 1) );
      gs.setCdnf( rs.getString   ( 2) );
      gs.setData( rs.getString( 3) );

      return gs;
    } catch (Exception e ) {
      throw new Exception ("Erro na leitura do VND_NfpedidoBean!");
    }
  }

}
