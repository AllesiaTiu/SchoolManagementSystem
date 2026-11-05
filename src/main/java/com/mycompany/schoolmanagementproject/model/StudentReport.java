package com.mycompany.schoolmanagementproject.model;

public class StudentReport {
    private String studentId;
    private String name;
    private String program;
    private double gwa;
    private String status;

    public StudentReport(String studentId, String name, String program, double gwa) {
        this.studentId = studentId;
        this.name = name;
        this.program = program;
        this.gwa = gwa;
        
        // Automated status evaluation rule (Standard college scale)
        if (gwa <= 0.0) {
            this.status = "No Grade";
        } else if (gwa >= 90.0 || (gwa <= 1.75 && gwa >= 1.0)) {
            this.status = "Dean's Lister";
        } else if (gwa >= 75.0 || (gwa <= 3.0 && gwa > 1.75)) {
            this.status = "Passed";
        } else {
            this.status = "Warning / Failed";
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getGwa() {
        return gwa;
    }

    public void setGwa(double gwa) {
        this.gwa = gwa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}