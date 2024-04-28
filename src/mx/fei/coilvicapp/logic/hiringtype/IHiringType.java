package mx.fei.coilvicapp.logic.hiringtype;

import java.sql.SQLException;
import java.util.List;

public interface IHiringType {
    
    public int insertHiringType(HiringType hiringType) throws SQLException;
    public int updateHiringType(String newHiringType, String hiringTypeName) throws SQLException;
    public int deleteHiringType(String hiringTypeName) throws SQLException;
    public List<String> getHiringTypes();
    
}
