package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Models the Grosse Entry of the Grosse Table</h1>
 * The GrosseEintrag class is used to model the Grosse Table of the database. In addition to 
 * getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table.
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

    /**
     * 
     * @return Integer This is the id of the current grosse
     */
    public Integer getGrosseId() {
        return grosseId;
    }

    /**
     * 
     * @param grosseId the grosse id to be set to the current grosse
     */
    public void setGrosseId(Integer grosseId) {
        this.grosseId = grosseId;
    }

    /**
     * 
     * @return String This is the name of the current grosse
     */
    public String getGrosseName() {
        return grosseName;
    }

    /**
     * 
     * @param grosseName the grosse name to be set to the current grosse
     */
    public void setGrosseName(String grosseName) {
        this.grosseName = grosseName;
    }

    /**
     * 
     * @return String This is the description of the current grosse
     */    
    public String getGrosseBeschreibung() {
        return grosseBeschreibung;
    }

    /**
     * 
     * @param grosseBeschreibung the description to be set to the current grosse
     */
    public void setGrosseBeschreibung(String grosseBeschreibung) {
        this.grosseBeschreibung = grosseBeschreibung;
    }

    /**
     * 
     * Stores the current grosse entry in the Grosse table in the database
     * 
     * @return boolean true if successfully stored
     */
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
