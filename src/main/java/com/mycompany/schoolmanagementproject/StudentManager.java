package com.mycompany.schoolmanagementproject;

import java.sql.*;
import java.util.ArrayList;

public class StudentManager {

    // 1. ADD Student and their Enrolled Courses to Database
    public static boolean addStudent(Student student) {
        String insertStudent = "INSERT INTO students (student_id, student_name) VALUES (?, ?)";
        String insertEnrollment = "INSERT INTO enrollments (student_id, course_name) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Transaction

            try (PreparedStatement psStudent = conn.prepareStatement(insertStudent)) {
                psStudent.setString(1, student.getID());
                psStudent.setString(2, student.getName());
                psStudent.executeUpdate();
            }

            try (PreparedStatement psEnroll = conn.prepareStatement(insertEnrollment)) {
                for (String course : student.getEnrolledCourses()) {
                    psEnroll.setString(1, student.getID());
                    psEnroll.setString(2, course);
                    psEnroll.executeUpdate();
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. GET ALL Students from Database
    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(rs.getString("student_id"), rs.getString("student_name"));
                s.setEnrolledCourses(getEnrolledCourses(s.getID()));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Helper: Get enrolled courses for one student
    public static ArrayList<String> getEnrolledCourses(String studentId) {
        ArrayList<String> courses = new ArrayList<>();
        String sql = "SELECT course_name FROM enrollments WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(rs.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // 3. UPDATE Student Name
    public static boolean updateStudent(String studentId, String newName) {
        String sql = "UPDATE students SET student_name = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. DELETE Student
    public static boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. GET All Available Courses (so StudentBranch doesn't hardcode them!)
    public static ArrayList<String> getAvailableCourses() {
        ArrayList<String> courses = new ArrayList<>();
        String sql = "SELECT course_name FROM courses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(rs.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fallback default list if database table is currently empty
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