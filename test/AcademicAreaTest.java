import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import mx.fei.coilvicapp.logic.academicarea.*;
import mx.fei.coilvicapp.logic.implementations.DAOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class AcademicAreaTest {
    
    private static final AcademicArea TEST_ACADEMIC_AREA = new AcademicArea();
    private static final AcademicAreaDAO ACADEMIC_AREA_DAO = new AcademicAreaDAO();
    
    @Before
    public void setUp() {
        TEST_ACADEMIC_AREA.setName("Economico-Administrativo");
        try {
            int idTestAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(TEST_ACADEMIC_AREA);
            TEST_ACADEMIC_AREA.setIdAreaAcademica(idTestAcademicArea);
            System.out.println(idTestAcademicArea);
        } catch(DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @After
    public void tearDown() {
        try{
            ACADEMIC_AREA_DAO.deleteAcademicArea(TEST_ACADEMIC_AREA.getIdAreaAcademica());
        } catch(DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @Test
    public void testSuccessInsertAcademicArea() {
        AcademicArea academicArea = new AcademicArea("Ciencias de la salud");
        int idTestAcademicArea = 0;
        
        try {            
            idTestAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(academicArea);
            ACADEMIC_AREA_DAO.deleteAcademicArea(idTestAcademicArea);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }    
        Assert.assertTrue(idTestAcademicArea > 0);
    } 
        
    @Test
    public void testFailureInsertAcademicAreaByNameAlreadyRegistered() {
        int idTestAcademicArea = 0;
        AcademicArea academicArea = new AcademicArea(TEST_ACADEMIC_AREA.getName());
        
        try {            
            idTestAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(academicArea);
            ACADEMIC_AREA_DAO.deleteAcademicArea(idTestAcademicArea);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }    
        Assert.assertTrue(idTestAcademicArea > 0);
    } 

    @Test
    public void testSuccesUpdateAcademicArea() {
        int result = 0;        
        AcademicArea academicArea = new AcademicArea("Artes plasticas");
        
        academicArea.setIdAreaAcademica(TEST_ACADEMIC_AREA.getIdAreaAcademica());
        try {            
            result = ACADEMIC_AREA_DAO.updateAcademicAreaVerification(academicArea);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        } 
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testFailureUpdateAcademicAreaByAlreadyRegisteredName() {
        int result = 0;
        int idTestAcademicArea = 0;
        
        AcademicArea academicArea = new AcademicArea("Tecnica");
        academicArea.setIdAreaAcademica(TEST_ACADEMIC_AREA.getIdAreaAcademica());
        try {            
            idTestAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(new AcademicArea("Tecnica"));
            result = ACADEMIC_AREA_DAO.updateAcademicAreaVerification(academicArea);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            try {
                ACADEMIC_AREA_DAO.deleteAcademicArea(idTestAcademicArea);
            } catch(DAOException exception) {
                Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testSuccessDeleteAcademicArea() {
        int result = 0;
        
        try {
            result = ACADEMIC_AREA_DAO.deleteAcademicArea(TEST_ACADEMIC_AREA.getIdAreaAcademica());
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testFailureDeleteAcademicAreaByIdNotAvailable() {
        int result = 0;
        
        try {
            result = ACADEMIC_AREA_DAO.deleteAcademicArea(1);
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testSuccessGetAcademicAreaByName() {
        AcademicArea academicArea = new AcademicArea();
        try {
            academicArea = ACADEMIC_AREA_DAO.getAcademicAreaByName(TEST_ACADEMIC_AREA.getName());            
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertEquals(TEST_ACADEMIC_AREA.getName(), academicArea.getName());
    }
    
    @Test
    public void testFailureGetAcademicAreaByNameNotAvailable() {
        AcademicArea academicArea = new AcademicArea();
        try {
            academicArea = ACADEMIC_AREA_DAO.getAcademicAreaByName("Artes plasticas");            
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertEquals(TEST_ACADEMIC_AREA.getName(), academicArea.getName());
    }
    
    @Test 
    public void testGetAcademicAreas() {
        ArrayList<AcademicArea> expectedAcademicAreas = new ArrayList<>();
        ArrayList<AcademicArea> actualAcademicAreas = new ArrayList<>();
        expectedAcademicAreas = initializeAcademicAreaArray();
        try {
            actualAcademicAreas = ACADEMIC_AREA_DAO.getAcademicAreas();
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception); 
        }
        Assert.assertArrayEquals(expectedAcademicAreas.toArray(), actualAcademicAreas.toArray());
    }
    
    public ArrayList<AcademicArea> initializeAcademicAreaArray() {
        ArrayList<AcademicArea> academicAreas = new ArrayList<>();
        
        academicAreas.add(TEST_ACADEMIC_AREA);
        AcademicArea academicAreaAux1 = new AcademicArea("Humanidades");
        AcademicArea academicAreaAux2 = new AcademicArea("Ciencias de la salud");
        try {
            int idAcademicArea = 0;
            idAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(academicAreaAux1);
            academicAreaAux1.setIdAreaAcademica(idAcademicArea);
            academicAreas.add(academicAreaAux1);
            idAcademicArea = ACADEMIC_AREA_DAO.registerAcademicArea(academicAreaAux2);
            academicAreaAux2.setIdAreaAcademica(idAcademicArea);
            academicAreas.add(academicAreaAux2);
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception); 
        }      
        return academicAreas;
    }
    
}
