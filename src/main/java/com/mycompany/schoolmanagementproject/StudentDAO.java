package com.mycompany.schoolmanagementproject;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // 1. INSERT Student and their enrolled courses
    public boolean insertStudent(Student student) {
        String sqlStudent = "INSERT INTO students (student_id, name, gwa) VALUES (?, ?, ?)";
        String sqlEnrollment = "INSERT INTO enrollments (student_id, course_name) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Insert into 'students' table
            try (PreparedStatement psStudent = conn.prepareStatement(sqlStudent)) {
                psStudent.setString(1, student.getID());
                psStudent.setString(2, student.getName());
                psStudent.setDouble(3, 0.0);
                psStudent.executeUpdate();
            }

            // Insert into 'enrollments' table
            if (student.getEnrolledCourses() != null && !student.getEnrolledCourses().isEmpty()) {
                try (PreparedStatement psEnroll = conn.prepareStatement(sqlEnrollment)) {
                    for (String courseName : student.getEnrolledCourses()) {
                        psEnroll.setString(1, student.getID());
                        psEnroll.setString(2, courseName);
                        psEnroll.executeUpdate();
                    }
                }
            }

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            System.err.println("DAO Error [insertStudent]: " + e.getMessage());
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    // 2. GET ALL Students from database
    public ArrayList<Student> selectAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT student_id, name FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("name");
                
                Student s = new Student(id, name);
                // Attach the enrolled courses for this student
                s.setEnrolledCourses(selectCoursesByStudentId(id));
                list.add(s);
            }
        } catch (SQLException e) {
            System.err.println("DAO Error [selectAllStudents]: " + e.getMessage());
        }
        return list;
    }

    // Helper: Fetch enrolled courses for one student
    public ArrayList<String> selectCoursesByStudentId(String studentId) {
        ArrayList<String> courses = new ArrayList<>();
        String sql = "SELECT course_name FROM enrollments WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(rs.getString("course_name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("DAO Error [selectCoursesByStudentId]: " + e.getMessage());
        }
        return courses;
    }

    // 3. UPDATE Student Name
    public boolean updateStudentName(String studentId, String newName) {
        String sql = "UPDATE students SET name = ? WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("DAO Error [updateStudentName]: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE Student
    public boolean deleteStudentById(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("DAO Error [deleteStudentById]: " + e.getMessage());
            return false;
        }
    }

    // 5. GET All Available Courses (from courses table with fallback)
    public ArrayList<String> selectAvailableCourses() {
        ArrayList<String> courses = new ArrayList<>();
        String sql = "SELECT course_name FROM courses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                courses.add(rs.getString("course_name"));
            }
        } catch (SQLException e) {
            System.err.println("DAO Error [selectAvailableCourses]: " + e.getMessage());
        }

        // Fallback default list if no courses added in database yet
        if (courses.isEmpty()) {
            courses.add("Programming 3");
            courses.add("Data Structures");
            courses.add("PathFit");
            courses.add("Reading in Visual Arts");
            courses.add("Computer Networks");
            courses.add("Operating Systems");
        }
        return courses;
    }
}