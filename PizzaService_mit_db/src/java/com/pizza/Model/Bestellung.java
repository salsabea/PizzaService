/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Sabah
 */
public class Bestellung extends com.pizza.CMModel.DBConnect {
    private Integer bestellungId;
    private Integer kundeId;
    private String sessionId;
    private String ipAddress;    
    private Double totalPreis;    

    public Bestellung() {
    }

    public Bestellung(Integer bestellungId, Integer kundeId, String sessionId, String ipAddress, Double totalPreis) {
        this.bestellungId = bestellungId;
        this.kundeId = kundeId;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;     
        this.totalPreis = totalPreis;
    }

    public Integer getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }        

    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Double getTotalPreis() {
        return totalPreis;
    }

    public void setTotalPreis(Double totalPreis) {
        this.totalPreis = totalPreis;
    }
    
    public boolean storeTotalPreis(Double totalPreis) {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        
        try {
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("UPDATE bestellung  SET TotalPreis = ? WHERE BestellungId = ?");
            stm.setDouble(1, totalPreis);
            stm.setInt(2, this.getBestellungId());
            
            
            int rows = stm.executeUpdate();
            System.out.println("totalPreis updated");
                                    
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
    
    public boolean store(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        
        this.setSessionId(req.getSession().getId());
        this.setIpAddress(req.getRemoteAddr()); 
        try {
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("INSERT INTO bestellung (KundeId, sessionId, ipAddress, Datum) "
                    + "VALUES(?,?,?,CURRENT_TIMESTAMP)", stm.RETURN_GENERATED_KEYS);
            stm.setInt(1, this.kundeId);
            stm.setString(2, this.sessionId.trim());
            stm.setString(3, this.ipAddress.trim());                                    
            
            int rows = stm.executeUpdate();
            System.out.println("rows stored in bestellung are " + rows);
                        
            if (rows == 0) {
                throw new SQLException("Kein Bestellung wird gespeischert");
            }
            
            try (ResultSet generatedKey = stm.getGeneratedKeys()){
                if (generatedKey.next()){                    
                    this.setBestellungId(generatedKey.getInt(1));
                } else {
                    throw new SQLException("Kein KundeId");
                }
            }
            
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
