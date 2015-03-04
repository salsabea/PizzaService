/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.herstell.grund;
// POJO
import info.herstell.grund.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Plain
 * Old
 * Java
 * Object
 * @author Polichronis Tsolakidis
 */
public class Eintrag extends GConnection {

    private String vorname="";
    private String nachname="walther";
    private String email="fritz.walther@gmx.de";
    private String web="www.walther.de";
    private Integer age;
    private String region="Bayern";
    private String kommentar="Test";
    private String sessionid="1111";
    private String inet="192";
    private Date datum;
    private Date entrydate;
   
    /**
     * @return the vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @param vorname the vorname to set
     */
    public void setVorname(String vorname) {
        
        this.vorname = vorname;
    }

    /**
     * @return the nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * @param nachname the nachname to set
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge( Integer age) {
        this.age = age;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the kommentar
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * @param kommentar the kommentar to set
     */
    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    /**
     * @return the sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * @param sessionid the sessionid to set
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    /**
     * @return the inet
     */
    public String getInet() {
        return inet;
    }

    /**
     * @param inet the inet to set
     */
    public void setInet(String inet) {
        this.inet = inet;
    }

    /**
     * @return the datum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     * Speichert den Datensatz.
     * @return true Wenn der Datensatz gespeichert wurde.
     */
    public boolean store() {

		//Kommentar
        
        Connection con = null;
        PreparedStatement stm = null;
        boolean stored = false;
        try {
            System.out.println("Aus der Store" + this.getAge());
            con = getConnection();
            if(con == null) return false;
            stm = con.prepareStatement("INSERT INTO gaestebuch (vorname,nachname,email,web,age,region,kommentar,sessionid,inet,datum) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
            stm.setString(1, this.vorname.trim());
            stm.setString(2, this.nachname.trim());
            stm.setString(3, this.email.trim());
            stm.setString(4, this.web.trim());
            stm.setInt(5, this.getAge() != null ? getAge() : 0);
            stm.setString(6, this.getRegion());
            stm.setString(7, this.getKommentar());
            stm.setString(8, this.getSessionid());
            stm.setString(9, this.getInet());
            
            int rows = stm.executeUpdate();
            con.commit();
            stored = rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
            stored = false;
        } finally {
            try { if( stm != null) stm.close(); } catch(Exception e) {}
            try { if( con != null) con.close(); } catch(Exception e) {}
        }
        return stored;
    }
}
