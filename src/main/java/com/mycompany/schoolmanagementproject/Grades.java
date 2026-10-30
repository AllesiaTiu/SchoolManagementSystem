/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementproject;

/**
 *
 * @author Angel Shane
 */
public class Grades {
  private String studentId;
  private String courseId;
  private double score;
  
  
  public Grades(String studentId, String courseId, double score) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.score = score;  
  }
    public String getStudentId(){
        return studentId;    
    }
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }
public String getCourseId(){
        return courseId;    
    }
    public void setcourseId(String courseId){
        this.courseId = courseId;
    }
    public double getScore(){
        return score;    
    }
    public void setScoreId(double score){
        this.score = score;
}
}