package com.mycompany.schoolmanagementproject.model;

import java.util.ArrayList;
import java.util.Random;

public class Student {
    private String name;
    private String id;
    private double gwa;
    private ArrayList<String> enrolledCourses;

    // Constructor 1: Used when creating a BRAND NEW student
    public Student(String name) {
        this.name = name;
        Random random = new Random();
        int idnum = random.nextInt(1000, 9999);
        this.id = "2026-" + idnum + "-BN-0";
        this.gwa = 0.0;
        this.enrolledCourses = new ArrayList<>();
    }

    // Constructor 2: Used when loading an EXISTING student from the database
    public Student(String id, String name, double gwa) {
        this.id = id;
        this.name = name;
        this.gwa = gwa;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public double getGwa() {
        return gwa;
    }

    public void setGwa(double gwa) {
        this.gwa = gwa;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void enrollCourse(String courseName) {
        this.enrolledCourses.add(courseName);
    }
}