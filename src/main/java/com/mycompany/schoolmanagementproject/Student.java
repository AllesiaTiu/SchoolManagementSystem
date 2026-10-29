package com.mycompany.schoolmanagementproject;



import java.util.Random;
public class Student {
    
    Random random = new Random();
    String name;
    static private int idcount;
    int idnum;
    static private int studentCount;

    public Student(String name) {
        this.name = name;
        idcount = random.nextInt(1000, 9999);
        idnum = idcount;
        studentCount++;
        
        
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
    static public String getStudentCount(){
        String studentCountStr = Integer.toString(studentCount);
        return studentCountStr;
    }
    
    
    
    
    
}
