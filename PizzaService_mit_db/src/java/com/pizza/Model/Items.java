/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sabah
 */
public class Items extends com.pizza.CMModel.DBConnect {
    private Integer itemId;
    private Integer bestellungId;
    private Integer preisId;
    private Integer menge;

    public Items() {
    }

    public Items(Integer itemId, Integer bestellungId, Integer preisId, Integer menge) {
        this.itemId = itemId;
        this.bestellungId = bestellungId;
        this.preisId = preisId;
        this.menge = menge;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }

    public Integer getPreisId() {
        return preisId;
    }

    public void setPreisId(Integer preisId) {
        this.preisId = preisId;
    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }
    
    public boolean store() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        
        try {
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("INSERT INTO items (BestellungsId, PreisId, Menge) "
                    + "VALUES(?,?,?)");
            stm.setInt(1, this.bestellungId);
            stm.setInt(2, this.preisId);
            stm.setInt(3, this.menge);
            
            int rows = stm.executeUpdate();
            System.out.println("rows stored are " + rows);
            con.commit();
            stored = rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(com.pizza.Model.Kunde.class.getName()).log(Level.SEVERE, null, ex);
            stored = false;
        } finally {
            try { if( stm != null) stm.close(); } catch(Exception e) {}
            try { if( con != null) con.close(); } catch(Exception e) {}
        }
        return stored;
    }
}
