package com.mycompany.schoolmanagementproject.model;

public class TranscriptRecord {
    private int no;
    private String subjectCode;
    private String subjectTitle;
    private int units;
    private double grade;
    private String remarks;

    public TranscriptRecord(int no, String subjectCode, String subjectTitle, int units, double grade) {
        this.no = no;
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
        this.units = units;
        this.grade = grade;
        
        // Smart Remarks:
        if (grade <= 0.0) {
            this.remarks = "In Progress";
        } else if (grade >= 75.0 || (grade <= 3.0 && grade >= 1.0)) {
            this.remarks = "Passed";
        } else {
            this.remarks = "Failed";
        }
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}