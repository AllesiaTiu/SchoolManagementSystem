package com.mycompany.schoolmanagementproject;

import com.mycompany.schoolmanagementproject.databaseAccess.DBConnection;
import com.mycompany.schoolmanagementproject.model.Grades;
import com.mycompany.schoolmanagementproject.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    // 1. Authenticate Student Login
    public Student authenticateStudent(String studentId, String password) {
        String sql = "SELECT * FROM students WHERE student_id = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId.trim());
            ps.setString(2, password.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getDouble("gwa")
                    );
                    return student;
                }
            }
        } catch (SQLException e) {
            System.err.println("UserDAO Error [authenticateStudent]: " + e.getMessage());
        }
        return null; // Invalid credentials
    }

    // 2. Fetch Grades for the logged-in student
    public ArrayList<Grades> getStudentGrades(String studentId) {
        ArrayList<Grades> list = new ArrayList<>();
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
            System.err.println("UserDAO Error [getStudentGrades]: " + e.getMessage());
        }
        return list;
    }

    // 3. Fetch Attendance records for the logged-in student
    public ArrayList<AttendanceRecord> getStudentAttendance(String studentId) {
        ArrayList<AttendanceRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_id = ? ORDER BY date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId.trim());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AttendanceRecord(
                        "", // Student name not needed here
                        rs.getString("student_id"),
                        rs.getString("course_name"),
                        rs.getString("date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("UserDAO Error [getStudentAttendance]: " + e.getMessage());
        }
        return list;
    }
}