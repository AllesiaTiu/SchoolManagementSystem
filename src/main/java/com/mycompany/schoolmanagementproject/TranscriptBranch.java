
package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.EmptyBorder;

public class TranscriptBranch extends JFrame {

    // ==========================================
    // COLORS
    // ==========================================

    private final Color BG = new Color(239, 245, 249);
    private final Color SIDEBAR = new Color(28, 48, 62);
    private final Color HEADER = new Color(48, 77, 94);
    private final Color BLUE = new Color(48, 105, 174);
    private final Color TEXT = new Color(35, 49, 60);
    private final Color MUTED = new Color(110, 125, 135);
    private final Color BORDER = new Color(220, 229, 234);

    public TranscriptBranch() {

        setTitle("School Management System");
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

        JLabel brand = new JLabel("◆ School System");
        brand.setBounds(18, 20, 160, 30);
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("Arial", Font.BOLD, 15));
        sidebar.add(brand);

        String[] menuItems = {
            "⌂   Dashboard",
            "♟   Student",
            "▣   Course",
            "▤   Grade",
            "▤   Transcript",
            "⚙   Settings"
        };

        int menuY = 70;

        for (String item : menuItems) {

            JButton button = new JButton(item);
            button.setBounds(0, menuY, 180, 48);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            if (item.contains("Transcript")) {
                button.setBackground(BLUE);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(SIDEBAR);
                button.setForeground(new Color(225, 233, 239));
            }

            sidebar.add(button);
            menuY += 48;
        }

        JButton btnLogout = new JButton("⇥   Logout");
        btnLogout.setBounds(0, 645, 180, 45);
        btnLogout.setBackground(SIDEBAR);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        sidebar.add(btnLogout);

        // ==========================================
        // 2. HEADER
        // ==========================================

        JPanel header = new JPanel();
        header.setBounds(180, 0, 920, 80);
        header.setBackground(HEADER);
        header.setLayout(null);
        add(header);

        JLabel lblHeaderIcon = new JLabel("▤");
        lblHeaderIcon.setBounds(25, 15, 40, 45);
        lblHeaderIcon.setFont(new Font("Arial", Font.BOLD, 34));
        lblHeaderIcon.setForeground(Color.WHITE);
        header.add(lblHeaderIcon);

        JLabel lblTitle = new JLabel("Transcript");
        lblTitle.setBounds(75, 15, 300, 45);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ==========================================
        // 3. SEARCH STUDENT
        // ==========================================

