package com.mycompany.schoolmanagementproject.databaseAccess;

import com.mycompany.schoolmanagementproject.databaseAccess.DBConnection;
import com.mycompany.schoolmanagementproject.model.AttendanceRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAO {

   
    public ArrayList<AttendanceRecord> getStudentsByCourse(String courseName, String date) {
        ArrayList<AttendanceRecord> list = new ArrayList<>();
        

        String sql = "SELECT s.student_id, s.name, IFNULL(a.status, 'Present') AS current_status "
                   + "FROM enrollments e "
                   + "JOIN students s ON e.student_id = s.student_id "
                   + "LEFT JOIN attendance a ON a.student_id = s.student_id "
                   + "   AND LOWER(a.course_name) = LOWER(e.course_name) "
                   + "   AND a.date = ? "
                   + "WHERE LOWER(e.course_name) = LOWER(?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, date.trim());
            ps.setString(2, courseName.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AttendanceRecord(
                        rs.getString("name"),
                        rs.getString("student_id"),
                        courseName,
                        date,
                        rs.getString("current_status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("AttendanceDAO Error [getStudentsByCourse]: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

   
    public boolean saveAttendanceList(ArrayList<AttendanceRecord> records) {
        String sql = "INSERT INTO attendance (student_id, course_name, date, status) VALUES (?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE status = VALUES(status)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (AttendanceRecord r : records) {
                    ps.setString(1, r.getStudentId());
                    ps.setString(2, r.getCourseName());
                    ps.setString(3, r.getDate());
                    ps.setString(4, r.getStatus());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("AttendanceDAO Batch Error [saveAttendanceList]: " + e.getMessage());
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
}