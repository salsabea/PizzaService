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

/**
 *
 * @author Sabah
 */
public class Kunde extends com.pizza.CMModel.DBConnect {

    private Integer kundeId;
    private String vorname;
    private String nachname;

    private String strasse;
    private String hausNr;
    private Integer plz;
    private String stadt;
    private String telefonnummer;

    public Kunde() {
    }

    public Kunde(Integer kundeId, String vorname, String nachname, String strasse, String adresseHausNr, Integer adressePLZ, String adresseStadt, String telefonnummer) {
        this.kundeId = kundeId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.hausNr = adresseHausNr;
        this.plz = adressePLZ;
        this.stadt = adresseStadt;
        this.telefonnummer = telefonnummer;
    }

    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausNr() {
        return hausNr;
    }

    public void setHausNr(String hausNr) {
        this.hausNr = hausNr;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }    

    public boolean store() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;

        try {
            con = getConnection();
            if (con == null) {
                return false;
            }
            stm = con.prepareStatement("INSERT INTO kunde (Vorname, Nachname, Telefon, Strasse, Plz, HausNr, Stadt) "
                    + "VALUES(?,?,?,?,?,?,?)", stm.RETURN_GENERATED_KEYS);
            stm.setString(1, this.vorname.trim());
            stm.setString(2, this.nachname.trim());
            stm.setString(3, this.telefonnummer.trim());
            stm.setString(4, this.strasse.trim());
            stm.setInt(5, this.plz);
            stm.setString(6, this.hausNr.trim());
            stm.setString(7, this.stadt.trim());

            int rows = stm.executeUpdate();
            System.out.println("rows stored in kunde are " + rows);
            
            if (rows == 0) {
                throw new SQLException("Kein Kunde gespeischert");
            }
            
            try (ResultSet generatedKey = stm.getGeneratedKeys()){
                if (generatedKey.next()){                    
                    this.setKundeId(generatedKey.getInt(1));
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
        return stored;
    }
}
