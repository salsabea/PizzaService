/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author IBB Teilnehmer
 */

public class Bestellung implements Serializable{
    private Integer bestellung_id;
    private Integer kunde_id;
    private String ip_address;
    private String session_id;
    private Double total_preis;
    private Date datum;

    public Integer getBestellung_id() {
        return bestellung_id;
    }

    public void setBestellung_id(Integer bestellung_id) {
        this.bestellung_id = bestellung_id;
    }

    public Integer getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(Integer kunde_id) {
        this.kunde_id = kunde_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Double getTotal_preis() {
        return total_preis;
    }

    public void setTotal_preis(Double total_preis) {
        this.total_preis = total_preis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }          
}
