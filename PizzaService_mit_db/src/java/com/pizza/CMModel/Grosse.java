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
 * <h1>Controls the database part of the GrosseEintrg Model</h1>
 * The Grosse class is used to do the actual connection to the database. It contains
 * a set of methods needed to get Grosse entries. Here the actual queries are executed.
 * 
 * @author Sabah Al-Sabea
 */

public class Grosse extends DBConnect{
    /**
     * This method gets all the entries from the Grosse table
     * 
     * @return List<GrosseEintrag> This is a list of all the Grosse entries
     */
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
    
    /**
     * This method gets all the entries from the Grosse table that have a specific
     * speiseId. Ex. a speise X that has speiseID id_X can have more than one 
     * size i.e. Grosse.
     * 
     * @param speiseId This is the ID of the speise whose grosse entry we are looking for
     * @return List<GrosseEintrag> This is a list of Grosse entries with SpeiseId = {@code speiseId}
     */
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
    
    /**
     * This method gets the specific Grosse entry from the Grosse table that have a 
     * certain preisId.
     * 
     * @param preisId This is the preis ID whose grosse entry we are looking for
     * @return GrosseEintrag This is the Grosse entry corresponding to a certain {@code preisId}
     */
    public GrosseEintrag getGrosseFromPreisId(Integer preisId){
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
                    + "                     WHERE preis.PreisId = " + preisId);
            
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
