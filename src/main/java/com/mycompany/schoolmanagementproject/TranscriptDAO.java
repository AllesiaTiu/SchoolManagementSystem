package com.mycompany.schoolmanagementproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TranscriptDAO {

    // Pulls all recorded subjects, course codes, and grades for a specific student ID
    public ArrayList<TranscriptRecord> getTranscriptByStudentId(String studentId) {
        ArrayList<TranscriptRecord> list = new ArrayList<>();
        
        String sql = "SELECT c.course_id, g.course_name, g.score "
                   + "FROM grades g "
                   + "LEFT JOIN courses c ON LOWER(TRIM(c.course_name)) = LOWER(TRIM(g.course_name)) "
                   + "WHERE g.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, studentId.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                int count = 1;
                while (rs.next()) {
                    String code = rs.getString("course_id");
                    if (code == null || code.trim().isEmpty()) {
                        code = "IT" + (100 + count); // Fallback code if unassigned
                    }
                    String title = rs.getString("course_name");
                    double score = rs.getDouble("score");
                    int defaultUnits = 3; // Standard 3 units per subject

                    list.add(new TranscriptRecord(count++, code, title, defaultUnits, score));
                }
            }
        } catch (SQLException e) {
            System.err.println("TranscriptDAO Error [getTranscriptByStudentId]: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}