package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradesBranch extends JFrame implements ActionListener {
    
    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
    JButton addBtn;
    JButton updBtn;
    JButton deleteBtn;
    JButton computeAve;
    JButton searchBtn;
    JButton sortBtn;
    
    JLabel title;
    JLabel studentId;
    JLabel courseId;
    JLabel score;
    JLabel gradesList;
    
    JTextField idInput;
    String[] courseList = {
        "Programming 3", 
        "Data Structures", 
        "PathFit", 
        "RVA", 
        "DCAN", 
        "Operating System"
    };
    JComboBox<String> courseCmb;
    JTextField scoreInput;
    
    // panels
    JPanel panelInputs;
    JPanel panelTitle;
    JPanel listTitle;
    
    // table 
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
            
    GradesBranch() {
        setTitle("Student Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        courseId = new JLabel("Course ID:");
        courseId.setBounds(30, 60, 100, 30);
        
        courseCmb = new JComboBox<>(courseList);
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
        
        gradesList = new JLabel("Grades List");
        gradesList.setBounds(30, 0, 150, 30);
        
        listTitle = new JPanel();
        listTitle.setBackground(Color.pink);
        listTitle.setBounds(180, 240, 600, 30);
        listTitle.setLayout(null);
        listTitle.add(gradesList);
        add(listTitle);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Programming 3");
        tableModel.addColumn("Data Structures");
        tableModel.addColumn("PathFit");
        tableModel.addColumn("RVA");
        tableModel.addColumn("DCAN");
        tableModel.addColumn("Operating System");
        tableModel.addColumn("GWA");
        
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 280, 800, 200);
        add(scrollPane);
        
        // Navigation button listeners
        grades.addActionListener(this);
        students.addActionListener(this);
        courses.addActionListener(this);
        
        // Action button listeners
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        computeAve.addActionListener(this);
        
        // Clicking a row loads student ID into input field
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    idInput.setText(tableModel.getValueAt(row, 0).toString());
                }
            }
        });
    }

    // Helper method to clear all input fields
    private void clearFields() {
        idInput.setText("");
        scoreInput.setText("");
        courseCmb.setSelectedIndex(0);
        table.clearSelection();
    }

    // Helper method: Finds if a student already exists in the table
    private int findStudentRow(String name) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Navigation
        if (e.getSource() == courses) {
            CourseBranch c = new CourseBranch();
            c.setVisible(true);
        }
        if (e.getSource() == grades) {
            GradesBranch g = new GradesBranch();
            g.setVisible(true);
        }
        if (e.getSource() == students) {
            StudentBranch s = new StudentBranch();
            s.setVisible(true);
        }

        // --- 1. ADD BUTTON LOGIC ---
        if (e.getSource() == addBtn) {
            String id = idInput.getText().trim();
            String scoreStr = scoreInput.getText().trim();

            if (id.isEmpty() || scoreStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Student ID and Score.");
                return;
            }

            try {
                Double.parseDouble(scoreStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Score must be a valid number.");
                return;
            }

            int colIndex = courseCmb.getSelectedIndex() + 1;
            int existingRow = findStudentRow(id);

            if (existingRow != -1) {
                tableModel.setValueAt(scoreStr, existingRow, colIndex);
            } else {
                Object[] newRow = new Object[8];
                newRow[0] = id;
                for (int i = 1; i < 8; i++) {
                    newRow[i] = "";
                }
                newRow[colIndex] = scoreStr;
                tableModel.addRow(newRow);
            }

            // Clears both ID and Score
            clearFields();
            JOptionPane.showMessageDialog(this, "Grade recorded successfully!");
        }

        // --- 2. UPDATE BUTTON LOGIC ---
        if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row in the table to update.");
                return;
            }

            String id = idInput.getText().trim();
            String scoreStr = scoreInput.getText().trim();

            if (!id.isEmpty()) {
                tableModel.setValueAt(id, selectedRow, 0);
            }

            if (!scoreStr.isEmpty()) {
                try {
                    Double.parseDouble(scoreStr);
                    int colIndex = courseCmb.getSelectedIndex() + 1;
                    tableModel.setValueAt(scoreStr, selectedRow, colIndex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Score must be a valid number.");
                    return;
                }
            }

            // Clears both ID and Score
            clearFields();
            JOptionPane.showMessageDialog(this, "Record updated successfully!");
        }

        // --- 3. DELETE BUTTON LOGIC ---
        if (e.getSource() == deleteBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                // Clears both ID and Score
                clearFields();
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row in the table to delete.");
            }
        }

        // --- 4. COMPUTE AVERAGE (GWA) LOGIC ---
        if (e.getSource() == computeAve) {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "The table is empty.");
                return;
            }

            int selectedRow = table.getSelectedRow();
            int startRow = (selectedRow != -1) ? selectedRow : 0;
            int endRow = (selectedRow != -1) ? selectedRow + 1 : tableModel.getRowCount();

            for (int r = startRow; r < endRow; r++) {
                double total = 0;
                int count = 0;

                for (int c = 1; c <= 6; c++) {
                    Object val = tableModel.getValueAt(r, c);
                    if (val != null && !val.toString().trim().isEmpty()) {
                        try {
                            total += Double.parseDouble(val.toString().trim());
                            count++;
                        } catch (NumberFormatException ex) {
                            // Skip non-numeric values
                        }
                    }
                }

                if (count > 0) {
                    double ave = total / count;
                    tableModel.setValueAt(String.format("%.2f", ave), r, 7);
                } else {
                    tableModel.setValueAt("N/A", r, 7);
                }
            }

            JOptionPane.showMessageDialog(this, "GWA computed successfully!");
        }
    }
}