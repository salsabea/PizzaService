/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sabah
 */
public class BestellungsList extends com.pizza.CMModel.DBConnect {

    private Map<Integer, Integer> bestellList; // Map<PreisId, Menge>
    private Integer bestellungId;
    private Double totalPreis;

    public BestellungsList() {
    }

    public BestellungsList(Map<Integer, Integer> bestellList, Integer bestellungId, Double totalPreis) {
        this.bestellList = bestellList;
        this.bestellungId = bestellungId;
        this.totalPreis = totalPreis;
    }

    public Map<Integer, Integer> getBestellList() {
        return bestellList;
    }

    public void setBestellList(Map<Integer, Integer> bestellList) {
        this.bestellList = bestellList;
    }

    public Integer getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(Integer bestellungId) {
        this.bestellungId = bestellungId;
    }

    public Double getTotalPreis() {
        return totalPreis;
    }

    public void setTotalPreis(Double totalPreis) {
        this.totalPreis = totalPreis;
    }

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
