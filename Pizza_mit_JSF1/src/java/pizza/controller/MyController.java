/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import pizza.model.BestellItem;
import pizza.model.Bestellung;
import pizza.model.DBConnect;
import pizza.model.Kunde;

/**
 *
 * @author IBB Teilnehmer
 */
@Named
@SessionScoped
public class MyController extends DBConnect implements Serializable{
    private Kunde kunde;
    private Bestellung bestellung;
    private List<BestellItem> items;

    public MyController() {
        this.items = new ArrayList();
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }     
    
    public List<BestellItem> getItems() {
        return items;
    }

    public void setItems(List<BestellItem> items) {
        this.items = items;
    }        
}
