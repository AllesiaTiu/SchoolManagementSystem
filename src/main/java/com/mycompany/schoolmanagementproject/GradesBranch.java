package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradesBranch extends JFrame implements ActionListener {
    
    GradesManager gradesManager = new GradesManager();
    ArrayList<Grades> gradesList; // Explicit ArrayList

    JButton students, courses, grades, transcript, report, attendance;
    JButton addBtn, updBtn, deleteBtn, computeAve;
    
    JTextField searchTxt;
    JButton searchBtn;
    
    JLabel title, studentId, courseId, score, gradesListTitle;
    JTextField idInput, scoreInput;
    JComboBox<String> courseCmb;
    
    JPanel panelInputs, panelTitle, listTitle;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
            
    public GradesBranch() {
        gradesList = new ArrayList<>();

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
        
        gradesListTitle = new JLabel("Grades List");
        gradesListTitle.setBounds(30, 0, 150, 30);
        
        listTitle = new JPanel();
        listTitle.setBackground(Color.pink);
        listTitle.setBounds(180, 240, 600, 30);
        listTitle.setLayout(null);
        listTitle.add(gradesListTitle);
        add(listTitle);

        searchTxt = new JTextField();
        searchTxt.setBounds(790, 245, 110, 22);
        add(searchTxt);

        searchBtn = new JButton("Search ID");
        searchBtn.setBounds(905, 245, 80, 22);
        searchBtn.addActionListener(this);
        add(searchBtn);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Score");
        
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 280, 800, 350);
        add(scrollPane);
        
        grades.addActionListener(this);
        students.addActionListener(this);
        courses.addActionListener(this);
        attendance.addActionListener(this);
        report.addActionListener(this);
        transcript.addActionListener(this);
        
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        computeAve.addActionListener(this);
        
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

        refreshTable();
    }

  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        }

        // --- BINARY SEARCH ---
        if (e.getSource() == searchBtn) {
            String query = searchTxt.getText().trim();
            if (query.isEmpty()) {
                refreshTable();
                return;
            }

            gradesList = gradesManager.getAllGrades();
            gradesManager.sortGradesByStudentId(gradesList);

            int idx = binarySearchGrades(gradesList, query);
            if (idx != -1) {
                Grades match = gradesList.get(idx);
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{match.getStudentId(), match.getCourseId(), match.getScore()});
                JOptionPane.showMessageDialog(this, "Grade found via Binary Search at sorted index [" + idx + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "No grades found for Student ID: " + query);
                refreshTable();
            }
        }

        if (e.getSource() == addBtn) {
            String id = idInput.getText().trim();
            if (courseCmb.getSelectedItem() == null) return;
            String course = courseCmb.getSelectedItem().toString();
            String scoreStr = scoreInput.getText().trim();

            if (id.isEmpty() || scoreStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Student ID and Score.");
                return;
            }

            double scoreVal = Double.parseDouble(scoreStr);
            Grades newGrade = new Grades(id, course, scoreVal);
            if (gradesManager.addGrade(newGrade)) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Grade saved successfully!");
            }
        }

        if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) return;

            String id = idInput.getText().trim();
            String course = courseCmb.getSelectedItem().toString();
            double newScore = Double.parseDouble(scoreInput.getText().trim());

            if (gradesManager.updateGrade(id, course, newScore)) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Grade updated successfully!");
            }
        }

        if (e.getSource() == deleteBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = tableModel.getValueAt(selectedRow, 0).toString();
                String courseName = tableModel.getValueAt(selectedRow, 1).toString();

                if (gradesManager.deleteGrade(studentId, courseName)) {
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Grade deleted successfully!");
                }
            }
        }

        if (e.getSource() == computeAve) {
            String id = idInput.getText().trim();
            if (id.isEmpty()) return;

            double gwa = gradesManager.calculateGWA(id);
            if (gwa > 0) {
                JOptionPane.showMessageDialog(this, "Student: " + id + "\nCalculated GWA: " + String.format("%.2f", gwa) + "\n(Updated in Database!)");
            }
        }
    }
      private void loadDynamicCourses() {
        courseCmb.removeAllItems();
        ArrayList<String> coursesList = StudentManager.getAvailableCourses();
        for (String c : coursesList) {
            courseCmb.addItem(c);
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        gradesList = gradesManager.getAllGrades();
        for (Grades g : gradesList) {
            tableModel.addRow(new Object[]{g.getStudentId(), g.getCourseId(), g.getScore()});
        }
    }

    // ==========================================
    // MANUAL BINARY SEARCH FOR GRADES
    // ==========================================
    public int binarySearchGrades(ArrayList<Grades> list, String targetId) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getStudentId().compareToIgnoreCase(targetId);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}