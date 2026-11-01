
package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TranscriptBranch extends JFrame {

    // COLORS
    private final Color BG = new Color(240, 243, 248);
    private final Color SIDEBAR = new Color(28, 43, 65);
    private final Color HEADER = new Color(41, 67, 98);
    private final Color BLUE = new Color(41, 103, 196);
    private final Color BORDER = new Color(220, 225, 235);
    private final Color TEXT = new Color(40, 50, 60);

    public TranscriptBranch() {

        setTitle("School Management System - Transcript");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(BG);

        // ==========================================
        // 1. SIDEBAR
        // ==========================================

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 180, 720);
        sidebar.setBackground(SIDEBAR);
        sidebar.setLayout(null);
        add(sidebar);

        JLabel brand = new JLabel("School System");
        brand.setBounds(20, 20, 150, 30);
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("Arial", Font.BOLD, 15));
        sidebar.add(brand);

        String[] menuItems = {
            "Dashboard",
            "Student",
            "Course",
            "Grade",
            "Transcript",
            "Settings"
        };

        int y = 70;

        for (String item : menuItems) {

            JButton button = new JButton(item);
            button.setBounds(15, y, 150, 38);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setFocusPainted(false);

            if (item.equals("Transcript")) {
                button.setBackground(BLUE);
                button.setForeground(Color.WHITE);
            }

            sidebar.add(button);
            y += 45;
        }

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(15, 620, 150, 35);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 12));
        sidebar.add(btnLogout);

        // ==========================================
        // 2. HEADER
        // ==========================================

        JPanel header = new JPanel();
        header.setBounds(180, 0, 920, 60);
        header.setBackground(HEADER);
        header.setLayout(null);
        add(header);

        JLabel lblTitle = new JLabel("Transcript");
        lblTitle.setBounds(20, 12, 250, 35);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ==========================================
        // 3. SEARCH SECTION
        // ==========================================

        JLabel lblSearch = new JLabel("Search Student:");
        lblSearch.setBounds(200, 80, 120, 30);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 13));
        lblSearch.setForeground(TEXT);
        add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(315, 80, 270, 30);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
        add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(595, 80, 95, 30);
        btnSearch.setBackground(BLUE);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        add(btnSearch);

        // ==========================================
        // 4. STUDENT INFORMATION CARD
        // ==========================================

        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(200, 125, 630, 135);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        infoPanel.setLayout(null);
        add(infoPanel);

        // Student Photo

        JLabel lblAvatar = new JLabel(
            "[ Photo ]", SwingConstants.CENTER
        );
        lblAvatar.setBounds(15, 20, 80, 85);
        lblAvatar.setOpaque(true);
        lblAvatar.setBackground(new Color(225, 235, 245));
        infoPanel.add(lblAvatar);

        // Student Details

        JLabel lblId = new JLabel("Student ID:");
        lblId.setBounds(115, 15, 200, 22);
        infoPanel.add(lblId);

        JLabel lblIdValue = new JLabel("");
        lblIdValue.setBounds(215, 15, 130, 22);
        infoPanel.add(lblIdValue);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(115, 42, 200, 22);
        infoPanel.add(lblName);

        JLabel lblNameValue = new JLabel("");
        lblNameValue.setBounds(215, 42, 130, 22);
        infoPanel.add(lblNameValue);

        JLabel lblProgram = new JLabel("Program:");
        lblProgram.setBounds(115, 69, 200, 22);
        infoPanel.add(lblProgram);

        JLabel lblProgramValue = new JLabel("");
        lblProgramValue.setBounds(215, 69, 130, 22);
        infoPanel.add(lblProgramValue);

        JLabel lblYear = new JLabel("Year Level:");
        lblYear.setBounds(115, 96, 200, 22);
        infoPanel.add(lblYear);

        JLabel lblYearValue = new JLabel("");
        lblYearValue.setBounds(215, 96, 130, 22);
        infoPanel.add(lblYearValue);

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
        // 5. GWA CARD
        // ==========================================

        JPanel gwaPanel = new JPanel();
        gwaPanel.setBounds(845, 125, 215, 135);
        gwaPanel.setBackground(Color.WHITE);
        gwaPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        gwaPanel.setLayout(null);
        add(gwaPanel);

        JLabel lblGwaTitle = new JLabel(
            "General Weighted Average (GWA)"
        );
        lblGwaTitle.setBounds(12, 12, 200, 20);
        lblGwaTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        gwaPanel.add(lblGwaTitle);

        JLabel lblGwaVal = new JLabel("");
        lblGwaVal.setBounds(12, 38, 150, 35);
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 26));
        gwaPanel.add(lblGwaVal);

        JLabel lblEq = new JLabel("Equivalent Grade");
        lblEq.setBounds(12, 80, 150, 18);
        lblEq.setFont(new Font("Arial", Font.PLAIN, 11));
        gwaPanel.add(lblEq);

        JLabel lblEqVal = new JLabel("");
        lblEqVal.setBounds(12, 100, 100, 20);
        lblEqVal.setFont(new Font("Arial", Font.BOLD, 15));
        gwaPanel.add(lblEqVal);

        // ==========================================
        // 6. ACTION BUTTONS
        // ==========================================

        JButton btnPrint = new JButton("Print Transcript");
        btnPrint.setBounds(845, 275, 215, 35);
        btnPrint.setBackground(BLUE);
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFocusPainted(false);
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
        lblRecord.setBounds(200, 275, 200, 25);
        lblRecord.setFont(new Font("Arial", Font.BOLD, 14));
        lblRecord.setForeground(TEXT);
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
            new DefaultTableModel(data, columns) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        JTable table = new JTable(tableModel);

        table.setRowHeight(26);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setGridColor(BORDER);
        table.setBackground(Color.WHITE);
        table.setForeground(TEXT);
        table.setSelectionBackground(new Color(210, 225, 245));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));
        tableHeader.setBackground(new Color(225, 233, 240));
        tableHeader.setForeground(TEXT);
        tableHeader.setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(260);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(55);
        table.getColumnModel().getColumn(5).setPreferredWidth(75);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 305, 630, 290);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        // ==========================================
        // 8. FOOTER
        // ==========================================

        JLabel lblTotalUnits = new JLabel("Total Units:");
        lblTotalUnits.setBounds(200, 610, 150, 20);
        lblTotalUnits.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotalUnits.setForeground(TEXT);
        add(lblTotalUnits);

        JLabel lblDisclaimer = new JLabel(
            "Note: This is not an official transcript. " +
            "For official use, please request from the Registrar's Office."
        );

        lblDisclaimer.setBounds(460, 610, 550, 20);
        lblDisclaimer.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDisclaimer.setForeground(Color.GRAY);
        add(lblDisclaimer);

        // ==========================================
        // 9. BUTTON ACTIONS
        // ==========================================

        btnRefresh.addActionListener(e -> {

            txtSearch.setText("");

            cbAY.removeAllItems();
            cbSem.removeAllItems();

            lblIdValue.setText("");
            lblNameValue.setText("");
            lblProgramValue.setText("");
            lblYearValue.setText("");

            lblGwaVal.setText("");
            lblEqVal.setText("");

            lblTotalUnits.setText("Total Units:");

            tableModel.setRowCount(0);
        });

        btnPrint.addActionListener(e -> {

            try {
                table.print();

            } catch (java.awt.print.PrinterException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Unable to print transcript: " + ex.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

    }

}
