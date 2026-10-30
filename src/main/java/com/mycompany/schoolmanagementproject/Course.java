package com.mycompany.schoolmanagementproject;

public class Course {
    private String cID;
    private String cName;
    
    //para maaccess ng global variable at macall 
    public Course(String cID, String cName) {
        this.cID = cID;
        this.cName = cName;
        
    }
    //returns course ID
    public String getcID() {
        return cID;
    }
    //returns course Name
    public String getcName() {
        return cName;
    }
    
    public void setcName(String cName) {
        this.cName = cName;
    }
    
    public void setcID(String cID) {
        this.cID = cID;
    }
}
