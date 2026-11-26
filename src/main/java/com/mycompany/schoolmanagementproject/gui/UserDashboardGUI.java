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

public class UserDashboardGUI extends JFrame implements ActionListener {

    private Student loggedInStudent;
    private UserDAO userDAO;

    // Sidebar buttons
    private JButton btnDashboard, btnCourses, btnGrades, btnAttendance, btnTranscript, btnLogout;

    // KPI Summary Card Labels
    private JLabel lblTotalCoursesVal, lblGwaVal, lblAttendanceRateVal, lblStandingVal;

    // Main Table
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel sectionTitle;

    public UserDashboardGUI(Student student) {
        this.loggedInStudent = student;
        this.userDAO = new UserDAO();

        setTitle("School Management System - Student Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ==========================================
        // 1. SIDEBAR NAVIGATION BUTTONS
        // ==========================================
        btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(20, 30, 150, 50);
        btnDashboard.setBackground(Color.DARK_GRAY);
        btnDashboard.setForeground(Color.WHITE);
        btnDashboard.addActionListener(this);
        add(btnDashboard);

        btnCourses = new JButton("My Courses");
        btnCourses.setBounds(20, 90, 150, 50);
        btnCourses.addActionListener(this);
        add(btnCourses);

        btnGrades = new JButton("My Grades");
        btnGrades.setBounds(20, 150, 150, 50);
        btnGrades.addActionListener(this);
        add(btnGrades);

        btnAttendance = new JButton("My Attendance");
        btnAttendance.setBounds(20, 210, 150, 50);
        btnAttendance.addActionListener(this);
        add(btnAttendance);

        btnTranscript = new JButton("My Transcript");
        btnTranscript.setBounds(20, 270, 150, 50);
        btnTranscript.addActionListener(this);
        add(btnTranscript);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(20, 580, 150, 45);
        btnLogout.setBackground(new Color(220, 80, 80));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(this);
        add(btnLogout);

        // ==========================================
        // 2. HEADER BANNER
        // ==========================================
        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setBounds(180, 30, 780, 40);
        panelTitle.setLayout(null);

        JLabel welcomeTitle = new JLabel("Welcome back, " + loggedInStudent.getName() + " (" + loggedInStudent.getID() + ")");
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 15));
        welcomeTitle.setBounds(15, 8, 600, 25);
        panelTitle.add(welcomeTitle);
        add(panelTitle);

        // ==========================================
        // 3. KPI SUMMARY CARDS (4 CARDS)
        // ==========================================
        
        // CARD 1: Total Courses
        JPanel card1 = createCard("Enrolled Subjects", 180, 85, 185, 80);
        lblTotalCoursesVal = new JLabel("0 Subjects");
        lblTotalCoursesVal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalCoursesVal.setBounds(15, 35, 150, 25);
        card1.add(lblTotalCoursesVal);
        add(card1);

