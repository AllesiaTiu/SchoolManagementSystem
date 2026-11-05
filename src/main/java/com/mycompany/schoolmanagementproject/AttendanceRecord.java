package com.mycompany.schoolmanagementproject;

public class AttendanceRecord {
    private String studentName;
    private String studentId;
    private String courseName;
    private String date;
    private String status; // "Present", "Late", "Absent"

    public AttendanceRecord(String studentName, String studentId, String courseName, String date, String status) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.date = date;
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}