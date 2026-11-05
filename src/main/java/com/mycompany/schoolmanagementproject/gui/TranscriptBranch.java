package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.gui.ReportBranch;
import com.mycompany.schoolmanagementproject.gui.AttendanceBranch;
import com.mycompany.schoolmanagementproject.gui.GradesBranch;
import com.mycompany.schoolmanagementproject.gui.CourseBranch;
import com.mycompany.schoolmanagementproject.gui.StudentBranch;
import com.mycompany.schoolmanagementproject.manager.TranscriptManager;
import com.mycompany.schoolmanagementproject.manager.StudentManager;
import com.mycompany.schoolmanagementproject.model.TranscriptRecord;
import com.mycompany.schoolmanagementproject.model.Student;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TranscriptBranch extends JFrame implements ActionListener {

    private TranscriptManager transcriptManager;
    private ArrayList<Student> studentList; // Explicit ArrayList
    private ArrayList<TranscriptRecord> currentRecords;

    // Sidebar navigation buttons
    JButton students, courses, grades, transcript, report, attendance;
    
    // Action buttons & controls
    JTextField txtSearch;
    JButton btnSearch, btnRefresh, btnPrint;

    // Display labels
    JLabel lblIdValue, lblNameValue, lblProgramValue, lblYearValue;
    JLabel lblGwaVal, lblEqVal, lblTotalUnits;

    // Table
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    public TranscriptBranch() {
        transcriptManager = new TranscriptManager();
        studentList = new ArrayList<>();
        currentRecords = new ArrayList<>();

        setTitle("School Management System - Transcript");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- 1. Sidebar Navigation Buttons ---
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

        // --- 2. Title Banner (Standard Pink Banner) ---
        JLabel title = new JLabel("Official Student Transcript");
        title.setBounds(20, 0, 250, 30);

        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setBounds(180, 30, 780, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);
        add(panelTitle);

        // --- 3. Search Bar ---
        JLabel lblSearch = new JLabel("Search Student ID:");
        lblSearch.setBounds(180, 70, 140, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(310, 70, 240, 25);
        add(txtSearch);

        btnSearch = new JButton("Binary Search");
        btnSearch.setBounds(560, 70, 120, 25);
        btnSearch.addActionListener(this);
        add(btnSearch);

        btnRefresh = new JButton("Clear");
        btnRefresh.setBounds(690, 70, 80, 25);
        btnRefresh.addActionListener(this);
        add(btnRefresh);

        // --- 4. Student Information Card ---
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.setBounds(180, 105, 580, 110);
        cardPanel.setLayout(null);

        JLabel lblId = new JLabel("Student ID:");
        lblId.setBounds(15, 10, 80, 20);
        cardPanel.add(lblId);

        lblIdValue = new JLabel("-");
        lblIdValue.setBounds(100, 10, 200, 20);
        cardPanel.add(lblIdValue);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(15, 35, 80, 20);
        cardPanel.add(lblName);

        lblNameValue = new JLabel("-");
        lblNameValue.setBounds(100, 35, 200, 20);
        cardPanel.add(lblNameValue);

        JLabel lblProgram = new JLabel("Program:");
        lblProgram.setBounds(15, 60, 80, 20);
        cardPanel.add(lblProgram);

        lblProgramValue = new JLabel("BS Information Technology");
        lblProgramValue.setBounds(100, 60, 200, 20);
        cardPanel.add(lblProgramValue);

        JLabel lblYear = new JLabel("Year Level:");
        lblYear.setBounds(15, 85, 80, 20);
        cardPanel.add(lblYear);

        lblYearValue = new JLabel("2nd Year");
        lblYearValue.setBounds(100, 85, 200, 20);
        cardPanel.add(lblYearValue);

        add(cardPanel);

        // --- 5. GWA & Equivalent Card ---
        JPanel gwaPanel = new JPanel();
        gwaPanel.setBackground(Color.WHITE);
        gwaPanel.setBounds(770, 105, 190, 110);
        gwaPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gwaPanel.setLayout(null);

        JLabel lblGwaTitle = new JLabel("General Weighted Average");
        lblGwaTitle.setBounds(10, 10, 180, 15);
        lblGwaTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        gwaPanel.add(lblGwaTitle);

        lblGwaVal = new JLabel("0.0");
        lblGwaVal.setBounds(10, 28, 180, 35);
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 24));
        gwaPanel.add(lblGwaVal);

        JLabel lblEq = new JLabel("Equivalent Rating:");
        lblEq.setBounds(10, 65, 180, 15);
        lblEq.setFont(new Font("Arial", Font.PLAIN, 11));
        gwaPanel.add(lblEq);

        lblEqVal = new JLabel("-");
        lblEqVal.setBounds(10, 82, 180, 20);
        lblEqVal.setFont(new Font("Arial", Font.BOLD, 12));
        gwaPanel.add(lblEqVal);

        add(gwaPanel);

        // --- 6. Academic Records Table ---
        String[] columns = {"No.", "Subject Code", "Subject Title", "Units", "Grade", "Remarks"};
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);
        table.setRowHeight(25);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 225, 780, 380);
        add(scrollPane);

        lblTotalUnits = new JLabel("Total Units: 0");
        lblTotalUnits.setBounds(180, 615, 150, 25);
        lblTotalUnits.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblTotalUnits);

        btnPrint = new JButton("Print Transcript");
        btnPrint.setBounds(820, 615, 140, 30);
        btnPrint.addActionListener(this);
        add(btnPrint);

        // Listeners
        students.addActionListener(this);
        courses.addActionListener(this);
        grades.addActionListener(this);
        transcript.addActionListener(this);
        report.addActionListener(this);
        attendance.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Sidebar Navigation Handlers
        if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == grades) {
            new GradesBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        }

        // --- BINARY SEARCH BY STUDENT ID ---
        if (e.getSource() == btnSearch) {
            String targetId = txtSearch.getText().trim();
            if (targetId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID to search.");
                return;
            }

            // 1. Fetch student list from database
            studentList = StudentManager.getAllStudents();
            if (studentList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students exist in database.");
                return;
            }

            // 2. Sort the ArrayList by ID (Binary Search requirement)
            transcriptManager.sortStudentsById(studentList);

            // 3. Execute Binary Search (O(log n))
            int foundIdx = transcriptManager.binarySearchStudentById(studentList, targetId);

            if (foundIdx != -1) {
                Student s = studentList.get(foundIdx);

                // Update info cards
                lblIdValue.setText(s.getID());
                lblNameValue.setText(s.getName());
                lblGwaVal.setText((s.getGwa() > 0.0) ? String.format("%.2f", s.getGwa()) : "N/A");
                lblEqVal.setText(transcriptManager.getEquivalent(s.getGwa()));

                // Fetch real subject grades from MySQL via DAO
                tableModel.setRowCount(0);
                currentRecords = transcriptManager.getStudentTranscript(s.getID());
                for (TranscriptRecord r : currentRecords) {
                    tableModel.addRow(new Object[]{
                        r.getNo(),
                        r.getSubjectCode(),
                        r.getSubjectTitle(),
                        r.getUnits(),
                        String.format("%.2f", r.getGrade()),
                        r.getRemarks()
                    });
                }
                lblTotalUnits.setText("Total Units: " + transcriptManager.calculateTotalUnits(currentRecords));
                JOptionPane.showMessageDialog(this, "Student found via Binary Search at sorted position [" + foundIdx + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "Student ID '" + targetId + "' not found in records (Binary Search).");
            }
        }

        // --- CLEAR / REFRESH ---
        if (e.getSource() == btnRefresh) {
            txtSearch.setText("");
            lblIdValue.setText("-");
            lblNameValue.setText("-");
            lblGwaVal.setText("0.0");
            lblEqVal.setText("-");
            lblTotalUnits.setText("Total Units: 0");
            tableModel.setRowCount(0);
        }

        // --- PRINT TRANSCRIPT ---
        if (e.getSource() == btnPrint) {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No transcript record loaded to print.");
                return;
            }
            try {
                table.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Print Error: " + ex.getMessage());
            }
        }
    }
}