        JLabel lblSearch = new JLabel("Search Student:");
        lblSearch.setBounds(200, 95, 130, 30);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
        lblSearch.setForeground(TEXT);
        add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(330, 95, 300, 36);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER),
            new EmptyBorder(5, 10, 5, 10)
        ));
        txtSearch.setToolTipText("Enter Student ID or Name");
        add(txtSearch);

        JButton btnSearch = new JButton("⌕   Search");
        btnSearch.setBounds(645, 95, 110, 36);
        btnSearch.setBackground(BLUE);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 13));
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        add(btnSearch);

        // ==========================================
        // 4. STUDENT INFORMATION CARD
        // ==========================================

        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(200, 145, 630, 145);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        infoPanel.setLayout(null);
        add(infoPanel);

        // Student Avatar

        JLabel lblAvatar = new JLabel("●", SwingConstants.CENTER);
        lblAvatar.setBounds(18, 22, 90, 95);
        lblAvatar.setFont(new Font("Arial", Font.BOLD, 65));
        lblAvatar.setForeground(new Color(82, 104, 124));
        lblAvatar.setBackground(new Color(229, 237, 243));
        lblAvatar.setOpaque(true);
        infoPanel.add(lblAvatar);

        // Student Details - EMPTY

        JLabel lblId = new JLabel("Student ID:");
        lblId.setBounds(125, 22, 120, 22);
        lblId.setFont(new Font("Arial", Font.BOLD, 13));
        infoPanel.add(lblId);

        JLabel lblIdValue = new JLabel("");
        lblIdValue.setBounds(245, 22, 120, 22);
        infoPanel.add(lblIdValue);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(125, 49, 120, 22);
        lblName.setFont(new Font("Arial", Font.BOLD, 13));
        infoPanel.add(lblName);

        JLabel lblNameValue = new JLabel("");
        lblNameValue.setBounds(245, 49, 150, 22);
        infoPanel.add(lblNameValue);

        JLabel lblProgram = new JLabel("Program:");
        lblProgram.setBounds(125, 76, 120, 22);
        lblProgram.setFont(new Font("Arial", Font.BOLD, 13));
        infoPanel.add(lblProgram);

        JLabel lblProgramValue = new JLabel("");
        lblProgramValue.setBounds(245, 76, 120, 22);
        infoPanel.add(lblProgramValue);

        JLabel lblYear = new JLabel("Year Level:");
        lblYear.setBounds(125, 103, 120, 22);
        lblYear.setFont(new Font("Arial", Font.BOLD, 13));
        infoPanel.add(lblYear);

        JLabel lblYearValue = new JLabel("");
        lblYearValue.setBounds(245, 103, 120, 22);
        infoPanel.add(lblYearValue);

        // Academic Year - EMPTY

        JLabel lblAY = new JLabel("Academic Year:");
        lblAY.setBounds(390, 28, 110, 25);
        lblAY.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lblAY);

        JComboBox<String> cbAY = new JComboBox<>();
        cbAY.setBounds(490, 25, 125, 32);
        infoPanel.add(cbAY);

        // Semester - EMPTY

        JLabel lblSem = new JLabel("Semester:");
        lblSem.setBounds(390, 85, 100, 25);
        lblSem.setFont(new Font("Arial", Font.BOLD, 12));
        infoPanel.add(lblSem);

        JComboBox<String> cbSem = new JComboBox<>();
        cbSem.setBounds(490, 82, 125, 32);
        infoPanel.add(cbSem);

        // ==========================================
        // 5. GWA CARD
        // ==========================================

        JPanel gwaPanel = new JPanel();
        gwaPanel.setBounds(845, 145, 215, 145);
        gwaPanel.setBackground(Color.WHITE);
        gwaPanel.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        gwaPanel.setLayout(null);
        add(gwaPanel);

        JLabel lblGwaTitle = new JLabel(
            "General Weighted Average (GWA)"
        );

        lblGwaTitle.setBounds(12, 15, 200, 22);
        lblGwaTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        lblGwaTitle.setForeground(MUTED);
        gwaPanel.add(lblGwaTitle);

        JLabel lblGwaVal = new JLabel("");
        lblGwaVal.setBounds(12, 43, 180, 40);
        lblGwaVal.setFont(new Font("Arial", Font.BOLD, 28));
        lblGwaVal.setForeground(TEXT);
        gwaPanel.add(lblGwaVal);

        JLabel lblEq = new JLabel("Equivalent Grade");
        lblEq.setBounds(12, 95, 150, 18);
        lblEq.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEq.setForeground(MUTED);
        gwaPanel.add(lblEq);

        JLabel lblEqVal = new JLabel("");
        lblEqVal.setBounds(12, 112, 100, 25);
        lblEqVal.setFont(new Font("Arial", Font.BOLD, 17));
        lblEqVal.setForeground(TEXT);
        gwaPanel.add(lblEqVal);

        // ==========================================
        // 6. ACADEMIC RECORD TITLE
        // ==========================================

        JLabel lblRecord = new JLabel("Academic Record");
        lblRecord.setBounds(200, 310, 250, 30);
        lblRecord.setFont(new Font("Arial", Font.BOLD, 18));
        lblRecord.setForeground(TEXT);
        add(lblRecord);

        // ==========================================
        // 7. ACADEMIC RECORD TABLE - EMPTY
        // ==========================================

        String[] columns = {
            "No.",
            "Subject Code",
            "Subject Title",
            "Units",
            "Grade",
            "Remarks"
        };

        Object[][] data = {};

        DefaultTableModel tableModel = new DefaultTableModel(
            data, columns
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);

        table.setRowHeight(39);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setForeground(TEXT);
        table.setBackground(Color.WHITE);
        table.setGridColor(BORDER);
        table.setSelectionBackground(new Color(218, 232, 248));
        table.setSelectionForeground(TEXT);
        table.setShowGrid(true);
        table.setIntercellSpacing(new java.awt.Dimension(1, 1));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(226, 235, 241));
        tableHeader.setForeground(TEXT);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));
        tableHeader.setPreferredSize(
            new java.awt.Dimension(0, 38)
        );
        tableHeader.setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(65);
        table.getColumnModel().getColumn(4).setPreferredWidth(65);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(200, 345, 630, 265);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        // ==========================================
        // 8. RIGHT ACTION BUTTONS
        // ==========================================

        JButton btnPrint = new JButton("▣   Print Transcript");
        btnPrint.setBounds(845, 310, 215, 48);
        btnPrint.setBackground(BLUE);
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFont(new Font("Arial", Font.BOLD, 13));
        btnPrint.setFocusPainted(false);
        btnPrint.setBorderPainted(false);
        add(btnPrint);

        JButton btnExport = new JButton("Export to PDF");
        btnExport.setBounds(845, 370, 215, 45);
        btnExport.setBackground(Color.WHITE);
        btnExport.setForeground(TEXT);
        btnExport.setFont(new Font("Arial", Font.PLAIN, 13));
        btnExport.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        btnExport.setFocusPainted(false);
        add(btnExport);

        JButton btnRefresh = new JButton("⟳   Refresh");
        btnRefresh.setBounds(845, 425, 215, 45);
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setForeground(TEXT);
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 13));
        btnRefresh.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        btnRefresh.setFocusPainted(false);
        add(btnRefresh);

        // ==========================================
        // 9. FOOTER
        // ==========================================

        JPanel footer = new JPanel();
        footer.setBounds(200, 620, 860, 55);
        footer.setBackground(Color.WHITE);
        footer.setBorder(
            BorderFactory.createLineBorder(BORDER)
        );
        footer.setLayout(null);
        add(footer);

        JLabel lblTotalUnits = new JLabel("Total Units:");
        lblTotalUnits.setBounds(15, 12, 180, 25);
        lblTotalUnits.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalUnits.setForeground(TEXT);
        footer.add(lblTotalUnits);

        JLabel lblDisclaimer = new JLabel(
            "Note: This is not an official transcript. " +
            "For official use, please request from the Registrar's Office."
        );

        lblDisclaimer.setBounds(350, 12, 500, 25);
        lblDisclaimer.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDisclaimer.setForeground(MUTED);
        footer.add(lblDisclaimer);

        // ==========================================
        // 10. BUTTON ACTIONS
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
