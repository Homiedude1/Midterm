package edu.missouriwestern.blessing.CSC346;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteConnector {
    /**
     * Connect to a sample database
     */
    public static java.sql.Connection connect(String loc){
        java.sql.Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + loc;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection successful");
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
