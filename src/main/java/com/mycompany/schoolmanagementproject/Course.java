package com.mycompany.schoolmanagementproject;

public class Course {
    private String cID;
    private String cName;
    
    public Course(String cID, String cName) {
        this.cID = cID;
        this.cName = cName;
        
    }
    
    public String getcID() {
        return cID;
    }
    
    public String getcName() {
        return cName;
    }
    
    public void setcName(String cName) {
        this.cName = cName;
    }
}
