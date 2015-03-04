package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Models the Preis Entry of the Preis Table</h1>
 * The PreisEintrag class is used to model the Preis Table of the database. In addition to 
 * getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table.
 * 
 * @author Sabah Al-Sabea
 */
public class PreisEintrag extends DBConnect{
    private Integer preisId;
    private Integer speiseId;
    private Integer grosseId;
    private Double preis;

    public PreisEintrag() {
        this.grosseId = -1;
    }
    
    /**
     * 
     * @return Integer This is the id of the current preis
     */
    public Integer getPreisId() {
        return preisId;
    }
    
    /**
     * 
     * @param preisId the preis Id to be set to the current preis entry
     */
    public void setPreisId(Integer preisId) {
        this.preisId = preisId;
    }
    
    /**
     * 
     * @return Integer This is the speiseId of the current preis
     */
    public Integer getSpeiseId() {
        return speiseId;
    }

    /**
     * 
     * @param speiseId the speiseId value to be set to the current preis entry
     */
    public void setSpeiseId(Integer speiseId) {
        this.speiseId = speiseId;
    }

    /**
     * 
     * @return Integer This is the grosseId of the current preis
     */
    public Integer getGrosseId() {
        return grosseId;
    }

    /**
     * 
     * @param grosseId the grosseId value to be set to the current preis entry
     */
    public void setGrosseId(Integer grosseId) {
        this.grosseId = grosseId;
    }

    /**
     * 
     * @return Double This is the value of the current preis
     */
    public Double getPreis() {
        return preis;
    }

    /**
     * 
     * @param preis the preis value to be set to the current preis entry
     */
    public void setPreis(Double preis) {
        this.preis = preis;
    }
    
    /**
     * 
     * Stores the current preis entry in the Preis table in the database
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

}
