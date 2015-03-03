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
 * @author Sabah
 */
public class PreisEintrag extends DBConnect{
    private Integer preisId;
    private Integer speiseId;
    private Integer grosseId;
    private Double preis;

    public PreisEintrag() {
        this.grosseId = -1;
    }

    public PreisEintrag(Integer preisId, Integer speiseId, Integer grosseId, Double preis) {
        this.preisId = preisId;
        this.speiseId = speiseId;
        this.grosseId = grosseId;
        this.preis = preis;
    }

    public Integer getPreisId() {
        return preisId;
    }

    public void setPreisId(Integer preisId) {
        this.preisId = preisId;
    }
    public boolean store() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        
        try {
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("INSERT INTO preis (SpeiseId, GrosseId, Preis) "
                    + "VALUES(?,?,?)");
            
            stm.setInt(1, this.speiseId); 
            
            stm.setInt(2, this.grosseId);
            stm.setDouble(3, this.preis);
                        
            int rows = stm.executeUpdate();
            System.out.println("rows stored are " + rows);
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

    public Integer getSpeiseId() {
        return speiseId;
    }

    public void setSpeiseId(Integer speiseId) {
        this.speiseId = speiseId;
    }

    public Integer getGrosseId() {
        return grosseId;
    }

    public void setGrosseId(Integer grosseId) {
        this.grosseId = grosseId;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }
    
    
}
