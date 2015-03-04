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
 * <h1>Controls the database part of the PreiseEintrag Model</h1>
 * The Preise class is used to do the actual connection to the database. It contains
 * the actual method needed to get all the Preis entries. Here the actual queries are executed.
 * 
 * @author Sabah Al-Sabea
 */
public class Preise extends DBConnect{
    /**
     * This method gets all the entries from the Preis table
     * 
     * @return List<PreisEintrag> This is a list of all the Preis entries
     */
    public List<PreisEintrag> getList() {
        List<PreisEintrag> preisList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

            connection = getConnection();
            if (connection == null) {
                return null;
            }
            
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM preis");
            
            while (result.next()){
                PreisEintrag currentPreisEintrag = new PreisEintrag();
                
                currentPreisEintrag.setPreisId(result.getInt("PreisId"));
                currentPreisEintrag.setSpeiseId(result.getInt("SpeiseId"));
                currentPreisEintrag.setGrosseId(result.getInt("GrosseId"));
                currentPreisEintrag.setPreis(result.getDouble("Preis"));
                preisList.add(currentPreisEintrag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        return preisList;
    }
}
