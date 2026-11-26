package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.databaseAccess.UserDAO;
import com.mycompany.schoolmanagementproject.gui.LoginGUI;
import com.mycompany.schoolmanagementproject.model.AttendanceRecord;
import com.mycompany.schoolmanagementproject.model.Grades;
import com.mycompany.schoolmanagementproject.model.Student;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentPortalGUI extends JFrame implements ActionListener {

    private Student loggedInStudent;
    private UserDAO userDAO;

    private JLabel titleLbl, lblIdVal, lblNameVal, lblGwaVal, lblStatusVal;
    private JTabbedPane tabbedPane;
    private DefaultTableModel gradesTableModel, attendanceTableModel;
    private JTable gradesTable, attendanceTable;
    private JButton btnLogout, btnRefresh;

    public StudentPortalGUI(Student student) {
        this.loggedInStudent = student;
        this.userDAO = new UserDAO();

        setTitle("School Management System - Student Portal");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- 1. Header Banner ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.PINK);
        headerPanel.setBounds(0, 0, 900, 50);
        headerPanel.setLayout(null);

        titleLbl = new JLabel("Welcome to Your Student Portal, " + loggedInStudent.getName());
        titleLbl.setFont(new Font("Arial", Font.BOLD, 16));
        titleLbl.setBounds(20, 12, 500, 25);
        headerPanel.add(titleLbl);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(770, 12, 90, 28);
        btnLogout.addActionListener(this);
        headerPanel.add(btnLogout);
        add(headerPanel);

        // --- 2. Student Info Card ---
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBounds(20, 65, 845, 80);
        infoPanel.setLayout(null);

        JLabel lblId = new JLabel("Student ID:");
        lblId.setFont(new Font("Arial", Font.BOLD, 12));
        lblId.setBounds(20, 15, 80, 20);
        infoPanel.add(lblId);

        lblIdVal = new JLabel(loggedInStudent.getID());
        lblIdVal.setBounds(110, 15, 200, 20);
        infoPanel.add(lblIdVal);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setFont(new Font("Arial", Font.BOLD, 12));
        lblName.setBounds(20, 45, 80, 20);
        infoPanel.add(lblName);

        lblNameVal = new JLabel(loggedInStudent.getName());
        lblNameVal.setBounds(110, 45, 200, 20);
        infoPanel.add(lblNameVal);

        JLabel lblGwa = new JLabel("Current GWA:");
        lblGwa.setFont(new Font("Arial", Font.BOLD, 12));
        lblGwa.setBounds(400, 15, 90, 20);
        infoPanel.add(lblGwa);

        String gwaStr = (loggedInStudent.getGwa() > 0.0) ? String.format("%.2f", loggedInStudent.getGwa()) : "N/A";
        lblGwaVal = new JLabel(gwaStr);
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblGwaVal.setBounds(500, 15, 100, 20);
        infoPanel.add(lblGwaVal);

        JLabel lblProg = new JLabel("Program:");
        lblProg.setFont(new Font("Arial", Font.BOLD, 12));
        lblProg.setBounds(400, 45, 90, 20);
        infoPanel.add(lblProg);

        lblStatusVal = new JLabel("BS Information Technology");
        lblStatusVal.setBounds(500, 45, 200, 20);
        infoPanel.add(lblStatusVal);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(720, 25, 100, 30);
        btnRefresh.addActionListener(this);
        infoPanel.add(btnRefresh);

        add(infoPanel);

        // --- 3. Tabs (Grades & Attendance) ---
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(20, 160, 845, 430);

        // TAB 1: GRADES
        String[] gradeCols = {"Course Name", "Score", "Remarks"};
        gradesTableModel = new DefaultTableModel(gradeCols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        gradesTable = new JTable(gradesTableModel);
        gradesTable.setRowHeight(25);
        JScrollPane gradesSp = new JScrollPane(gradesTable);
        tabbedPane.addTab("My Academic Grades", gradesSp);

        // TAB 2: ATTENDANCE
        String[] attCols = {"Date", "Course Name", "Attendance Status"};
        attendanceTableModel = new DefaultTableModel(attCols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        attendanceTable = new JTable(attendanceTableModel);
        attendanceTable.setRowHeight(25);
        JScrollPane attSp = new JScrollPane(attendanceTable);
        tabbedPane.addTab("My Attendance History", attSp);

        add(tabbedPane);

        // Load data on startup
        loadStudentData();
    }

    public void loadStudentData() {
        // 1. Populate Grades
        gradesTableModel.setRowCount(0);
        ArrayList<Grades> gradesList = userDAO.getStudentGrades(loggedInStudent.getID());
        for (Grades g : gradesList) {
            String remarks = (g.getScore() >= 75.0 || (g.getScore() <= 3.0 && g.getScore() >= 1.0)) ? "Passed" : "Failed";
            gradesTableModel.addRow(new Object[]{
                g.getCourseId(), // In your DB this holds course_name
                String.format("%.2f", g.getScore()),
                remarks
            });
        }

        // 2. Populate Attendance
        attendanceTableModel.setRowCount(0);
        ArrayList<AttendanceRecord> attList = userDAO.getStudentAttendance(loggedInStudent.getID());
        for (AttendanceRecord a : attList) {
            attendanceTableModel.addRow(new Object[]{
                a.getDate(),
                a.getCourseName(),
                a.getStatus()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRefresh) {
            loadStudentData();
            JOptionPane.showMessageDialog(this, "Portal refreshed with latest records!");
        }

        if (e.getSource() == btnLogout) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginGUI().setVisible(true);
                this.dispose();
            }
        }
    }
}
