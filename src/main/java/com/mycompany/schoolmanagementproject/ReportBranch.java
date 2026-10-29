package com.mycompany.schoolmanagementproject;

import javax.swing.*;
import javax.swing.JFrame;


 
public class ReportBranch extends JFrame{
    
    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
   
    
    ReportBranch(){
        setTitle("Student Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        //this is for the buttons
        students = new JButton("Students");
        students.setBounds(20, 30, 150, 50);
        add(students);
        
        courses = new JButton("Courses");
        courses.setBounds(20, 90, 150, 50);
        add(courses);
        
        grades = new JButton("Grades");
        grades.setBounds(20, 150, 150, 50);
        add(grades);
        
        transcript = new JButton("Transcript");
        transcript.setBounds(20, 210, 150, 50);
        add(transcript);
        
        report = new JButton("Report");
        report.setBounds(20, 270, 150, 50);
        add(report);
        
        attendance = new JButton("Attendance");
        attendance.setBounds(20, 330, 150, 50);
        add(attendance);
        

        //this is for adding columns
        JLabel reportTitle = new JLabel("Branch Student Reports");
        reportTitle.setFont(new java.awt.Font("Segoe UI", 1, 18));
        reportTitle.setBounds(200, 30, 300, 30);
        add(reportTitle);

        JLabel lblSearch = new JLabel("Search Student:");
        lblSearch.setBounds(200, 80, 100, 25);
        add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(310, 80, 200, 25);
        add(txtSearch);

        //jbutton
        JButton btnFilter = new JButton("Filter");
        btnFilter.setBounds(520, 80, 80, 25);
        add(btnFilter);
        
        //report grades
        String[]columns = {"Student ID", "Name", "Course", "GPA", "Status"};
        Object[][] data = {
            {"2026-001", "Juan Cruz", "BSIT", "1.25", "Passed"},
            {"2026-002", "Maria Santos", "BSIT", "1.75", "Passed"},
            {"2026-003", "Angelo Burkley", "BSIT", "2.00", "Passed"},
            {"2026-004", "Alex Reyes", "BSIT", "2.50", "Warning"},
            {"2026-005", "Jasmine Garcia", "BSIT", "5.00", "Dropped"},
            {"2026-006", "Alfred Grey", "BSIT", "5.00", "Dropped"}
        };
        
        JTable reportTable = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(reportTable);
        sp.setBounds(200, 130, 750, 400);
        add(sp);

        JButton btnExport = new JButton("Export Report");
        btnExport.setBounds(830, 550, 120, 30);
        add(btnExport);
        
        
        
                
         
                
                
                
            
       

    
        
       
        
        
        
        
        
         
    }
}
