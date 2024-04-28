package mx.fei.coilvicapp.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private Connection connection;
    private final String databaseName = "jdbc:mysql://127.0.0.1/coilvicdb";
    private final String databaseUser = "root";
    private final String databaseUserPassword = "Onepiece22";
    
    private void connect() throws SQLException {
        connection = DriverManager.getConnection(databaseName, databaseUser, databaseUserPassword);
    }
    
    public boolean closeConnection() {
        boolean isClosed = false;
        
        try {
            if (connection != null) {
                connection.close();
            }
            isClosed = true;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return isClosed;
    }
            
    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }
}
