package com.mycompany.schoolmanagementproject;

import java.util.ArrayList;

public class StudentManager {

    private static final StudentDAO studentDAO = new StudentDAO();

    // 1. ADD Student and their Enrolled Courses to Database
    public static boolean addStudent(Student student) {
        return studentDAO.insertStudent(student);
    }

    // 2. GET ALL Students from Database
    public static ArrayList<Student> getAllStudents() {
        return studentDAO.selectAllStudents();
    }

    // 3. UPDATE Student Name
    public static boolean updateStudent(String studentId, String newName) {
        return studentDAO.updateStudentName(studentId, newName);
    }

    // 4. DELETE Student
    public static boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudentById(studentId);
    }

    // 5. GET All Available Courses
    public static ArrayList<String> getAvailableCourses() {
        return studentDAO.selectAvailableCourses();
    }
}