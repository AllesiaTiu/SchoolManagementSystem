package com.mycompany.schoolmanagementproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/school_management_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
        }
        return conn;
    }

    
    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            System.out.println("DATABASE CONNECTED SUCCESSFULLY TO XAMPP!");
        } else {
            System.out.println("FAILED TO CONNECT TO DATABASE");
        }
    }
}