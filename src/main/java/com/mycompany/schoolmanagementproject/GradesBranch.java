package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradesBranch extends JFrame implements ActionListener {
    
    GradesManager gradesManager = new GradesManager();

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
    
    JLabel title;
    JLabel studentId;
    JLabel courseId;
    JLabel score;
    JLabel gradesList;
    
    JTextField idInput;
    JComboBox<String> courseCmb;
    JTextField scoreInput;
    
    // panels
    JPanel panelInputs;
    JPanel panelTitle;
    JPanel listTitle;
    
    // table 
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
            
    public GradesBranch() {
        setTitle("School Management System - Grades Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        
        courseId = new JLabel("Course Name:");
        courseId.setBounds(30, 60, 100, 30);
        
        // Dynamically load courses from DB
        courseCmb = new JComboBox<>();
        loadDynamicCourses();
        courseCmb.setBounds(140, 70, 250, 20);
        
        score = new JLabel("Score:");
        score.setBounds(30, 90, 100, 30);
        
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
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Score");
        
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 280, 800, 350);
        add(scrollPane);
        
        // Navigation button listeners
        grades.addActionListener(this);
        students.addActionListener(this);
        courses.addActionListener(this);
        attendance.addActionListener(this);
        report.addActionListener(this);
        transcript.addActionListener(this);
        
        // Action button listeners
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        computeAve.addActionListener(this);
        
        // Clicking a row loads data into inputs
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    idInput.setText(tableModel.getValueAt(row, 0).toString());
                    courseCmb.setSelectedItem(tableModel.getValueAt(row, 1).toString());
                    scoreInput.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });

        // Load existing grades on startup
        refreshTable();
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        // Navigation
        if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == grades) {
            // Already here
        }
        if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        }

        // --- 1. ADD BUTTON ---
        if (e.getSource() == addBtn) {
            String id = idInput.getText().trim();
            if (courseCmb.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "No courses available.");
                return;
            }
            String course = courseCmb.getSelectedItem().toString();
            String scoreStr = scoreInput.getText().trim();

            if (id.isEmpty() || scoreStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Student ID and Score.");
                return;
            }

            double scoreVal;
            try {
                scoreVal = Double.parseDouble(scoreStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Score must be a valid number.");
                return;
            }

            Grades newGrade = new Grades(id, course, scoreVal);
            if (gradesManager.addGrade(newGrade)) {
                refreshTable();
                clearFields();
                JOptionPane.showMessageDialog(this, "Grade saved to database successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save grade. Check if Student ID exists.");
            }
        }

        // --- 2. UPDATE BUTTON ---
        if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a grade from the table to update.");
                return;
            }

            String id = idInput.getText().trim();
            String course = courseCmb.getSelectedItem().toString();
            String scoreStr = scoreInput.getText().trim();

            if (scoreStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the new score.");
                return;
            }

            try {
                double newScore = Double.parseDouble(scoreStr);
                if (gradesManager.updateGrade(id, course, newScore)) {
                    refreshTable();
                    clearFields();
                    JOptionPane.showMessageDialog(this, "Grade updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Score must be a valid number.");
            }
        }

        // --- 3. DELETE BUTTON ---
        if (e.getSource() == deleteBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = tableModel.getValueAt(selectedRow, 0).toString();
                String courseName = tableModel.getValueAt(selectedRow, 1).toString();

                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Delete grade for " + studentId + " in " + courseName + "?", 
                    "Confirm", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (gradesManager.deleteGrade(studentId, courseName)) {
                        refreshTable();
                        clearFields();
                        JOptionPane.showMessageDialog(this, "Grade deleted successfully!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row in the table to delete.");
            }
        }

        // --- 4. COMPUTE AVERAGE BUTTON ---
        if (e.getSource() == computeAve) {
            String id = idInput.getText().trim();
            if (id.isEmpty()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    id = tableModel.getValueAt(selectedRow, 0).toString();
                } else {
                    JOptionPane.showMessageDialog(this, "Enter or select a Student ID to compute GWA.");
                    return;
                }
            }

            double gwa = gradesManager.calculateGWA(id);
            if (gwa > 0) {
                JOptionPane.showMessageDialog(this, "Student: " + id + "\nCalculated GWA: " + String.format("%.2f", gwa) + "\n(Updated in Database!)");
            } else {
                JOptionPane.showMessageDialog(this, "No grades found for Student ID: " + id);
            }
        }
    }
     private void loadDynamicCourses() {
        courseCmb.removeAllItems();
        ArrayList<String> courses = StudentManager.getAvailableCourses();
        for (String c : courses) {
            courseCmb.addItem(c);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Grades> list = gradesManager.getAllGrades();
        for (Grades g : list) {
            tableModel.addRow(new Object[]{
                g.getStudentId(),
                g.getCourseId(),
                g.getScore()
            });
        }
    }

    private void clearFields() {
        idInput.setText("");
        scoreInput.setText("");
        if (courseCmb.getItemCount() > 0) courseCmb.setSelectedIndex(0);
        table.clearSelection();
    }
}