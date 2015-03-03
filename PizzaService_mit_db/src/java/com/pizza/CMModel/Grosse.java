/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IBB Teilnehmer
 */

public class Grosse extends DBConnect{
    private GrosseEintrag meineEintrag;

    public List<GrosseEintrag> getList() {
        List<GrosseEintrag> grosseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

            connection = getConnection();
            if (connection == null) {
                return null;
            }
            
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM grosse");
            
            while (result.next()){
                GrosseEintrag currentGrosseEintrag = new GrosseEintrag();
                currentGrosseEintrag.setGrosseId(result.getInt("GrosseId"));
                currentGrosseEintrag.setGrosseName(result.getString("Name"));
                currentGrosseEintrag.setGrosseBeschreibung(result.getString("Beschreibung"));
                grosseList.add(currentGrosseEintrag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grosseList;
    }
    
    public List<GrosseEintrag> getList(Integer speiseId){
        List<GrosseEintrag> grosseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

            connection = getConnection();
            if (connection == null) {
                return null;
            }
            
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT grosse.* FROM grosse "
                    + "                     INNER JOIN preis ON grosse.GrosseId = preis.GrosseId "
                    + "                     WHERE preis.SpeiseId = " + speiseId);
            
            while (result.next()){
                GrosseEintrag currentGrosseEintrag = new GrosseEintrag();
                currentGrosseEintrag.setGrosseId(result.getInt("GrosseId"));
                currentGrosseEintrag.setGrosseName(result.getString("Name"));
                currentGrosseEintrag.setGrosseBeschreibung(result.getString("Beschreibung"));
                grosseList.add(currentGrosseEintrag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grosseList;
    }
    
    public GrosseEintrag getGrosseFromPreisId(Integer PreisId){
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        GrosseEintrag ge = new GrosseEintrag();

        try {
            connection = getConnection();
            if (connection == null) {
                return null;
            }
            
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT grosse.* FROM grosse "
                    + "                     INNER JOIN preis ON grosse.GrosseId = preis.GrosseId "
                    + "                     WHERE preis.PreisId = " + PreisId);
            
            int count = 0;
            while (result.next()){                
                ge.setGrosseId(result.getInt("GrosseId"));
                ge.setGrosseName(result.getString("Name"));
                ge.setGrosseBeschreibung(result.getString("Beschreibung"));                
            }
            if (count > 1){
                System.out.println("ERROR: Each preisId should correspond to one or no grosse");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ge;
    }
}
