package com.mycompany.schoolmanagementproject.databaseAccess;

import com.mycompany.schoolmanagementproject.model.Student;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

  
    public boolean insertStudent(Student student) {
        String sqlStudent = "INSERT INTO students (student_id, name, gwa) VALUES (?, ?, ?)";
        String sqlEnrollment = "INSERT INTO enrollments (student_id, course_name) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); 

          
            try (PreparedStatement psStudent = conn.prepareStatement(sqlStudent)) {
                psStudent.setString(1, student.getID());
                psStudent.setString(2, student.getName());
                psStudent.setDouble(3, student.getGwa());
                psStudent.executeUpdate();
            }

            if (student.getEnrolledCourses() != null && !student.getEnrolledCourses().isEmpty()) {
                try (PreparedStatement psEnroll = conn.prepareStatement(sqlEnrollment)) {
                    for (String courseName : student.getEnrolledCourses()) {
                        psEnroll.setString(1, student.getID());
                        psEnroll.setString(2, courseName);
                        psEnroll.executeUpdate();
                    }
                }
            }

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            System.err.println("Database Insert Error: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public ArrayList<Student> selectAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT student_id, name, gwa FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("name");
                double gwa = rs.getDouble("gwa");
                
                Student s = new Student(id, name, gwa);
                s.setEnrolledCourses(selectCoursesByStudentId(id));
                list.add(s);
            }
        } catch (SQLException e) {
            System.err.println("DAO Error [selectAllStudents]: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


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
            e.printStackTrace();
        }
        return courses;
    }


    public boolean updateStudentName(String studentId, String newName) {
        String sql = "UPDATE students SET name = ? WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("DAO Error [updateStudentName]: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean deleteStudentById(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("DAO Error [deleteStudentById]: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


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
            e.printStackTrace();
        }


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