        // CARD 2: Current GWA
        JPanel card2 = createCard("Current GWA", 375, 85, 185, 80);
        lblGwaVal = new JLabel("0.00");
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 18));
        lblGwaVal.setBounds(15, 35, 150, 25);
        card2.add(lblGwaVal);
        add(card2);

        // CARD 3: Attendance Rate
        JPanel card3 = createCard("Attendance Rate", 570, 85, 185, 80);
        lblAttendanceRateVal = new JLabel("100%");
        lblAttendanceRateVal.setFont(new Font("Arial", Font.BOLD, 18));
        lblAttendanceRateVal.setBounds(15, 35, 150, 25);
        card3.add(lblAttendanceRateVal);
        add(card3);

        // CARD 4: Academic Standing
        JPanel card4 = createCard("Academic Standing", 765, 85, 195, 80);
        lblStandingVal = new JLabel("Regular");
        lblStandingVal.setFont(new Font("Arial", Font.BOLD, 15));
        lblStandingVal.setForeground(new Color(30, 130, 60));
        lblStandingVal.setBounds(15, 35, 175, 25);
        card4.add(lblStandingVal);
        add(card4);

        // ==========================================
        // 4. MAIN OVERVIEW TABLE
        // ==========================================
        sectionTitle = new JLabel("My Enrolled Courses & Academic Performance");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
        sectionTitle.setBounds(180, 180, 400, 25);
        add(sectionTitle);

        String[] columns = {"Course Name", "Grade / Score", "Remarks", "Units"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        table = new JTable(tableModel);
        table.setRowHeight(28);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 210, 780, 415);
        add(scrollPane);

        // Load data on startup
        loadDashboardData();
    }

    // Helper method to create a clean UI Card
    private JPanel createCard(String title, int x, int y, int w, int h) {
        JPanel card = new JPanel();
        card.setBackground(Color.LIGHT_GRAY);
        card.setBounds(x, y, w, h);
        card.setLayout(null);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setBounds(15, 10, 150, 18);
        card.add(lblTitle);
        return card;
    }

    // Loads student info, grades, and attendance from MySQL
    public void loadDashboardData() {
        // 1. Fetch Enrolled Courses
        ArrayList<String> enrolledCourses = userDAO.getStudentCourses(loggedInStudent.getID());
        lblTotalCoursesVal.setText(enrolledCourses.size() + " Subjects");

        // 2. Fetch Grades
        ArrayList<Grades> gradesList = userDAO.getStudentGrades(loggedInStudent.getID());

        // 3. Update GWA & Academic Standing
        double gwa = loggedInStudent.getGwa();
        if (gwa > 0.0) {
            lblGwaVal.setText(String.format("%.2f", gwa));
            if (gwa >= 90.0 || (gwa <= 1.75 && gwa >= 1.0)) {
                lblStandingVal.setText("Dean's Lister");
                lblStandingVal.setForeground(new Color(30, 130, 60)); // Green
            } else if (gwa >= 75.0 || (gwa <= 3.0 && gwa > 1.75)) {
                lblStandingVal.setText("Good Standing");
                lblStandingVal.setForeground(new Color(40, 90, 180)); // Blue
            } else {
                lblStandingVal.setText("Academic Warning");
                lblStandingVal.setForeground(Color.RED);
            }
        } else {
            lblGwaVal.setText("N/A");
            lblStandingVal.setText("In Progress");
            lblStandingVal.setForeground(Color.DARK_GRAY);
        }

        // 4. Calculate Attendance Rate
        ArrayList<AttendanceRecord> attendanceList = userDAO.getStudentAttendance(loggedInStudent.getID());
        if (!attendanceList.isEmpty()) {
            int presentCount = 0;
            for (AttendanceRecord a : attendanceList) {
                if (a.getStatus().equalsIgnoreCase("Present")) {
                    presentCount++;
                }
            }
            double percentage = ((double) presentCount / attendanceList.size()) * 100;
            lblAttendanceRateVal.setText(String.format("%.0f%%", percentage));
        } else {
            lblAttendanceRateVal.setText("100%");
        }

        // 5. Populate Overview Table
        tableModel.setRowCount(0);
        for (String cName : enrolledCourses) {
            String scoreStr = "In Progress";
            String remarks = "Ongoing";

            // Check if student has a recorded grade for this course
            for (Grades g : gradesList) {
                if (g.getCourseId().equalsIgnoreCase(cName)) {
                    scoreStr = String.format("%.2f", g.getScore());
                    remarks = (g.getScore() >= 75.0 || (g.getScore() <= 3.0 && g.getScore() >= 1.0)) ? "Passed" : "Failed";
                    break;
                }
            }
            tableModel.addRow(new Object[]{cName, scoreStr, remarks, "3 Units"});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- LOGOUT ---
        if (e.getSource() == btnLogout) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginGUI().setVisible(true);
                this.dispose();
            }
            return;
        }

        // --- DASHBOARD (HOME OVERVIEW) ---
        if (e.getSource() == btnDashboard) {
            sectionTitle.setText("My Enrolled Courses & Academic Performance");
            tableModel.setColumnIdentifiers(new String[]{"Course Name", "Grade / Score", "Remarks", "Units"});
            loadDashboardData();
        }

        // --- MY COURSES VIEW ---
        if (e.getSource() == btnCourses) {
            sectionTitle.setText("All Enrolled Courses");
            tableModel.setColumnIdentifiers(new String[]{"No.", "Course Name", "Status", "Units"});
            tableModel.setRowCount(0);
            ArrayList<String> enrolledCourses = userDAO.getStudentCourses(loggedInStudent.getID());
            int num = 1;
            for (String c : enrolledCourses) {
                tableModel.addRow(new Object[]{num++, c, "Enrolled / Active", "3 Units"});
            }
        }

        // --- MY GRADES VIEW ---
        if (e.getSource() == btnGrades) {
            sectionTitle.setText("My Official Course Grades");
            tableModel.setColumnIdentifiers(new String[]{"Course Name", "Score / Rating", "Evaluation"});
            tableModel.setRowCount(0);
            ArrayList<Grades> gradesList = userDAO.getStudentGrades(loggedInStudent.getID());
            for (Grades g : gradesList) {
                String eval = (g.getScore() >= 75.0 || (g.getScore() <= 3.0 && g.getScore() >= 1.0)) ? "Passed" : "Failed";
                tableModel.addRow(new Object[]{g.getCourseId(), String.format("%.2f", g.getScore()), eval});
            }
        }

        // --- MY ATTENDANCE VIEW ---
        if (e.getSource() == btnAttendance) {
            sectionTitle.setText("My Attendance History");
            tableModel.setColumnIdentifiers(new String[]{"Date (YYYY-MM-DD)", "Subject", "Status"});
            tableModel.setRowCount(0);
            ArrayList<AttendanceRecord> attList = userDAO.getStudentAttendance(loggedInStudent.getID());
            for (AttendanceRecord a : attList) {
                tableModel.addRow(new Object[]{a.getDate(), a.getCourseName(), a.getStatus()});
            }
        }

        // --- MY TRANSCRIPT VIEW ---
        if (e.getSource() == btnTranscript) {
            sectionTitle.setText("My Academic Transcript");
            tableModel.setColumnIdentifiers(new String[]{"No.", "Course Name", "Grade", "Remarks"});
            tableModel.setRowCount(0);
            ArrayList<Grades> gradesList = userDAO.getStudentGrades(loggedInStudent.getID());
            int no = 1;
            for (Grades g : gradesList) {
                String remarks = (g.getScore() >= 75.0 || (g.getScore() <= 3.0 && g.getScore() >= 1.0)) ? "Passed" : "Failed";
                tableModel.addRow(new Object[]{no++, g.getCourseId(), String.format("%.2f", g.getScore()), remarks});
            }
            JOptionPane.showMessageDialog(this, 
                "Total Earned Units: " + (gradesList.size() * 3) + "\nOverall GWA: " + lblGwaVal.getText(),
                "Transcript Summary", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}