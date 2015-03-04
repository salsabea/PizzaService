/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.model;

/**
 *
 * @author IBB Teilnehmer
 */
public class Speise {
    private Integer speise_id;
    private String speise_name;
    private Double preis;
    private Integer menge;

    public Integer getSpeise_id() {
        return speise_id;
    }

    public void setSpeise_id(Integer speise_id) {
        this.speise_id = speise_id;
    }

    public String getSpeise_name() {
        return speise_name;
    }

    public void setSpeise_name(String speise_name) {
        this.speise_name = speise_name;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }
    
}
