package com.mycompany.schoolmanagementproject;



import java.util.Random;
public class Student {
    
    Random random = new Random();
    String name;
    static private int idcount;
    int idnum;

    public Student(String name) {
        this.name = name;
        idcount = random.nextInt(1000, 9999);
        idnum = idcount;
        
        
    }
    public String getName(){
        return name;
    }
    public String getID(){
        String idtxt = Integer.toString(idnum);
        String idstr = "2026" + "-" + idtxt + "-" + "BN" + "-" + "0";
   
        return idstr;
    }
    public void setName(String name){
        this.name = name;
        
    }
    
    
    
    
    
}
