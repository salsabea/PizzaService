package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Models the Speise Entry of the Speise Table</h1>
 * The SpeiseEintrag class is used to model the Speise Table of the database. In addition to 
 * getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table.
 * 
 * @author Sabah Al-Sabea
 */
public class SpeiseEintrag extends DBConnect {
    private Integer speiseId;
    private String name;
    private String typ;
    private String beschreibung;
    private String image;

    public SpeiseEintrag() {
        this.beschreibung = "";
        this.image = "";
    }

    /**
     * 
     * @return Integer This is the id of the current speise
     */
    public Integer getSpeiseId() {
        return speiseId;
    }

    /**
     * 
     * @param speiseId the speise id to be set to the current speise
     */
    public void setSpeiseId(Integer speiseId) {
        this.speiseId = speiseId;
    }

    /**
     * 
     * @return String This is the name of the current speise
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name the name to be set to the current speise
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return String This is the type of the current speise
     */
    public String getTyp() {
        return typ;
    }

    
    /**
     * 
     * @param typ the type to be set to the current speise
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * 
     * @return String This is the description of the current speise
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * 
     * @param beschreibung the description to be set to the current speise
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * 
     * @return String This is the image location of the current speise
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image the image location to be set to the current speise
     */
    public void setImage(String image) {
        this.image = image;
    }
        
    /**
     * 
     * Stores the current speise entry in the Speise table in the database
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
            stm = con.prepareStatement("INSERT INTO speise (Name, Beschreibung, Typ, Image) "
                    + "VALUES(?,?,?,?)");
            stm.setString(1, this.name.trim());
            
            if (this.beschreibung != null && !this.beschreibung.equals("")) 
                stm.setString(2, this.beschreibung.trim());
            else
                stm.setString(2, "NULL");
            
            stm.setString(3, this.typ.trim());
            
            if (this.image != null && !this.image.equals("")) 
                stm.setString(4, this.image.trim());
            else
                stm.setString(4, "NULL");
            
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
