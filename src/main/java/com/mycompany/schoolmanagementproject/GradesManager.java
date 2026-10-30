/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementproject;


/**
 *
 * @author Angel Shane
 */
import java.util.ArrayList;
import java.util.List;

public class GradesManager {
    // In-memory storage (replace or sync with DB/File if your project uses one)
    private List<Grades> gradeList = new ArrayList<>();

    // Add a new grade (enforces duplicate prevention)
    public boolean addGrade(Grades grade) {
        if (gradeExists(grade.getStudentId(), grade.getCourseId())) {
            return false; // Duplicate exists
        }
        gradeList.add(grade);
        return true;
    }

    // Update an existing grade
    public boolean updateGrade(String studentId, String courseId, double newScore) {
        for (Grades g : gradeList) {
            if (g.getStudentId().equalsIgnoreCase(studentId) && 
                g.getCourseId().equalsIgnoreCase(courseId)) {
                g.setScore(newScore);
                return true;
            }
        }
        return false;
    }

    // Delete a grade
    public boolean deleteGrade(String studentId, String courseId) {
        return gradeList.removeIf(g -> 
            g.getStudentId().equalsIgnoreCase(studentId) && 
            g.getCourseId().equalsIgnoreCase(courseId));
    }

    // Lookup: Filter grades by Student ID (Used by UI & TranscriptBranch)
    public List<Grades> getGradesByStudentId(String studentId) {
        List<Grades> result = new ArrayList<>();
        for (Grades g : gradeList) {
            if (g.getStudentId().equalsIgnoreCase(studentId)) {
                result.add(g);
            }
        }
        return result;
    }

    // Lookup: Filter grades by Course ID (Used by Reports)
    public List<Grades> getGradesByCourseId(String courseId) {
        List<Grades> result = new ArrayList<>();
        for (Grades g : gradeList) {
            if (g.getCourseId().equalsIgnoreCase(courseId)) {
                result.add(g);
            }
        }
        return result;
    }

    // Check duplicate
    public boolean gradeExists(String studentId, String courseId) {
        for (Grades g : gradeList) {
            if (g.getStudentId().equalsIgnoreCase(studentId) && 
                g.getCourseId().equalsIgnoreCase(courseId)) {
                return true;
            }
        }
        return false;
    }

    // Calculate General Weighted Average (Simple or Weighted)
    public double calculateGWA(String studentId) {
        List<Grades> studentGrades = getGradesByStudentId(studentId);
        if (studentGrades.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;
        for (Grades g : studentGrades) {
            totalScore += g.getScore();
        }
        return totalScore / studentGrades.size();
    }

    public List<Grades> getAllGrades() {
        return new ArrayList<>(gradeList);
    }
}

