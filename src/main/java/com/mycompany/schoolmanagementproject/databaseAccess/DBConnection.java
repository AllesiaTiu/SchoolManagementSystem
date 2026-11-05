package com.mycompany.schoolmanagementproject.databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

 
    private static final String URL = "jdbc:mysql://localhost:3306/school_management_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() throws SQLException {
        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Check your dependencies.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection testConn = getConnection()) {
            if (testConn != null && !testConn.isClosed()) {
                System.out.println("DATABASE CONNECTED SUCCESSFULLY TO XAMPP!");
            }
        } catch (SQLException e) {
            System.err.println("FAILED TO CONNECT TO DATABASE: " + e.getMessage());
        }
    }
}