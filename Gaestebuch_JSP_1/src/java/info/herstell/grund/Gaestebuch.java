/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.herstell.grund;

import info.herstell.grund.Eintrag;
import info.herstell.grund.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Polichronis Tsolakidis
 */
public class Gaestebuch extends GConnection {

 private Eintrag meineEinträge;
    
    
    
    public List<Eintrag> getList() {
        
        List<Eintrag> v = new ArrayList<Eintrag>();
       
        
       
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            if (con == null) {
                return null;
            }
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT vorname,nachname,email,web,age,region,kommentar,sessionid,inet,datum FROM gaestebuch  ORDER BY datum DESC,nachname,vorname ");
            
            while (rs.next()) {
                Eintrag en = new Eintrag();
              
                en.setVorname(rs.getString("vorname"));
                System.out.println("Vorname:"+rs.getString("vorname") );
                en.setNachname(rs.getString("nachname"));
                en.setWeb(rs.getString("web"));
                //en.setAge(rs.getInt("age"));
                en.setRegion(rs.getString("region"));
                en.setKommentar(rs.getString("kommentar"));
                en.setSessionid(rs.getString("sessionid"));
                en.setInet(rs.getString("inet"));
                en.setDatum( new Date(rs.getTimestamp("datum").getTime()));
                v.add(en);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return v;
    }
}
