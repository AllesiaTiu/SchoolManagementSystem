package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.gui.AttendanceBranch;
import com.mycompany.schoolmanagementproject.gui.GradesBranch;
import com.mycompany.schoolmanagementproject.gui.CourseBranch;
import com.mycompany.schoolmanagementproject.gui.StudentBranch;
import com.mycompany.schoolmanagementproject.manager.ReportManager;
import com.mycompany.schoolmanagementproject.model.StudentReport;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportBranch extends JFrame implements ActionListener {

    private ReportManager reportManager;
    private ArrayList<StudentReport> reportList; // Explicit ArrayList storage

    JButton students, courses, grades, transcript, report, attendance;
    
    JTextField txtSearch;
    JComboBox<String> cmbFilter;
    JButton btnFilter, btnExport, btnSearch;
    
    DefaultTableModel reportModel;
    JTable reportTable;
    JScrollPane sp;

    public ReportBranch() {

        setTitle("School Management System - Report Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        //For jbutton
        reportManager = new ReportManager();
        reportList = new ArrayList<>();

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

     
        JLabel reportTitle = new JLabel("Academic Performance Reports");
        reportTitle.setBounds(20, 0, 250, 30);

        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setBounds(180, 30, 780, 30);
        panelTitle.setLayout(null);
        panelTitle.add(reportTitle);
        add(panelTitle);

        JLabel lblSearch = new JLabel("Search Name:");
        lblSearch.setBounds(180, 75, 90, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(275, 75, 140, 25);
        add(txtSearch);

        btnSearch = new JButton("Binary Search");
        btnSearch.setBounds(420, 75, 120, 25);
        btnSearch.addActionListener(this);
        add(btnSearch);

        JLabel lblFilter = new JLabel("Status:");
        lblFilter.setBounds(550, 75, 50, 25);
        add(lblFilter);

        cmbFilter = new JComboBox<>(new String[]{"All", "Dean's Lister", "Passed", "Warning / Failed", "No Grade"});
        cmbFilter.setBounds(600, 75, 120, 25);
        add(cmbFilter);

        btnFilter = new JButton("Filter");
        btnFilter.setBounds(725, 75, 75, 25);
        btnFilter.addActionListener(this);
        add(btnFilter);

        btnExport = new JButton("Export CSV");
        btnExport.setBounds(840, 75, 120, 25);
        btnExport.addActionListener(this);
        add(btnExport);

        String[] columns = {"Student ID", "Name", "Course / Program", "GPA / GWA", "Status"};
        reportModel = new DefaultTableModel(columns, 0);

        reportTable = new JTable(reportModel);
        reportTable.setRowHeight(25);
        sp = new JScrollPane(reportTable);
        sp.setBounds(180, 115, 780, 500);
        add(sp);

        students.addActionListener(this);
        courses.addActionListener(this);
        grades.addActionListener(this);
        transcript.addActionListener(this);
        report.addActionListener(this);
        attendance.addActionListener(this);

        loadReportsFromDatabase();
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
        } else if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        }

 
        if (e.getSource() == btnSearch) {
            String query = txtSearch.getText().trim();
            if (query.isEmpty()) {
                loadReportsFromDatabase();
                return;
            }

         
            reportList = reportManager.getAllReports();
            reportManager.sortReportsByName(reportList);

 
            int foundIdx = reportManager.binarySearchByName(reportList, query);

            if (foundIdx != -1) {
                ArrayList<StudentReport> singleMatch = new ArrayList<>();
                singleMatch.add(reportList.get(foundIdx));
                refreshTable(singleMatch);
                JOptionPane.showMessageDialog(this, "Student found via Binary Search at sorted position [" + foundIdx + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "Student '" + query + "' not found in reports (Binary Search).");
                loadReportsFromDatabase();
            }
        }

    
        if (e.getSource() == btnFilter) {
            String selectedStatus = cmbFilter.getSelectedItem().toString();
            reportList = reportManager.filterByStatus(selectedStatus);
            refreshTable(reportList);
        }

       
        if (e.getSource() == btnExport) {
            if (reportList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No records to export.");
                return;
            }

            boolean ok = reportManager.exportToCSV("Student_Report.csv", reportList);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Report exported successfully to Student_Report.csv in your project folder!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to export report.");
            }
        }
    }
     private void loadReportsFromDatabase() {
        reportList = reportManager.getAllReports();
        refreshTable(reportList);
    }
  
    private void refreshTable(ArrayList<StudentReport> list) {
        reportModel.setRowCount(0);
        for (StudentReport r : list) {
            String gwaStr = (r.getGwa() > 0.0) ? String.format("%.2f", r.getGwa()) : "N/A";
            reportModel.addRow(new Object[]{
                r.getStudentId(),
                r.getName(),
                r.getProgram(),
                gwaStr,
                r.getStatus()
            });
        }
    }
}