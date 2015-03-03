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
 * @author Sabah
 */
public class Preise extends DBConnect{
    
    private PreisEintrag meineEintrag;
    
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
