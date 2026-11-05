package com.mycompany.schoolmanagementproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportDAO {

    // Fetches all student records and their current GWAs from MySQL
    public ArrayList<StudentReport> selectAllReports() {
        ArrayList<StudentReport> list = new ArrayList<>();
        String sql = "SELECT student_id, name, gwa FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("name");
                double gwa = rs.getDouble("gwa");

                // Default program set to BSIT
                list.add(new StudentReport(id, name, "BSIT", gwa));
            }
        } catch (SQLException e) {
            System.err.println("ReportDAO Error [selectAllReports]: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}