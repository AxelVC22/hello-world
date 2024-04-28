package mx.fei.coilvicapp.logic.region;

import java.sql.SQLException;
import java.util.List;

public interface IRegion {
    
    public int insertRegion(Region region) throws SQLException;
    public int updateRegion(String newRegion, String regionName) throws SQLException;
    public int deleteRegion(String regionName) throws SQLException;
    public List<String> getRegions();
            
}
