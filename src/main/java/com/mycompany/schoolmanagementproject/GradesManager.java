package com.mycompany.schoolmanagementproject;

import java.util.ArrayList;

public class GradesManager {
    private GradesDAO gradesDAO;

    public GradesManager() {
        this.gradesDAO = new GradesDAO();
    }

    public boolean addGrade(Grades grade) {
        return gradesDAO.saveOrUpdateGrade(grade);
    }

    public boolean updateGrade(String studentId, String courseName, double newScore) {
        return gradesDAO.saveOrUpdateGrade(new Grades(studentId, courseName, newScore));
    }

    public boolean deleteGrade(String studentId, String courseName) {
        return gradesDAO.deleteGrade(studentId, courseName);
    }

    public ArrayList<Grades> getAllGrades() {
        return new ArrayList<>(gradesDAO.selectAllGrades());
    }

    public ArrayList<Grades> getGradesByStudentId(String studentId) {
        return new ArrayList<>(gradesDAO.getGradesByStudentId(studentId));
    }

    // Manual Bubble Sort on ArrayList<Grades> by Student ID
    public void sortGradesByStudentId(ArrayList<Grades> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getStudentId().compareToIgnoreCase(list.get(j + 1).getStudentId()) > 0) {
                    Grades temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public double calculateGWA(String studentId) {
        ArrayList<Grades> studentGrades = getGradesByStudentId(studentId);
        if (studentGrades.isEmpty()) return 0.0;

        double total = 0.0;
        for (Grades g : studentGrades) {
            total += g.getScore();
        }
        double avg = total / studentGrades.size();
        gradesDAO.updateStudentGWA(studentId, avg);
        return avg;
    }
}