package com.mycompany.schoolmanagementproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentBranch extends JFrame implements ActionListener {

    // Sidebar navigation buttons
    private JButton btnNavStudents, btnNavCourses, btnNavGrades, btnNavReports, btnNavExit;

    public StudentBranch() {
        setTitle("School Management System - Student Management");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Add the Navigation Sidebar on the Left
        initSidebar();

        // Add a placeholder center panel (where your student inputs & table go)
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("Student Management Form & Table Goes Here"));
        add(contentPanel, BorderLayout.CENTER);
    }

    private void initSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(8, 1, 5, 10));
        sidebar.setPreferredSize(new Dimension(180, 700));
        sidebar.setBackground(new Color(236, 240, 241));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnNavStudents = new JButton("👨‍🎓 Students");
        btnNavCourses  = new JButton("📖 Courses");
        btnNavGrades   = new JButton("📝 Grades");
        btnNavReports  = new JButton("📊 Reports");
        btnNavExit     = new JButton("🚪 Exit");

        // Attach listeners
        btnNavStudents.addActionListener(this);
        btnNavCourses.addActionListener(this);
        btnNavGrades.addActionListener(this);
        btnNavReports.addActionListener(this);
        btnNavExit.addActionListener(this);

        sidebar.add(btnNavStudents);
        sidebar.add(btnNavCourses);
        sidebar.add(btnNavGrades);
        sidebar.add(btnNavReports);
        sidebar.add(new JLabel("")); // empty spacer
        sidebar.add(btnNavExit);

        add(sidebar, BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNavStudents) {
            // Already on Students screen, do nothing or refresh
        } 
        else if (e.getSource() == btnNavCourses) {
            new CourseBranch().setVisible(true); // Open Course window
            this.dispose();                      // Close current window
        } 
        else if (e.getSource() == btnNavGrades) {
            new GradeBranch().setVisible(true);  // Open Grade window
            this.dispose();                      // Close current window
        } 
        else if (e.getSource() == btnNavReports) {
            new ReportBranch().setVisible(true); // Open Report window
            this.dispose();                      // Close current window
        } 
        else if (e.getSource() == btnNavExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentBranch().setVisible(true);
        });
    }
}