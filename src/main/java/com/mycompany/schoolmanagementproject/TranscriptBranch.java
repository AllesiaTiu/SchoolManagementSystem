
package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TranscriptBranch extends JFrame {

    // DARK THEME COLORS
    private final Color BG = new Color(21, 27, 43);
    private final Color SIDEBAR = new Color(16, 24, 39);
    private final Color HEADER = new Color(30, 41, 59);
    private final Color CARD = new Color(32, 43, 61);
    private final Color BLUE = new Color(37, 99, 235);
    private final Color TEXT = new Color(241, 245, 249);
    private final Color MUTED = new Color(156, 163, 175);
    private final Color BORDER = new Color(55, 65, 81);
    private final Color FIELD = new Color(42, 54, 73);

    public TranscriptBranch() {

        setTitle("School Management System - Transcript");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(BG);

        // ==========================================
        // 1. LEFT SIDEBAR PANEL
        // ==========================================

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 180, 720);
        sidebar.setBackground(SIDEBAR);
        sidebar.setLayout(null);
        add(sidebar);

        JLabel brandTitle = new JLabel("School System");
        brandTitle.setForeground(TEXT);
        brandTitle.setFont(new Font("Arial", Font.BOLD, 15));
        brandTitle.setBounds(20, 20, 150, 30);
        sidebar.add(brandTitle);

        String[] menuItems = {
            "Dashboard", "Student", "Course",
            "Grade", "Transcript", "Settings"
        };

        int y = 70;

        for (String item : menuItems) {

            JButton button = new JButton(item);
            button.setBounds(15, y, 150, 38);
            button.setFont(new Font("Arial", Font.PLAIN, 13));
            button.setFocusPainted(false);
            button.setBorderPainted(false);

            if (item.equals("Transcript")) {
                button.setBackground(BLUE);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(SIDEBAR);
                button.setForeground(MUTED);
            }

            sidebar.add(button);
            y += 45;
        }

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(15, 620, 150, 35);
        btnLogout.setBackground(new Color(127, 29, 29));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        sidebar.add(btnLogout);

        // ==========================================
        // 2. HEADER TITLE BAR
        // ==========================================

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(180, 0, 920, 60);
        headerPanel.setBackground(HEADER);
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
        lblSearch.setForeground(TEXT);
        lblSearch.setBounds(200, 80, 110, 30);
        add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(315, 80, 270, 30);
        txtSearch.setBackground(FIELD);
        txtSearch.setForeground(TEXT);
        txtSearch.setCaretColor(TEXT);
        txtSearch.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(595, 80, 95, 30);
        btnSearch.setBackground(BLUE);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        add(btnSearch);

        // ==========================================
        // 4. STUDENT INFORMATION CARD
        // ==========================================

        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(200, 125, 630, 135);
        infoPanel.setBackground(CARD);
        infoPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        infoPanel.setLayout(null);
        add(infoPanel);

        JLabel lblAvatar = new JLabel(
            "[ Photo ]", SwingConstants.CENTER
        );
        lblAvatar.setOpaque(true);
        lblAvatar.setBackground(FIELD);
        lblAvatar.setForeground(TEXT);
        lblAvatar.setBounds(15, 20, 80, 85);
        infoPanel.add(lblAvatar);

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

        JLabel lblAY = new JLabel("Academic Year:");
        lblAY.setBounds(350, 20, 110, 25);
        infoPanel.add(lblAY);

        JComboBox<String> cbAY = new JComboBox<>();
        cbAY.setBounds(460, 20, 140, 26);
        cbAY.setBackground(FIELD);
        cbAY.setForeground(TEXT);
        infoPanel.add(cbAY);

        JLabel lblSem = new JLabel("Semester:");
        lblSem.setBounds(350, 70, 110, 25);
        infoPanel.add(lblSem);

        JComboBox<String> cbSem = new JComboBox<>();
        cbSem.setBounds(460, 70, 140, 26);
        cbSem.setBackground(FIELD);
        cbSem.setForeground(TEXT);
        infoPanel.add(cbSem);

        for (Component c : infoPanel.getComponents()) {
            if (c instanceof JLabel && c != lblAvatar) {
                c.setForeground(TEXT);
            }
        }

        // ==========================================
        // 5. GWA CARD
        // ==========================================

        JPanel gwaPanel = new JPanel();
        gwaPanel.setBounds(845, 125, 215, 135);
        gwaPanel.setBackground(CARD);
        gwaPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        gwaPanel.setLayout(null);
        add(gwaPanel);

        JLabel lblGwaTitle = new JLabel(
            "General Weighted Average (GWA)"
        );
        lblGwaTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        lblGwaTitle.setForeground(MUTED);
        lblGwaTitle.setBounds(12, 12, 200, 20);
        gwaPanel.add(lblGwaTitle);

        JLabel lblGwaVal = new JLabel("");
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 26));
        lblGwaVal.setForeground(BLUE);
        lblGwaVal.setBounds(12, 38, 150, 35);
        gwaPanel.add(lblGwaVal);

        JLabel lblEq = new JLabel("Equivalent Grade");
        lblEq.setFont(new Font("Arial", Font.PLAIN, 11));
        lblEq.setForeground(MUTED);
        lblEq.setBounds(12, 80, 150, 18);
        gwaPanel.add(lblEq);

        JLabel lblEqVal = new JLabel("");
        lblEqVal.setFont(new Font("Arial", Font.BOLD, 15));
        lblEqVal.setForeground(TEXT);
        lblEqVal.setBounds(12, 100, 80, 20);
        gwaPanel.add(lblEqVal);

        // ==========================================
        // 6. ACTION BUTTONS
        // ==========================================

        JButton btnPrint = new JButton("Print Transcript");
        btnPrint.setBounds(845, 275, 215, 35);
        btnPrint.setBackground(BLUE);
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFocusPainted(false);
        btnPrint.setBorderPainted(false);
        add(btnPrint);

        JButton btnExport = new JButton("Export to PDF");
        btnExport.setBounds(845, 320, 215, 35);
        btnExport.setBackground(FIELD);
        btnExport.setForeground(TEXT);
        btnExport.setFocusPainted(false);
        btnExport.setBorderPainted(false);
        add(btnExport);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(845, 365, 215, 35);
        btnRefresh.setBackground(FIELD);
        btnRefresh.setForeground(TEXT);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorderPainted(false);
        add(btnRefresh);

        // ==========================================
        // 7. ACADEMIC RECORD TABLE
        // ==========================================

        JLabel lblRecord = new JLabel("Academic Record");
        lblRecord.setFont(new Font("Arial", Font.BOLD, 14));
        lblRecord.setForeground(TEXT);
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

        Object[][] data = {};

        DefaultTableModel tableModel =
            new DefaultTableModel(data, columns);

        JTable table = new JTable(tableModel);

        table.setRowHeight(30);
        table.setBackground(CARD);
        table.setForeground(TEXT);
        table.setGridColor(BORDER);
        table.setSelectionBackground(BLUE);
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setShowGrid(true);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(HEADER);
        tableHeader.setForeground(TEXT);
        tableHeader.setFont(
            new Font("Arial", Font.BOLD, 12)
        );
        tableHeader.setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(75);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 305, 630, 290);
        scrollPane.getViewport().setBackground(CARD);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        add(scrollPane);

        // ==========================================
        // 8. FOOTER
        // ==========================================

        JLabel lblTotalUnits = new JLabel("Total Units:");
        lblTotalUnits.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotalUnits.setForeground(TEXT);
        lblTotalUnits.setBounds(200, 610, 150, 20);
        add(lblTotalUnits);

        JLabel lblDisclaimer = new JLabel(
            "Note: This is not an official transcript. " +
            "For official use, please request from the Registrar's Office."
        );
        lblDisclaimer.setFont(
            new Font("Arial", Font.PLAIN, 10)
        );
        lblDisclaimer.setForeground(MUTED);
        lblDisclaimer.setBounds(460, 610, 550, 20);
        add(lblDisclaimer);

    }

}
