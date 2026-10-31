
package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TranscriptBranch extends JFrame {

    public TranscriptBranch() {
        setTitle("School Management System - Transcript");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 243, 248));

        // ==========================================
        // 1. LEFT SIDEBAR PANEL
        // ==========================================
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 180, 720);
        sidebar.setBackground(new Color(28, 43, 65));
        sidebar.setLayout(null);
        add(sidebar);

        JLabel brandTitle = new JLabel("School System");
        brandTitle.setForeground(Color.WHITE);
        brandTitle.setFont(new Font("Arial", Font.BOLD, 15));
        brandTitle.setBounds(20, 20, 150, 30);
        sidebar.add(brandTitle);

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(15, 70, 150, 38);
        sidebar.add(btnDashboard);

        JButton btnStudent = new JButton("Student");
        btnStudent.setBounds(15, 115, 150, 38);
        sidebar.add(btnStudent);

        JButton btnCourse = new JButton("Course");
        btnCourse.setBounds(15, 160, 150, 38);
        sidebar.add(btnCourse);

        JButton btnGrade = new JButton("Grade");
        btnGrade.setBounds(15, 205, 150, 38);
        sidebar.add(btnGrade);

        JButton btnTranscript = new JButton("Transcript");
        btnTranscript.setBounds(15, 250, 150, 38);
        btnTranscript.setBackground(new Color(41, 103, 196));
        btnTranscript.setForeground(Color.WHITE);
        sidebar.add(btnTranscript);

        JButton btnSettings = new JButton("Settings");
        btnSettings.setBounds(15, 295, 150, 38);
        sidebar.add(btnSettings);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(15, 620, 150, 35);
        sidebar.add(btnLogout);

        // ==========================================
        // 2. HEADER TITLE BAR
        // ==========================================
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(180, 0, 920, 60);
        headerPanel.setBackground(new Color(41, 67, 98));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Transcript");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 12, 200, 35);
        headerPanel.add(lblTitle);

        // ==========================================
        // 3. SEARCH SECTION
        // ==========================================
        JLabel lblSearch = new JLabel("Search Student:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 13));
        lblSearch.setBounds(200, 80, 110, 30);
        add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(315, 80, 270, 30);
        add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(595, 80, 95, 30);
        btnSearch.setBackground(new Color(41, 103, 196));
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        // ==========================================
        // 4. STUDENT INFO CARD
        // ==========================================
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(200, 125, 630, 135);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 235))
        );
        infoPanel.setLayout(null);
        add(infoPanel);

        // Avatar placeholder
        JLabel lblAvatar = new JLabel("[ Photo ]", SwingConstants.CENTER);
        lblAvatar.setOpaque(true);
        lblAvatar.setBackground(new Color(225, 235, 245));
        lblAvatar.setBounds(15, 20, 80, 85);
        infoPanel.add(lblAvatar);

        // Student Details
        JLabel lblId = new JLabel("Student ID:");
        lblId.setBounds(115, 15, 200, 22);
        infoPanel.add(lblId);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(115, 42, 200, 22);
        infoPanel.add(lblName);

        JLabel lblProgram = new JLabel("Program:");
        lblProgram.setBounds(115, 69, 200, 22);
        infoPanel.add(lblProgram);

        JLabel lblYear = new JLabel("Year Level:");
        lblYear.setBounds(115, 96, 200, 22);
        infoPanel.add(lblYear);

        // Academic Year
        JLabel lblAY = new JLabel("Academic Year:");
        lblAY.setBounds(350, 20, 110, 25);
        infoPanel.add(lblAY);

        JComboBox<String> cbAY = new JComboBox<>();
        cbAY.setBounds(460, 20, 140, 26);
        infoPanel.add(cbAY);

        // Semester
        JLabel lblSem = new JLabel("Semester:");
        lblSem.setBounds(350, 70, 110, 25);
        infoPanel.add(lblSem);

        JComboBox<String> cbSem = new JComboBox<>();
        cbSem.setBounds(460, 70, 140, 26);
        infoPanel.add(cbSem);

        // ==========================================
        // 5. GWA CARD (TOP RIGHT)
        // ==========================================
        JPanel gwaPanel = new JPanel();
        gwaPanel.setBounds(845, 125, 215, 135);
        gwaPanel.setBackground(Color.WHITE);
        gwaPanel.setBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 235))
        );
        gwaPanel.setLayout(null);
        add(gwaPanel);

        JLabel lblGwaTitle = new JLabel("General Weighted Average (GWA)");
        lblGwaTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        lblGwaTitle.setBounds(12, 12, 200, 20);
        gwaPanel.add(lblGwaTitle);

        JLabel lblGwaVal = new JLabel("");
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 26));
        lblGwaVal.setBounds(12, 38, 100, 35);
        gwaPanel.add(lblGwaVal);

        JLabel lblEq = new JLabel("Equivalent Grade");
        lblEq.setFont(new Font("Arial", Font.PLAIN, 11));
        lblEq.setBounds(12, 80, 150, 18);
        gwaPanel.add(lblEq);

        JLabel lblEqVal = new JLabel("");
        lblEqVal.setFont(new Font("Arial", Font.BOLD, 15));
        lblEqVal.setBounds(12, 100, 80, 20);
        gwaPanel.add(lblEqVal);

        // ==========================================
        // 6. ACTION BUTTONS (RIGHT)
        // ==========================================
        JButton btnPrint = new JButton("Print Transcript");
        btnPrint.setBounds(845, 275, 215, 35);
        btnPrint.setBackground(new Color(41, 103, 196));
        btnPrint.setForeground(Color.WHITE);
        add(btnPrint);

        JButton btnExport = new JButton("Export to PDF");
        btnExport.setBounds(845, 320, 215, 35);
        add(btnExport);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(845, 365, 215, 35);
        add(btnRefresh);

        // ==========================================
        // 7. ACADEMIC RECORD TABLE
        // ==========================================
        JLabel lblRecord = new JLabel("Academic Record");
        lblRecord.setFont(new Font("Arial", Font.BOLD, 14));
        lblRecord.setBounds(200, 275, 150, 25);
        add(lblRecord);

        String[] columns = {
            "No.",
            "Subject Code",
            "Subject Title",
            "Units",
            "Grade",
            "Remarks"
        };

        // Empty academic records
        Object[][] data = {};

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
        table.setRowHeight(26);

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(75);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 305, 630, 290);
        add(scrollPane);

        // ==========================================
        // 8. FOOTER
        // ==========================================
        JLabel lblTotalUnits = new JLabel("Total Units:");
        lblTotalUnits.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotalUnits.setBounds(200, 610, 150, 20);
        add(lblTotalUnits);

        JLabel lblDisclaimer = new JLabel(
            "Note: This is not an official transcript. For official use, please request from the Registrar's Office."
        );
        lblDisclaimer.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDisclaimer.setForeground(Color.GRAY);
        lblDisclaimer.setBounds(460, 610, 550, 20);
        add(lblDisclaimer);
    }

    // ==========================================
    // 9. MAIN METHOD
    // ==========================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TranscriptBranch().setVisible(true);
        });
    }
}
