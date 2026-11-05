package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.gui.GradesBranch;
import com.mycompany.schoolmanagementproject.gui.CourseBranch;
import com.mycompany.schoolmanagementproject.gui.StudentBranch;
import com.mycompany.schoolmanagementproject.manager.AttendanceManager;
import com.mycompany.schoolmanagementproject.manager.StudentManager;
import com.mycompany.schoolmanagementproject.model.AttendanceRecord;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AttendanceBranch extends JFrame implements ActionListener {

    private AttendanceManager attendanceManager;
    private ArrayList<AttendanceRecord> attendanceList; // Explicit ArrayList storage


    JButton students, courses, grades, transcript, report, attendance;
    

    JButton searchBtn, saveBtn, loadStudentsBtn;


    JComboBox<String> semesterBox;
    JComboBox<String> classBox;
    JComboBox<String> sectionBox;
    JTextField dateInput, searchInput;


    JPanel panelTitle, panelControls;
    JLabel title;


    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    public AttendanceBranch() {
    
        setTitle("School Management System - Attendance");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        attendanceManager = new AttendanceManager();
        attendanceList = new ArrayList<>();

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

        title = new JLabel("Student Attendance Management");
        title.setBounds(20, 0, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 14));

        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setBounds(180, 30, 780, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);
        add(panelTitle);

  
        panelControls = new JPanel();
        panelControls.setBounds(180, 70, 780, 95);
        panelControls.setLayout(null);
        panelControls.setBackground(Color.LIGHT_GRAY);

        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setBounds(10, 5, 80, 20);
        panelControls.add(semesterLabel);

        semesterBox = new JComboBox<>(new String[]{"1st Semester", "2nd Semester"});
        semesterBox.setBounds(10, 25, 120, 25);
        panelControls.add(semesterBox);

        JLabel coursesLabel = new JLabel("Course:");
        coursesLabel.setBounds(140, 5, 70, 20);
        panelControls.add(coursesLabel);

     
        classBox = new JComboBox<>();
        loadDynamicCourses();
        classBox.setBounds(140, 25, 180, 25);
        panelControls.add(classBox);

        JLabel sectionLabel = new JLabel("Section:");
        sectionLabel.setBounds(330, 5, 70, 20);
        panelControls.add(sectionLabel);

        sectionBox = new JComboBox<>(new String[]{"2-1", "2-2"});
        sectionBox.setBounds(330, 25, 80, 25);
        panelControls.add(sectionBox);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(420, 5, 140, 20);
        panelControls.add(dateLabel);

        dateInput = new JTextField(LocalDate.now().toString());
        dateInput.setBounds(420, 25, 120, 25);
        panelControls.add(dateInput);

        loadStudentsBtn = new JButton("Load Class");
        loadStudentsBtn.setBounds(550, 25, 100, 25);
        loadStudentsBtn.addActionListener(this);
        panelControls.add(loadStudentsBtn);

        saveBtn = new JButton("Save Attendance");
        saveBtn.setBounds(655, 25, 120, 25);
        saveBtn.addActionListener(this);
        panelControls.add(saveBtn);

        searchInput = new JTextField();
        searchInput.setBounds(10, 60, 200, 25);
        panelControls.add(searchInput);

        searchBtn = new JButton("Binary Search");
        searchBtn.setBounds(215, 60, 120, 25);
        searchBtn.addActionListener(this);
        panelControls.add(searchBtn);

        add(panelControls);

 
        String[] columns = {"Student Name", "Student ID", "Attendance Status (Present / Late / Absent)"};
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);
        table.setRowHeight(25);

 
        JComboBox<String> statusCmb = new JComboBox<>(new String[]{"Present", "Late", "Absent"});
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(statusCmb));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 175, 780, 470);
        add(scrollPane);


        students.addActionListener(this);
        courses.addActionListener(this);
        grades.addActionListener(this);
        transcript.addActionListener(this);
        report.addActionListener(this);
        attendance.addActionListener(this);
    }

  

    @Override
    public void actionPerformed(ActionEvent e) {
     
        if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == grades) {
            new GradesBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        }

       
        if (e.getSource() == loadStudentsBtn) {
            if (classBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "No courses available.");
                return;
            }

            String selectedCourse = classBox.getSelectedItem().toString();
            String date = dateInput.getText().trim();

            attendanceList = attendanceManager.getStudentsForCourse(selectedCourse, date);
            refreshTable(attendanceList);

            if (attendanceList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students are currently enrolled in " + selectedCourse + ".");
            }
        }

     
        if (e.getSource() == saveBtn) {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty. Please click 'Load Class' first.");
                return;
            }


            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            String course = classBox.getSelectedItem().toString();
            String date = dateInput.getText().trim();

            ArrayList<AttendanceRecord> records = new ArrayList<>();
            for (int r = 0; r < tableModel.getRowCount(); r++) {
                String name = tableModel.getValueAt(r, 0).toString();
                String id = tableModel.getValueAt(r, 1).toString();
                String status = tableModel.getValueAt(r, 2).toString();
                records.add(new AttendanceRecord(name, id, course, date, status));
            }

            if (attendanceManager.saveAttendance(records)) {
                JOptionPane.showMessageDialog(this, "Attendance records saved successfully to database!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save attendance.");
            }
        }

   
        if (e.getSource() == searchBtn) {
            String query = searchInput.getText().trim();
            if (query.isEmpty() || attendanceList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please load a class and type a student name to search.");
                return;
            }


            attendanceManager.sortAttendanceByName(attendanceList);
            refreshTable(attendanceList);


            int foundIndex = attendanceManager.binarySearchByName(attendanceList, query);

            if (foundIndex != -1) {
         
                table.setRowSelectionInterval(foundIndex, foundIndex);
                table.scrollRectToVisible(table.getCellRect(foundIndex, 0, true));
                JOptionPane.showMessageDialog(this, "Student '" + query + "' found via Binary Search at sorted index [" + foundIndex + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "Student '" + query + "' not found in this class (Binary Search).");
            }
        }
    }
      private void loadDynamicCourses() {
        classBox.removeAllItems();
        ArrayList<String> coursesList = StudentManager.getAvailableCourses();
        for (String c : coursesList) {
            classBox.addItem(c);
        }
    }

    private void refreshTable(ArrayList<AttendanceRecord> list) {
        tableModel.setRowCount(0);
        for (AttendanceRecord r : list) {
            tableModel.addRow(new Object[]{r.getStudentName(), r.getStudentId(), r.getStatus()});
        }
    }
}