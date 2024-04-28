package mx.fei.coilvicapp.logic.student;

import java.sql.SQLException;
import java.util.List;

public interface IStudent {
    
    public int insertStudent(Student student) throws SQLException;
    public List<String> getStudentByEmail(String email);
    
}