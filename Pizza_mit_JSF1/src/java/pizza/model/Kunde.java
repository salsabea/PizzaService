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
public class Kunde {
    private Integer kunde_id;
    private String vorname;
    private String nachname;
    private String telefon;
    private String strasse;
    private String haus_nr;
    private String stadt;
    private Integer plz;   

    public Integer getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(Integer kunde_id) {
        this.kunde_id = kunde_id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHaus_nr() {
        return haus_nr;
    }

    public void setHaus_nr(String haus_nr) {
        this.haus_nr = haus_nr;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    
}
