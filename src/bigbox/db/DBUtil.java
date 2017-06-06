package bigbox.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection()  {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://localhost:3306/bigbox";
                String username = "bb_user";
                String password = "sesame";

                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
             
                return connection;
            } catch (SQLException e) {
            
            }            
        }
        System.out.print("b");
        return connection;
    }
    
    public static synchronized void closeConnection()  {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
               
            } finally {
                connection = null;                
            }
        }
    }
}