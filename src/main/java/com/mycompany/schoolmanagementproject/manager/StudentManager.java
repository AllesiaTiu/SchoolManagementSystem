package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.StudentDAO;
import com.mycompany.schoolmanagementproject.model.Student;
import java.util.ArrayList;

public class StudentManager {

    private static final StudentDAO studentDAO = new StudentDAO();

   
    public static boolean addStudent(Student student) {
        return studentDAO.insertStudent(student);
    }

    
    public static ArrayList<Student> getAllStudents() {
        return studentDAO.selectAllStudents();
    }


    public static boolean updateStudent(String studentId, String newName) {
        return studentDAO.updateStudentName(studentId, newName);
    }


    public static boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudentById(studentId);
    }

   
    public static ArrayList<String> getAvailableCourses() {
        return studentDAO.selectAvailableCourses();
    }
}