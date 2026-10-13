package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradesBranch extends JFrame implements ActionListener{
    
    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
    JButton addBtn;
    JButton updBtn;
    JButton deleteBtn;
    JButton computeAve;
    JButton searchBtn;
    JButton sortBtn;
    
    JLabel title;
    JLabel studentId;
    JLabel courseId;
    JLabel score;
    JLabel gradesList;
    
    JTextField idInput;
    String [] courseList = {"options"};
    JComboBox<String> courseCmb;
    JTextField scoreInput;
    
    
    //panel
    JPanel panelInputs;
    JPanel panelTitle;
    JPanel listTitle;
    
    //table 
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
            
    GradesBranch(){
        setTitle("Student Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
       
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
        
        title = new JLabel("Grades Management");
        title.setBounds(30, 0, 150, 30);
        
        studentId = new JLabel("Student ID: ");
        studentId.setBounds(30, 30, 100, 30);
        
        idInput = new JTextField();
        idInput.setBounds(140, 35, 250, 20);
        
        courseId = new JLabel("Course ID:");
        courseId.setBounds(30, 60, 100, 30);
        
        courseCmb = new JComboBox (courseList);
        courseCmb.setBounds(140, 70, 250, 20);
        
        
        score = new JLabel("Score:");
        score.setBounds(30, 90 , 100, 30);
        
        scoreInput = new JTextField();
        scoreInput.setBounds(140, 100, 250, 20);
        
         addBtn = new JButton("Add");
        addBtn.setBounds(50, 130, 100, 40);
        
        updBtn = new JButton("Update");
        updBtn.setBounds(160, 130, 100, 40);
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(270, 130, 100, 40);
        
        computeAve = new JButton("Average");
        computeAve.setBounds(380, 130, 100, 40);
       
       
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setSize(600, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);
        
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 200);
        panelInputs.setLayout(null);
        panelInputs.add(panelTitle);
        panelInputs.add(studentId);
        panelInputs.add(courseId);
        panelInputs.add(score);
        panelInputs.add(idInput);
        panelInputs.add(courseCmb);
        panelInputs.add(scoreInput);
        panelInputs.add(addBtn);
        panelInputs.add(updBtn);
        panelInputs.add(deleteBtn);
        panelInputs.add(computeAve);
        add(panelInputs);
        
        
        gradesList = new JLabel("Grades List");
        gradesList.setBounds(30, 0, 150, 30);
        
        listTitle = new JPanel();
        listTitle.setBackground(Color.pink);
        listTitle.setBounds(180, 240, 600, 30);
        listTitle.setLayout(null);
        listTitle.add(gradesList);
        add(listTitle);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Programmming 3");
        tableModel.addColumn("Data Structures");
        tableModel.addColumn("PathFit");
        tableModel.addColumn("RVA");
        tableModel.addColumn("DCAN");
        tableModel.addColumn("Operating System");
        tableModel.addColumn("GWA");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 280, 800, 200);
        add(scrollPane);
        
        grades.addActionListener(this);
        students.addActionListener(this);
        courses.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == courses){
            CourseBranch c = new CourseBranch();
             c.setVisible(true);
        }
        if(e.getSource() == grades){
            GradesBranch g = new GradesBranch();
            g.setVisible(true);
        
        }
        if(e.getSource() == students){
             StudentBranch s = new StudentBranch();
              s.setVisible(true);
        }
    }
}
