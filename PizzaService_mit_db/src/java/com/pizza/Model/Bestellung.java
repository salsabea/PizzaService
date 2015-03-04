package com.pizza.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


/**
 * <h1>Models the Bestellung entry of the Bestellung Table</h1>
 * The Bestellung class is used to model the entries of the bestellung table of the database. 
 * In addition to getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table and another one to update the totalPreis.
 * 
 * @author Sabah Al-Sabea
 */
public class Bestellung extends com.pizza.CMModel.DBConnect {
    private Integer bestellungId;
    private Integer kundeId;
    private String sessionId;
    private String ipAddress;    
    private Double totalPreis;    

    public Bestellung() {
    }

    /**
     * 
     * @return Integer This is the id of the order (or bestellung) from the bestellung table
     */
    public Integer getBestellungId() {
        return bestellungId;
    }

    /**
     * 
     * @param bestellungId the Id of the bestellung from the bestellung table.
     */
    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }        

    /**
     * 
     * @return Integer This is the id of the customer (or kunde) from the bestellung table
     */
    public Integer getKundeId() {
        return kundeId;
    }

    /**
     * 
     * @param kundeId the Id of the customer (or kunde) from the bestellung table.
     */
    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    /**
     * 
     * @return String This is the sessionId of the order (or bestellung)
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 
     * @param sessionId the session Id of the bestellung from the bestellung table.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 
     * @return String This is the IP Address of the order (or bestellung)
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 
     * @param ipAddess the IP Address of the bestellung from the bestellung table.
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 
     * @return Double This is the total price of the order (or bestellung)
     */
    public Double getTotalPreis() {
        return totalPreis;
    }

    /**
     * 
     * @param totalPreis the total price sum of all the items in a bestellung.
     * It should be stored in the bestellung table.
     */
    public void setTotalPreis(Double totalPreis) {
        this.totalPreis = totalPreis;
    }
    
    /**
     * 
     * Stores an entry to the bestellug table using the current bestellung
     * 
     * @return boolean true if successfully stored 
     */
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
    
    /**
     * 
     * Updates the totalPreis value with the given value
     * 
     * @param totalPreis the value of the total price of the bestellung 
     * @return boolean true if successfully updated
     */
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
    
}
