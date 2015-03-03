/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizza.CMModel;

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
 * @author IBB Teilnehmer
 */
public class DBConnect {

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Get a connection
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzalagondola", "root", "1234");

            conn.setAutoCommit(false);
            return conn;

            // with pool do the next, surround with the corrsponding try-catch
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("jdbc/LaGondola");
//            
//            Connection conn = ds.getConnection();
//            
//            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
