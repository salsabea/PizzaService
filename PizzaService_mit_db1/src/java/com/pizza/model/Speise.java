/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.model;

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
 * @author Sabah Al-Sabea
 */
public class Speise extends DBConnect {

    private SpeiseEintrag meineEintrag;

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
            result = statement.executeQuery("SELECT * FROM speise");
            
            while (result.next()){
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
}
