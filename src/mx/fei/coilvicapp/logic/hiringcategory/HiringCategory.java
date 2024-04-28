package mx.fei.coilvicapp.logic.hiringcategory;

public class HiringCategory {
    
    private int idhiringCategory = 0;
    private String name;
    
    public HiringCategory() {
        
    }
    
    public HiringCategory(String name) {
        this.name = name;
    }
    
    public int getIdhiringCategory () {
        return idhiringCategory;
    }
    
    public void setIdhiringCategory(int idhiringCategory) {
        this.idhiringCategory = idhiringCategory;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }  
    
}
