/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.herstell.grund;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Polichronis Tsolakidis
 */
public class GConnection {

    /**
     * Holt eine Datenbankverbindung.
     * @return SQL Connection oder null wenn Fehler.
     */
    protected  Connection getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app", "app");
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            System.out.println( ex.getStackTrace());
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
