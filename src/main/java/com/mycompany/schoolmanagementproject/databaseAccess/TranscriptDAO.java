package com.mycompany.schoolmanagementproject.databaseAccess;

import com.mycompany.schoolmanagementproject.model.TranscriptRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TranscriptDAO {

    public ArrayList<TranscriptRecord> getTranscriptByStudentId(String studentId) {
        ArrayList<TranscriptRecord> list = new ArrayList<>();
        
        // Pulls all enrolled subjects first, and pulls grades/scores if already graded
        String sql = "SELECT "
                   + "    IFNULL(c.course_id, 'IT101') AS course_id, "
                   + "    e.course_name, "
                   + "    IFNULL(g.score, 0.0) AS score "
                   + "FROM enrollments e "
                   + "LEFT JOIN courses c ON LOWER(TRIM(c.course_name)) = LOWER(TRIM(e.course_name)) "
                   + "LEFT JOIN grades g ON g.student_id = e.student_id AND LOWER(TRIM(g.course_name)) = LOWER(TRIM(e.course_name)) "
                   + "WHERE e.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, studentId.trim());
            
            try (ResultSet rs = ps.executeQuery()) {
                int count = 1;
                while (rs.next()) {
                    String code = rs.getString("course_id");
                    if (code == null || code.trim().isEmpty()) {
                        code = "CS" + (100 + count);
                    }
                    String title = rs.getString("course_name");
                    double score = rs.getDouble("score");
                    int defaultUnits = 3; 

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