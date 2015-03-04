package com.pizza.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Controls the Bestellung and Items Models</h1>
 * The BestellungList class controls the list of items, the quantity and the price 
 * of the order (or bestellung). Here are also the ordered items stored in the 
 * corresponding table in the database.
 * 
 * @author Sabah Al-Sabea
 */
public class BestellungsList extends com.pizza.CMModel.DBConnect {

    private Map<Integer, Integer> bestellList; // Map<PreisId, Menge>
    private Integer bestellungId;
    private Double totalPreis;

    public BestellungsList() {
    }

    /**
     * 
     * @return Map<Integer, Integer> This is a Map representing the ordered list of items
     * in the form of {@code Map<PreisId, Menge>}
     */
    public Map<Integer, Integer> getBestellList() {
        return bestellList;
    }

    /**
     * 
     * @param bestellungId the bestellungId value to be set to the bestellung entry
     * and the corresponding bestellung items
     */
    public void setBestellList(Map<Integer, Integer> bestellList) {
        this.bestellList = bestellList;
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
     * @param bestellungId the bestellungId value to be set to the bestellung entry
     * and the corresponding bestellung items
     */
    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }

    /**
     * 
     * @return Double This is the total price (sum) of the whole order (or bestellung)
     * in the form of {@code Map<PreisId, Menge>}
     */
    public Double getTotalPreis() {
        return totalPreis;
    }

    /**
     * 
     * @param totalPreis the totalPreis value to be set to the bestellung entry
     */
    public void setTotalPreis(Double totalPreis) {
        this.totalPreis = totalPreis;
    }

    /**
     * 
     * Stores the ordered items in the Items table of the database
     */
    public boolean store() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;

        try {
            con = getConnection();
            if (con == null) {
                return false;
            }
            stm = con.prepareStatement("INSERT INTO items (BestellungId, PreisId, Menge) VALUES(?,?,?)");
            int rows = 0;
            for (Map.Entry<Integer, Integer> item : this.bestellList.entrySet() ) {
                stm.setInt(1, this.bestellungId);
                stm.setInt(2, item.getKey());
                stm.setInt(3, item.getValue());
                rows += stm.executeUpdate();
            }

            
            System.out.println("rows stored in items are " + rows);            

            con.commit();
            stored = rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(com.pizza.Controller.BestellungsList.class.getName()).log(Level.SEVERE, null, ex);
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
