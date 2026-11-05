package com.mycompany.schoolmanagementproject.databaseAccess;

import com.mycompany.schoolmanagementproject.model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {


    public boolean insertCourse(Course course) {
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getcID().trim());
            ps.setString(2, course.getcName().trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [insertCourse]: " + e.getMessage());
            return false;
        }
    }

    
    public List<Course> selectAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Course(rs.getString("course_id"), rs.getString("course_name")));
            }
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [selectAllCourses]: " + e.getMessage());
        }
        return list;
    }


    public boolean updateCourse(String oldId, String newId, String newName) {
        String sql = "UPDATE courses SET course_id = ?, course_name = ? WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newId.trim());
            ps.setString(2, newName.trim());
            ps.setString(3, oldId.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [updateCourse]: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteCourse(String cID) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cID.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [deleteCourse]: " + e.getMessage());
            return false;
        }
    }


    public boolean courseExists(String cID) {
        String sql = "SELECT 1 FROM courses WHERE LOWER(course_id) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cID.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [courseExists]: " + e.getMessage());
            return false;
        }
    }

 
    public int getEnrolledCount(String courseName) {
        String sql = "SELECT COUNT(*) AS total FROM enrollments WHERE LOWER(course_name) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseName.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            System.err.println("CourseDAO Error [getEnrolledCount]: " + e.getMessage());
        }
        return 0;
    }
}