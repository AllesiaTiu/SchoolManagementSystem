package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseBranch extends JFrame implements ActionListener {
    
    private JButton students, courses, grades, transcript, report, attendance, addBtn, updBtn, delBtn, srchBtn, sortBtn, srch2Btn;
    private JPanel panelInputs, panelTitle, panelEnr, panelSort, pinkSort;
    private JLabel titleCM, courseID, courseName, enrllStud, noStudenr, sortC, srchLbl;
    private JTextField cID_txt, cNM_txt, inputCourse;
    private JTable table;
    private DefaultTableModel tblM;
    private JScrollPane scroll;
    
    public CourseBranch() {
        // title and layout for the school system
        setTitle("School Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        
        //Buttons for the site
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
        
        //label
        titleCM = new JLabel("Course Management");
        titleCM.setBounds(30, 0, 150, 30);
        
        //textfield or where to type
        
        cID_txt = new JTextField();
        cID_txt.setBounds(150, 35, 250, 20);
        
        cNM_txt = new JTextField();
        cNM_txt.setBounds(150, 65, 250, 20);
        
        sortC = new JLabel("Sort Courses");
        sortC.setBounds(30, 0, 150, 30);
        
        // panels for the extended function
        pinkSort = new JPanel();
        pinkSort.setSize(200, 30);
        pinkSort.setBackground(Color.pink);
        pinkSort.setLayout(null);
        pinkSort.add(sortC);
        
        panelTitle = new JPanel();
        panelTitle.setSize(600, 30);
        panelTitle.setBackground(Color.pink);
        panelTitle.setLayout(null);
        panelTitle.add(titleCM);
        
        //number of enrolled students
        noStudenr = new JLabel("0 Students");
        noStudenr.setBounds(5, -5, 150, 30);
        
        panelEnr = new JPanel();
        panelEnr.setBounds(152, 95, 245, 20);
        panelEnr.setBackground(Color.white);
        panelEnr.setLayout(null);
        panelEnr.add(noStudenr);
        
        courseID = new JLabel("Course ID: ");
        courseID.setBounds(30, 30, 100, 30);
        
        courseName = new JLabel("Course Name: ");
        courseName.setBounds(30, 60, 100, 30);
  
        enrllStud = new JLabel("Enrolled Students: ");
        enrllStud.setBounds(30, 90, 120, 30);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(30, 190, 100, 40);
        addBtn.addActionListener(this); 
        
        updBtn = new JButton("Update");
        updBtn.setBounds(140, 190, 100, 40);
        updBtn.addActionListener(this); 
        
        delBtn = new JButton("Delete");
        delBtn.setBounds(250, 190, 100, 40);
        delBtn.addActionListener(this); 
        
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 250);
        panelInputs.setLayout(null);
        panelInputs.add(courseID);
        panelInputs.add(courseName);
        panelInputs.add(enrllStud);
        panelInputs.add(panelTitle);
        panelInputs.add(cID_txt);
        panelInputs.add(cNM_txt);
        panelInputs.add(panelEnr);
        panelInputs.add(addBtn);
        panelInputs.add(updBtn);
        panelInputs.add(delBtn);
        
        add(panelInputs);
        
        sortBtn = new JButton("Sort by Name(A-Z)");
        sortBtn.setBounds(20, 190, 150, 40);
        
        srchLbl = new JLabel("Search Course");
        srchLbl.setBounds(30, 60, 100, 30);
        
        inputCourse = new JTextField();
        inputCourse.setBounds(30, 90, 150, 20);
        
        srch2Btn = new JButton("Search");
        srch2Btn.setBounds(30, 120, 100, 40);
        
        panelSort = new JPanel();
        panelSort.setBackground(Color.LIGHT_GRAY);
        panelSort.setBounds(790, 30, 200, 250);
        panelSort.setLayout(null);
        panelSort.add(pinkSort);
        panelSort.add(sortBtn);
        panelSort.add(srchLbl);
        panelSort.add(inputCourse);
        panelSort.add(srch2Btn);
        
        add(panelSort);
        
        tblM = new DefaultTableModel();
        tblM.addColumn("Course ID");
        tblM.addColumn("Course Name");
        tblM.addColumn("Enrolled Students");
        table = new JTable(tblM);
        scroll = new JScrollPane(table);
        scroll.setBounds(180, 290, 700, 200);
        add(scroll);
    }
    
    // Override the ActionListener method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String id = cID_txt.getText().trim();
            String name = cNM_txt.getText().trim();
            String enrolled = noStudenr.getText().trim();
            
            // Check if inputs are empty
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Please fill in both Course ID and Course Name.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check for duplicate Course ID
            for (int i = 0; i < tblM.getRowCount(); i++) {
                if (tblM.getValueAt(i, 0).toString().equalsIgnoreCase(id)) {
                    JOptionPane.showMessageDialog(this, 
                            "A course with this ID already exists.", 
                            "Duplicate ID", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            // Add the row to the table model
            tblM.addRow(new Object[]{id, name, enrolled});
            
            // Reset input fields
            cID_txt.setText("");
            cNM_txt.setText("");
            cID_txt.requestFocus();
            
            JOptionPane.showMessageDialog(this, "Course added successfully!");
        } 
        else if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            
            // Check if a course is selected from the table
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, 
                        "Please select a course to update.", 
                        "Selection Error", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String id = cID_txt.getText().trim();
            String name = cNM_txt.getText().trim();
            
            // Check if inputs are empty
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Please fill in both Course ID and Course Name.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check for duplicate ID on other rows
            for (int i = 0; i < tblM.getRowCount(); i++) {
                if (i != selectedRow && tblM.getValueAt(i, 0).toString().equalsIgnoreCase(id)) {
                    JOptionPane.showMessageDialog(this, 
                            "Another course with this ID already exists.", 
                            "Duplicate ID", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            // Update the selected row with new values
            tblM.setValueAt(id, selectedRow, 0);
            tblM.setValueAt(name, selectedRow, 1);
            
            // Clear fields
            cID_txt.setText("");
            cNM_txt.setText("");
            
            JOptionPane.showMessageDialog(this, "Course updated successfully!");
        }
        else if (e.getSource() == delBtn) {
            int selectedRow = table.getSelectedRow();
            
            // Check if a course is selected from the table
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, 
                        "Please select a course to delete.", 
                        "Selection Error", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Confirmation prompt
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete this course?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Remove row from the table model
                tblM.removeRow(selectedRow);
                
                // Clear fields
                cID_txt.setText("");
                cNM_txt.setText("");
                
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
            }
        }
    }
}