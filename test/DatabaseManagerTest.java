import mx.fei.coilvicapp.dataaccess.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
        
    }
        
    @Test
    public void testCloseConnectionSuccess() throws SQLException {
        System.out.println("closeConnection");
        DatabaseManager instance = new DatabaseManager();
        Connection result = instance.getConnection();
        Assert.assertNotNull(result);
        try {
            instance.closeConnection();
            Assert.assertTrue(result.isClosed());
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }        
    }
    
    @Test
    public void testGetConnectionSuccess() throws SQLException {
        System.out.println("getConnection");
        DatabaseManager instance = new DatabaseManager();
        Connection result = instance.getConnection();
        Assert.assertNotNull(result);
    }
}
