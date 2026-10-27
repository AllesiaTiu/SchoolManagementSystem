//jeanjean
//Attendance Branch
package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.Font;
import java.time.*;
import java.time.YearMonth;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AttendanceBranch extends JFrame {
    
    //Navigation Buttons
    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
    
     // Attendance controls
    JButton searchBtn;
    JButton saveBtn;

    JComboBox<String> semesterBox;
    JComboBox<String> classBox;
    JComboBox<String> sectionBox;

    JTextField dateInput;
    JTextField searchInput;

    // Panels
    JPanel panelTitle;
    JPanel panelControls;

    // Labels
    JLabel title;

    // Attendance table
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    
    AttendanceBranch(){
        setTitle("Attendance");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        
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
        
                title = new JLabel("Student Attendance");
        title.setBounds(20, 0, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 14));

        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setBounds(180, 30, 800, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);

        add(panelTitle);
        
        // ATTENDANCE CONTROLS
        
        panelControls = new JPanel();
        panelControls.setBounds(180, 60, 800, 90);
        panelControls.setLayout(null);
        panelControls.setBackground(Color.WHITE);
        
        // Semester
        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setBounds(10, 5, 90, 20);
        panelControls.add(semesterLabel);

        semesterBox = new JComboBox<>(new String[]{
            "1st Semester",
            "2nd Semester",
        });
        semesterBox.setBounds(10, 28, 150, 25);
        panelControls.add(semesterBox);
                // Class
        JLabel coursesLabel = new JLabel("Courses:");
        coursesLabel.setBounds(175, 5, 70, 20);
        panelControls.add(coursesLabel);

        classBox = new JComboBox<>(new String[]{
            "programming3",
            "Reading in Visual Arts",
            "Data Structures",
            "PathFit",
            "Computer Networks",
            "Operating Systems",

        });
        classBox.setBounds(175, 28, 140, 25);
        panelControls.add(classBox);
        
        // Section
        JLabel sectionLabel = new JLabel("Section:");
        sectionLabel.setBounds(330, 5, 70, 20);
        panelControls.add(sectionLabel);

        sectionBox = new JComboBox<>(new String[]{
            "2-1", "2-2"
        });
        sectionBox.setBounds(330, 28, 100, 25);
        panelControls.add(sectionBox);
        
        // Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(445, 5, 60, 20);
        panelControls.add(dateLabel);

        dateInput = new JTextField("0-0-0000");
        dateInput.setBounds(445, 28, 120, 25);
        panelControls.add(dateInput);
        
        // Search
        searchInput = new JTextField();
        searchInput.setBounds(10, 60, 250, 23);
        panelControls.add(searchInput);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(270, 60, 90, 23);
        panelControls.add(searchBtn);

        saveBtn = new JButton("Save Attendance");
        saveBtn.setBounds(580, 28, 170, 30);
        panelControls.add(saveBtn);

        add(panelControls);
        
                // Attendance Table
        String[] columns = {
            "Student Name",
            "Student I.D",
            "Attendance"
        };

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 160, 800, 440);
        add(scrollPane);
        
    }
    
}
