/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.model;

import java.io.Serializable;


/**
 *
 * @author IBB Teilnehmer
 */
public class BestellItem implements Serializable{
    private Integer item_id;
    private Integer bestellung_id;
    private Integer speise_id;
    private Integer menge;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getBestellung_id() {
        return bestellung_id;
    }

    public void setBestellung_id(Integer bestellung_id) {
        this.bestellung_id = bestellung_id;
    }

    public Integer getSpeise_id() {
        return speise_id;
    }

    public void setSpeise_id(Integer speise_id) {
        this.speise_id = speise_id;
    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }        
}
