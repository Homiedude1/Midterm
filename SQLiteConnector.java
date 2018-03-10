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
    public static Connection connect(){
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/etern/IdeaProjects/MidtermPersonB/src/main/java/edu/missouriwestern/blessing/CSC346/schedule.db";
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
