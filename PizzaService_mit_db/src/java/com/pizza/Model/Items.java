package com.pizza.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Models the Items entry of the Items Table</h1>
 * The Items class is used to model the entries of the items table of the database. 
 * In addition to getters and setter of the corresponding fields, there is a method used to store a new 
 * entry to the table.
 * 
 * @author Sabah Al-Sabea
 */
public class Items extends com.pizza.CMModel.DBConnect {
    private Integer itemId;
    private Integer bestellungId;
    private Integer preisId;
    private Integer menge;

    public Items() {
    }
    
    /**
     * 
     * @return Integer This is the id of the item from the items table
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 
     * @param itemId the item id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 
     * @return Integer This is the bestellung id of the item from the items table
     */
    public Integer getBestellungId() {
        return bestellungId;
    }

    /**
     * 
     * @param bestellungId the bestellung id of the item
     */
    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }

    /**
     * 
     * @return Integer This is the price id of the item from the items table
     */
    public Integer getPreisId() {
        return preisId;
    }

    /**
     * 
     * @param preisId the preis id of the item
     */
    public void setPreisId(Integer preisId) {
        this.preisId = preisId;
    }

    /**
     * 
     * @return Integer This is the amount ordered of this item from the items table
     */
    public Integer getMenge() {
        return menge;
    }

    /**
     * 
     * @param menge the amount ordered of the item
     */
    public void setMenge(Integer menge) {
        this.menge = menge;
    }
    
}
