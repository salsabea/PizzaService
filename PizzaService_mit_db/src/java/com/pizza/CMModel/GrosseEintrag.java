/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sabah Al-Sabea
 */
public class GrosseEintrag extends DBConnect {
    private Integer grosseId;
    private String grosseName;
    private String grosseBeschreibung;

    public GrosseEintrag() {
        this.grosseBeschreibung = "";
    }

    public GrosseEintrag(Integer grosseId, String grosseName, String grosseBeschreibung) {
        this.grosseId = grosseId;
        this.grosseName = grosseName;
        this.grosseBeschreibung = grosseBeschreibung;
    }

    public Integer getGrosseId() {
        return grosseId;
    }

    public void setGrosseId(Integer grosseId) {
        this.grosseId = grosseId;
    }

    public String getGrosseName() {
        return grosseName;
    }

    public void setGrosseName(String grosseName) {
        this.grosseName = grosseName;
    }

    public String getGrosseBeschreibung() {
        return grosseBeschreibung;
    }

    public void setGrosseBeschreibung(String grosseBeschreibung) {
        this.grosseBeschreibung = grosseBeschreibung;
    }

    public boolean store() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        
        try {
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("INSERT INTO grosse (Name, Beschreibung) "
                    + "VALUES(?,?)");
            stm.setString(1, this.grosseName.trim());
            
            if (this.grosseBeschreibung != null && !this.grosseBeschreibung.equals("")) 
                stm.setString(2, this.grosseBeschreibung.trim());
            else
                stm.setString(2, "NULL");
            
            int rows = stm.executeUpdate();
            con.commit();
            stored = rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SpeiseEintrag.class.getName()).log(Level.SEVERE, null, ex);
            stored = false;
        } finally {
            try { if( stm != null) stm.close(); } catch(Exception e) {}
            try { if( con != null) con.close(); } catch(Exception e) {}
        }
        return stored;
    }
}
