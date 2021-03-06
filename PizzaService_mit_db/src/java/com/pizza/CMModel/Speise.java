/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Controls the database part of the SpeiseEintrg Model</h1>
 * The Speise class is used to do the actual connection to the database. It contains
 * a set of methods needed to get Speise entries. Here the actual queries are executed.
 * 
 * @author Sabah Al-Sabea
 */
public class Speise extends DBConnect {
    /**
     * This method gets all the entries from the Speise table
     * 
     * @return List<SpeiseEintrag> This is a list of all the Speise entries
     */
    public List<SpeiseEintrag> getList() {
        List<SpeiseEintrag> speiseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = getConnection();
            if (connection == null) {
                return null;
            }

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM speise ORDER BY Typ");

            while (result.next()) {
                SpeiseEintrag currentSpeiseEintrag = new SpeiseEintrag();

                currentSpeiseEintrag.setSpeiseId(result.getInt("speiseId"));
                currentSpeiseEintrag.setName(result.getString("name"));
                currentSpeiseEintrag.setBeschreibung(result.getString("beschreibung"));
                currentSpeiseEintrag.setTyp(result.getString("typ"));
                currentSpeiseEintrag.setImage(result.getString("image"));
                speiseList.add(currentSpeiseEintrag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        return speiseList;
    }

    /**
     * This method gets all the specific speise entry  from the Speise table 
     * that corresponds to a certain preisId
     * 
     * @param preisId The preisId whose corresponding Speise we are looking for
     * @return SpeiseEintrag The speise entry corresponding to {@code preisId}
     */
    public SpeiseEintrag getSpeiseFromPreisId(Integer preisId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        SpeiseEintrag speiseEintrag = new SpeiseEintrag();

        try {
            connection = getConnection();
            if (connection == null) {
                return null;
            }
            
            String query = "SELECT speise.* FROM preis "
                    + "INNER JOIN speise "
                    + "ON speise.SpeiseId = preis.SpeiseId"
                    + " WHERE preis.PreisId = ?";
            
            statement = connection.prepareStatement(query);
            statement.setInt(1, preisId); 
            result = statement.executeQuery();
            connection.commit();
            
            int count = 0;
            while (result.next()) {
                speiseEintrag.setSpeiseId(result.getInt("speiseId"));
                speiseEintrag.setName(result.getString("name"));
                speiseEintrag.setBeschreibung(result.getString("beschreibung"));
                speiseEintrag.setTyp(result.getString("typ"));
                speiseEintrag.setImage(result.getString("image"));
                count ++;
            }
            if (count > 1){
                System.out.println("ERROR: Each preisId should correspond to one speise");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        return speiseEintrag;
    }
}
