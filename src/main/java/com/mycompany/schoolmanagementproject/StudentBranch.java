package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import javax.swing.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class StudentBranch extends JFrame implements ActionListener {

    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
    JButton addBtn;
    JButton updBtn;
    JButton deleteBtn;
    JButton searchBtn;
    JButton sortBtn;
    
    JPanel panelInputs;
    JPanel panelTitle;
    JPanel courseBox;
    
    JLabel title;
    JLabel studentId;
    JLabel studentName;
    JLabel studentCourse;
    
    JTextField nameInput;
    JTextField idInput;
    JTextField searchInput;
    
    //Table
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    JScrollPane scrollCourse;
    
    //ArrayLists
    ArrayList<Student> studentList;
    ArrayList<JCheckBox> courseCheckBox;
    
    //checkboxes
    JCheckBox programming3;
    JCheckBox dataStructures;
    JCheckBox pathfit;
    JCheckBox rva;
    JCheckBox computerNetworks;
    JCheckBox operatingSystem;
    JCheckBox freeElective;
    JCheckBox uts;
    
    //count
    int courseCount;
    
    
    StudentBranch(){
        setTitle("School Management System");
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
        
        studentId = new JLabel("Student ID: ");
        studentId.setBounds(30, 30, 100, 30);
        
        idInput = new JTextField();
        idInput.setBounds(140, 35, 250, 20);
        
        studentName = new JLabel("Name:");
        studentName.setBounds(30, 60, 100, 30);
        
        nameInput = new JTextField();
        nameInput.setBounds(140, 65, 250, 20);
        
        studentCourse = new JLabel("Courses: ");
        studentCourse.setBounds(30, 90, 100, 30);
       
        title = new JLabel("Student Management");
        title.setBounds(30, 0, 150, 30);
        
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setSize(600, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);
        
        courseCheckBox = new ArrayList<>();
        
        programming3 = new JCheckBox("Programming 3");
        programming3.setBounds(10, 10, 120, 10);
        courseCheckBox.add(programming3);
        
        dataStructures = new JCheckBox("Data Structures");
        dataStructures.setBounds(10, 30, 120, 10);
        courseCheckBox.add(dataStructures);
        
        pathfit = new JCheckBox("PathFit");
        pathfit.setBounds(10, 50, 120, 10);
        courseCheckBox.add(pathfit);
        
        rva = new JCheckBox("Reading in Visual Arts");
        rva.setBounds(10, 70, 200, 10);
        courseCheckBox.add(rva);
        
        computerNetworks = new JCheckBox("Computer Networks");
        computerNetworks.setBounds(10, 90, 180, 10);
        courseCheckBox.add(computerNetworks);
        
        operatingSystem = new JCheckBox("Operating Systems");
        operatingSystem.setBounds(10, 110, 180, 10);
        courseCheckBox.add(operatingSystem);
        
        courseBox = new JPanel();
        courseBox.setBackground(Color.WHITE);
        courseBox.setLayout(null);
        courseBox.setPreferredSize(new java.awt.Dimension(230, 200));
        
        for(JCheckBox checkBox:courseCheckBox){
            courseBox.add(checkBox);
        }
        
        scrollCourse = new JScrollPane(courseBox);
        scrollCourse.setBounds(140, 95, 250, 100);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(50, 205, 100, 40);
        
        updBtn = new JButton("Update");
        updBtn.setBounds(160, 205, 100, 40);
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(270, 205, 100, 40);
        
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 300);
        panelInputs.setLayout(null);
        panelInputs.add(panelTitle);
        panelInputs.add(studentId);
        panelInputs.add(idInput);
        panelInputs.add(studentName);
        panelInputs.add(nameInput);
        panelInputs.add(studentCourse);
        panelInputs.add(addBtn);
        panelInputs.add(updBtn);
        panelInputs.add(deleteBtn);
        panelInputs.add(scrollCourse);
        
        add(panelInputs);
        
        searchInput = new JTextField();
        searchInput.setBounds(180, 340, 250, 20);
        add(searchInput);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(440, 340, 100, 20);
        add(searchBtn);
        
        sortBtn = new JButton("Sort A-Z");
        sortBtn.setBounds(550, 340, 100, 20);
        add(sortBtn);
        
        studentList = new ArrayList<>();
     
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Enrolled Courses");
        tableModel.addColumn("GWA");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 370, 700, 200);
        add(scrollPane);
        
        
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        String nameText = nameInput.getText();
        String idText = idInput.getText();
        
        if(e.getSource() == addBtn){
            
           
                 boolean exist = false;
                for(Student list:studentList ){
                        if(list.getID().equals(idText)){
                             exist = true;
                             break;
                        }
                }
                if(exist){
                    JOptionPane.showMessageDialog(this, "Error");
                }
                else{
                     Student student = new Student(nameText, idText);
                         studentList.add(student);
                         
                         tableModel.addRow(new Object[]{
                         student.getName(),
                         student.getID(), countCourses()
                    
                           });
                         
                         
                }
                
                
           
        }
        if(e.getSource() == sortBtn){
            for(int i = 0; i < tableModel.getRowCount(); i++){
                for(int j = 0; j < tableModel.getColumnCount(); j++){
                    
                    tableModel.setValueAt(bubbleSort(studentList), i, j);
                    
                    
                }
            }
            
            
        }
        
        if(e.getSource() == searchBtn){
            
            
            
        }
        
        
    }
    public int countCourses(){
          for(int i = 0; i< courseCheckBox.size(); i++){
            
            if(courseCheckBox.get(i).isSelected()){
                courseCount++;
          
            }
        }
        return courseCount;
    }
    public void binarySearch(ArrayList<Student> studentList, String searchInput){
        
        
        
        
    }
    public ArrayList<Student> bubbleSort(ArrayList<Student> studentList){
        
        
        
        for(int i = 0; i < studentList.size()- 1; i++){
            for(int j = i + 1; j < studentList.size() - 1; j++){
                
                if(studentList.get(i).getName().compareTo(studentList.get(j).getName()) > 0){
                    
                    String container = studentList.get(i).getName();
                    studentList.get(i).setName(studentList.get(j).getName());
                    studentList.get(j).setName(container);
                    
                }
                
                
            }
            
            
            
        }
        
        return studentList;
        
    }
    
    
    
    
    
}
