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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Polichronis Tsolakidis
 */
public class GConnection {

    /**
     * Holt eine Datenbankverbindung.
     * @return SQL Connection oder null wenn Fehler.
     */
    protected  Connection getConnection()  {
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Context ctx=new InitialContext();
            //Get a connection
            DataSource myds=(DataSource) ctx.lookup("jdbc/sample");
            Connection conn = myds.getConnection();//DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app", "app");
            //conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            System.out.println( ex.getStackTrace());
            //Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Eintrag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
