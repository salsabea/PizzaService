/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

//import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import pizza.model.BestellItem;
import pizza.model.DBConnect;
import pizza.model.Speise;

/**
 *
 * @author IBB Teilnehmer
 */
@Named
@SessionScoped
public class SpeiseController  extends DBConnect implements Serializable{
    private Speise speise;
    private List<Speise> speiseKarte;
    private List<Integer> mengeList;
    @Inject
    MyController myController;

    public SpeiseController() {
        this.speiseKarte = new ArrayList<>();
        this.init(); // initialize speiseKarte
        // create mengeList of length = highest speise_id and fill it with zeros
        this.mengeList = new ArrayList(Collections.nCopies(speiseKarte.get(speiseKarte.size()-1).getSpeise_id(), 0));
    }

    public Speise getSpeise() {
        return speise;
    }

    public void setSpeise(Speise speise) {
        this.speise = speise;
    }
    
    public void init() {
        List<Speise> speiseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        System.out.println("fetching the data from speise table");

        try {
            connection = getConnection();

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM speise");
            
            while (result.next()) {
                Speise currentSpeise = new Speise();

                currentSpeise.setSpeise_id(result.getInt("speise_id"));
                currentSpeise.setSpeise_name(result.getString("speise_name"));
                currentSpeise.setPreis(result.getDouble("preis"));
                speiseList.add(currentSpeise);      
                //this.mengeList.add(currentSpeise.getSpeise_id(), 0);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Speise.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.speiseKarte = speiseList;
    }

    public List<Speise> getSpeiseKarte() {
        
        return speiseKarte;
    } 

    public List<Integer> getMengeList() {
        return mengeList;
    }

    public void setMengeList(List<Integer> mengeList) {
        this.mengeList = mengeList;
    }
    
    public void addMenge(Integer index){
// here I want to add the value to the list at index speise_id        
//this.mengeList.add(index);
    }
    
    public String createItems() {
       for (Speise tmp : this.speiseKarte) {
           if (this.getMengeList().get(tmp.getSpeise_id()) > 0) {
               BestellItem bestellItem = new BestellItem();
               bestellItem.setMenge(tmp.getMenge());
               bestellItem.setSpeise_id(tmp.getSpeise_id());
               
               myController.getItems().add(bestellItem);               
           }
       }
       return "kunde";
   }
}
