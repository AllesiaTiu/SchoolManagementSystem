package com.mycompany.schoolmanagementproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesDAO {

    // 1. ADD OR UPDATE GRADE (UPSERT)
    public boolean saveOrUpdateGrade(Grades grade) {
        String sql = "INSERT INTO grades (student_id, course_name, score) VALUES (?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE score = VALUES(score)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, grade.getStudentId().trim());
            ps.setString(2, grade.getCourseId().trim());
            ps.setDouble(3, grade.getScore());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [saveOrUpdateGrade]: " + e.getMessage());
            return false;
        }
    }

    // 2. DELETE SINGLE COURSE GRADE
    public boolean deleteGrade(String studentId, String courseName) {
        String sql = "DELETE FROM grades WHERE student_id = ? AND course_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId.trim());
            ps.setString(2, courseName.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [deleteGrade]: " + e.getMessage());
            return false;
        }
    }

    // 3. DELETE ALL GRADES FOR A STUDENT
    public boolean deleteAllGradesForStudent(String studentId) {
        String sql = "DELETE FROM grades WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [deleteAllGradesForStudent]: " + e.getMessage());
            return false;
        }
    }

    // 4. GET ALL GRADES FOR A SPECIFIC STUDENT
    public List<Grades> getGradesByStudentId(String studentId) {
        List<Grades> list = new ArrayList<>();
        String sql = "SELECT * FROM grades WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId.trim());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Grades(
                        rs.getString("student_id"),
                        rs.getString("course_name"),
                        rs.getDouble("score")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [getGradesByStudentId]: " + e.getMessage());
        }
        return list;
    }

    // 5. GET ALL GRADES (USED TO POPULATE TABLE ON LAUNCH)
    public List<Grades> selectAllGrades() {
        List<Grades> list = new ArrayList<>();
        String sql = "SELECT * FROM grades";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Grades(
                    rs.getString("student_id"),
                    rs.getString("course_name"),
                    rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [selectAllGrades]: " + e.getMessage());
        }
        return list;
    }

    // 6. UPDATE GWA IN THE STUDENTS TABLE
    public boolean updateStudentGWA(String studentId, double gwa) {
        String sql = "UPDATE students SET gwa = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, gwa);
            ps.setString(2, studentId.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("GradesDAO Error [updateStudentGWA]: " + e.getMessage());
            return false;
        }
    }
}