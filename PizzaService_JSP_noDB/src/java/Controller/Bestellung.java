/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Kunde;
import Model.Speise;
import java.util.Map;

/**
 *
 * @author IBB Teilnehmer
 */
public class Bestellung {
    private Kunde kunde;
    private Map<Speise,Integer> bestellList;

    public Bestellung() {
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Map<Speise, Integer> getBestellList() {
        return bestellList;
    }

    public void setBestellList(Map<Speise, Integer> bestellList) {
        this.bestellList = bestellList;
    }

    
    
}
