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
}
