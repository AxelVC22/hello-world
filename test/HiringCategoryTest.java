import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import mx.fei.coilvicapp.logic.implementations.DAOException;
import mx.fei.coilvicapp.logic.hiringcategory.*;

public class HiringCategoryTest {
    
    private static final HiringCategory TEST_HIRING_CATEGORY = new HiringCategory();
    private static final HiringCategory AUX_TEST_HIRING_CATEGORY  = new HiringCategory();
    private static final HiringCategoryDAO HIRING_CATEGORY_DAO = new HiringCategoryDAO();
    private static final ArrayList<HiringCategory> TEST_HIRING_CATEGORIES = new ArrayList<>();
    
    @Before
    public void setUp() {
        try {
            TEST_HIRING_CATEGORY.setName("Docente T.C.");
            int idHiringCategory = HIRING_CATEGORY_DAO.registerHiringCategory(TEST_HIRING_CATEGORY);
            TEST_HIRING_CATEGORY.setIdhiringCategory(idHiringCategory);
            System.out.println(idHiringCategory);
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }         
    }
    
    @After
    public void tearDown() {
        try {
            HIRING_CATEGORY_DAO.deleteHiringCategory(TEST_HIRING_CATEGORY.getIdhiringCategory());
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception); 
        }
    }
    
    @Test
    public void testSuccessInsertHiringCategory() {
        HiringCategory hiringCategory = new HiringCategory("Investigador T.C.");
        int idHiringCategory = 0;
        
        try {            
            idHiringCategory = HIRING_CATEGORY_DAO.registerHiringCategory(hiringCategory);
            HIRING_CATEGORY_DAO.deleteHiringCategory(idHiringCategory);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }    
        Assert.assertTrue(idHiringCategory > 0);
    } 
    
    @Test
    public void testFailureInsertHiringCategoryByNameAlreadyRegistered() {
        HiringCategory hiringCategory = new HiringCategory(TEST_HIRING_CATEGORY.getName());
        int idHiringCategory = 0;
        
        try {            
            idHiringCategory = HIRING_CATEGORY_DAO.registerHiringCategory(hiringCategory);
            HIRING_CATEGORY_DAO.deleteHiringCategory(idHiringCategory);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        }    
        Assert.assertTrue(idHiringCategory > 0);
    } 
    
    @Test
    public void testSuccessUpdateHiringCategory() {    
        int result = 0;
        HiringCategory hiringCategory = new HiringCategory("Tecnico Academico T.C.");
        
        hiringCategory.setIdhiringCategory(TEST_HIRING_CATEGORY.getIdhiringCategory());
        try {            
            result = HIRING_CATEGORY_DAO.updateHiringCategoryVerification(hiringCategory);
        } catch (DAOException exception) {
            Logger.getLogger(HiringCategoryTest.class.getName()).log(Level.SEVERE, null, exception);
        } 
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testFailureUpdateHiringCategoryByAlreadyRegisteredName() {
        int result = 0;
        int idHiringCategory = 0;
        
        HiringCategory hiringCategory = new HiringCategory("Tecnico Academico T.C.");
        hiringCategory.setIdhiringCategory(TEST_HIRING_CATEGORY.getIdhiringCategory());
        try {            
            idHiringCategory = HIRING_CATEGORY_DAO.registerHiringCategory(new HiringCategory("Tecnico Academico T.C."));
            result = HIRING_CATEGORY_DAO.updateHiringCategoryVerification(hiringCategory);
        } catch (DAOException exception) {
            Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            try {
                HIRING_CATEGORY_DAO.deleteHiringCategory(idHiringCategory);
            } catch (DAOException exception) {
                Logger.getLogger(AcademicAreaTest.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        Assert.assertTrue(result > 0);
    }
    
    @Test 
    public void testSuccessDeleteHiringCategory() {        
        int result = 0;
        
        try {            
           result = HIRING_CATEGORY_DAO.deleteHiringCategory(TEST_HIRING_CATEGORY.getIdhiringCategory());
        } catch (DAOException exception) {
            Logger.getLogger(HiringCategoryTest.class.getName()).log(Level.SEVERE, null, exception);
        }      
        Assert.assertTrue(result > 0);
    }
    
    @Test 
    public void testFailureDeleteHiringCategoryByIdNotAvailable() {        
        int result = 0;
        
        try {            
           result = HIRING_CATEGORY_DAO.deleteHiringCategory(1);
        } catch (DAOException exception) {
            Logger.getLogger(HiringCategoryTest.class.getName()).log(Level.SEVERE, null, exception);
        }      
        Assert.assertTrue(result > 0);
    }
    
    @Test
    public void testSuccessGetHiringCategoryByName() {
        HiringCategory hiringCategory = new HiringCategory();
        try {
            hiringCategory = HIRING_CATEGORY_DAO.getHiringCategoryByName(TEST_HIRING_CATEGORY.getName());
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertEquals(TEST_HIRING_CATEGORY.getName(), hiringCategory.getName());
    }
    
    @Test
    public void testFailureGetHiringCategoryByNameNotAvailable() {
        HiringCategory hiringCategory = new HiringCategory();
        try {
            hiringCategory = HIRING_CATEGORY_DAO.getHiringCategoryByName("Investigador T.C.");
        } catch (DAOException exception) {
            Logger.getLogger(ProfessorTest.class.getName()).log(Level.SEVERE, null, exception);
        }
        Assert.assertEquals(TEST_HIRING_CATEGORY.getName(), hiringCategory.getName());
    }
    
}
