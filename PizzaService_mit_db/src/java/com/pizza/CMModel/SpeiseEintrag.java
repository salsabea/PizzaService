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

    public SpeiseEintrag(Integer speiseId, String name, String typ, String beschreibung, String image) {
        this.speiseId = speiseId;
        this.name = name;
        this.typ = typ;
        this.beschreibung = beschreibung;
        this.image = image;
    }

    public Integer getSpeiseId() {
        return speiseId;
    }

    public void setSpeiseId(Integer speiseId) {
        this.speiseId = speiseId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
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
