package com.mycompany.schoolmanagementproject;

import java.util.ArrayList;
import java.util.List;

public class GradesManager {
    
    private GradesDAO gradesDAO;

    public GradesManager() {
        this.gradesDAO = new GradesDAO();
    }

    // Add or update grade directly into MySQL
    public boolean addGrade(Grades grade) {
        return gradesDAO.saveOrUpdateGrade(grade);
    }

    public boolean updateGrade(String studentId, String courseName, double newScore) {
        return gradesDAO.saveOrUpdateGrade(new Grades(studentId, courseName, newScore));
    }

    public boolean deleteGrade(String studentId, String courseName) {
        return gradesDAO.deleteGrade(studentId, courseName);
    }

    public boolean deleteAllGradesForStudent(String studentId) {
        return gradesDAO.deleteAllGradesForStudent(studentId);
    }

    public List<Grades> getGradesByStudentId(String studentId) {
        return gradesDAO.getGradesByStudentId(studentId);
    }

    public List<Grades> getAllGrades() {
        return gradesDAO.selectAllGrades();
    }

    // Calculate General Weighted Average & save it to students table!
    public double calculateGWA(String studentId) {
        List<Grades> studentGrades = gradesDAO.getGradesByStudentId(studentId);
        if (studentGrades.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;
        for (Grades g : studentGrades) {
            totalScore += g.getScore();
        }
        double avg = totalScore / studentGrades.size();
        
        // Sync to students table in MySQL
        gradesDAO.updateStudentGWA(studentId, avg);
        return avg;
    }
}