package com.pizza.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Models the Kunde entry of the Kunde Table</h1>
 * The Kunde class is used to model the entries of the kunde table of the database. 
 * In addition to getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table.
 * 
 * @author Sabah Al-Sabea
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

    /**
     * 
     * @return Integer This is the id of the customer (or kunde) from the kunde table
     */
    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    /**
     * 
     * @return Integer This is the first name of the customer (or kunde) from the kunde table
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * 
     * @param vorname the first name of the customer from kunde table
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * 
     * @return Integer This is the last name of the customer (or kunde) from the kunde table
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * 
     * @param nachname the last name of the customer from kunde table
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * 
     * @return Integer This is the street name of the customer (or kunde) from the kunde table
     */
    public String getStrasse() {
        return strasse;
    }
    
    /**
     * 
     * @param strasse the street name of the customer from kunde table
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * 
     * @return Integer This is the house number of the customer (or kunde) from the kunde table
     */
    public String getHausNr() {
        return hausNr;
    }

    /**
     * 
     * @param hausNr the house number of the customer from kunde table
     */
    public void setHausNr(String hausNr) {
        this.hausNr = hausNr;
    }

    /**
     * 
     * @return Integer This is the ZIP code of the customer (or kunde) from the kunde table
     */
    public Integer getPlz() {
        return plz;
    }

    /**
     * 
     * @param plz the ZIP code of the customer from kunde table
     */
    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    /**
     * 
     * @return Integer This is the address city of the customer (or kunde) from the kunde table
     */
    public String getStadt() {
        return stadt;
    }

    /**
     * 
     * @param stadt the address city of the customer from kunde table
     */
    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    /**
     * 
     * @return String This is the telephone number of the customer (or kunde) from the kunde table
     */
    public String getTelefonnummer() {
        return telefonnummer;
    }

    /**
     * 
     * @param telefonnummer the telephone number of the customer from kunde table
     */
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }    

    /**
     * 
     * Stores an entry to the kunde table using the current kunde
     * 
     * @return boolean true if successfully stored 
     */
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
