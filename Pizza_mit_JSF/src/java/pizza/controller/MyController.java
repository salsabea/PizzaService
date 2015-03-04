/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizza.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import pizza.model.Bestellung;
import pizza.model.DBConnect;
import pizza.model.Kunde;
import pizza.model.Speise;

/**
 *
 * @author IBB Teilnehmer
 */
@Named
@SessionScoped
public class MyController extends DBConnect implements Serializable {

    private Kunde kunde;
    private Bestellung bestellung;
    @Inject
    private SpeiseController speiseController;
    private List<Speise> bestellteSpeise;

    public MyController() {

        this.kunde = new Kunde();
        this.bestellung = new Bestellung();

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

    public List<Speise> getBestellteSpeise() {
        bestellteSpeise = speiseController.getMengeList();
        return bestellteSpeise;
    }

    public void setBestellteSpeise(List<Speise> bestellteSpeise) {
        this.bestellteSpeise = bestellteSpeise;
    }

    public void initBestellung() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        this.bestellung.setSession_id(req.getSession().getId());
        this.bestellung.setIp_address(req.getRemoteAddr());

        Double totalPreis = 0.0;
        List<Speise> tmp = speiseController.getMengeList();
        for (Speise s : speiseController.getMengeList()) {
            totalPreis += s.getPreis() * s.getMenge();
        }
        this.bestellung.setTotal_preis(totalPreis);
    }

    public boolean storeKunde() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        try {
            con = getConnection();
            if (con == null) {
                return false;
            }
            stm = con.prepareStatement("INSERT INTO kunde (vorname, nachname, telefon, strasse, haus_nr, stadt, plz) "
                    + "VALUES(?,?,?,?,?,?,?)", stm.RETURN_GENERATED_KEYS);
            stm.setString(1, this.kunde.getVorname().trim());
            stm.setString(2, this.kunde.getNachname().trim());
            stm.setString(3, this.kunde.getTelefon().trim());
            stm.setString(4, this.kunde.getStrasse().trim());
            stm.setString(5, this.kunde.getHaus_nr().trim());
            stm.setString(6, this.kunde.getStadt().trim());
            stm.setInt(7, this.kunde.getPlz());

            int rows = stm.executeUpdate();
            System.out.println("rows stored in kunde are " + rows);

            if (rows == 0) {
                throw new SQLException("Kein Kunde gespeischert");
            }

            try (ResultSet generatedKey = stm.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    this.kunde.setKunde_id(generatedKey.getInt(1));
                } else {
                    throw new SQLException("Kein KundeId");
                }
            }

            con.commit();
            stored = rows == 1;

        } catch (SQLException ex) {
            Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stored;
    }

    public boolean storeBestellung() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        initBestellung();

        try {

            con = getConnection();
            if (con == null) {
                return false;
            }
            stm = con.prepareStatement("INSERT INTO bestellung (kunde_id, ip_address, session_id, total_preis, datum) "
                    + "VALUES(?,?,?,?,CURRENT_TIMESTAMP)", stm.RETURN_GENERATED_KEYS);
            stm.setInt(1, this.kunde.getKunde_id());
            stm.setString(2, this.bestellung.getIp_address().trim());
            stm.setString(3, this.bestellung.getSession_id().trim());
            stm.setDouble(4, this.bestellung.getTotal_preis());

            int rows = stm.executeUpdate();
            System.out.println("rows stored in bestellung are " + rows);

            if (rows == 0) {
                throw new SQLException("Kein Bestellung wird gespeischert");
            }

            try (ResultSet generatedKey = stm.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    this.bestellung.setBestellung_id(generatedKey.getInt(1));
                } else {
                    throw new SQLException("Kein BestellungsId");
                }
            }

            con.commit();
            stored = rows == 1;

        } catch (SQLException ex) {
            Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stored;
    }

    public boolean storeItems() {
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;

        try {
            
            con = getConnection();
            if (con == null) {
                return false;
            }
            
            stm = con.prepareStatement("INSERT INTO bestell_item (bestellung_id, speise_id, menge) "
                    + "VALUES(?,?,?)", stm.RETURN_GENERATED_KEYS);
            int rows = 0;
            for (Speise s : bestellteSpeise) {
                
                stm.setInt(1, this.bestellung.getBestellung_id());
                stm.setInt(2, s.getSpeise_id());
                stm.setInt(3, s.getMenge());
                
                rows += stm.executeUpdate();
            
            }
            System.out.println("rows stored in bestell_item are " + rows);

            con.commit();
            stored = rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stored;
    }

    public void bestellen() {
        storeKunde();
        System.out.println("the generated kunde_id is  " + this.kunde.getKunde_id());
        storeBestellung(); 

        System.out.println("the gesamt preis is  " + this.bestellung.getTotal_preis());
        storeItems();
        // return to the rechnung seite
    }
}
