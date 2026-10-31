package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseBranch extends JFrame implements ActionListener {
    
    private CourseManager courseManager;
    
    // Navigation Buttons
    private JButton students, courses, grades, transcript, report, attendance;
    
    // Input Panel Elements
    private JPanel panelInputs, panelTitle, panelEnr;
    private JLabel titleCM, courseID, courseName, enrllStud, noStudenr;
    private JTextField cID_txt, cNM_txt;
    private JButton addBtn, updBtn, delBtn;
    
    // Sort & Search Panel Elements
    private JPanel panelSort, pinkSort;
    private JLabel sortC, srchLbl;
    private JTextField inputCourse;
    private JButton sortBtn, srch2Btn;
    
    // Table Elements
    private JTable table;
    private DefaultTableModel tblM;
    private JScrollPane scroll;
    
    public CourseBranch() {
        courseManager = new CourseManager();
        
        setTitle("School Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // --- 1. Navigation Sidebar Buttons ---
        students = new JButton("Students");
        students.setBounds(20, 30, 150, 50);
        students.addActionListener(this);
        add(students);
        
        courses = new JButton("Courses");
        courses.setBounds(20, 90, 150, 50);
        courses.addActionListener(this);
        add(courses);
        
        grades = new JButton("Grades");
        grades.setBounds(20, 150, 150, 50);
        grades.addActionListener(this);
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
        
        // --- 2. Course Input Panel ---
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 250);
        panelInputs.setLayout(null);
        
        titleCM = new JLabel("Course Management");
        titleCM.setBounds(30, 0, 150, 30);
        
        panelTitle = new JPanel();
        panelTitle.setBounds(0, 0, 600, 30);
        panelTitle.setBackground(Color.pink);
        panelTitle.setLayout(null);
        panelTitle.add(titleCM);
        panelInputs.add(panelTitle);
        
        courseID = new JLabel("Course ID: ");
        courseID.setBounds(30, 30, 100, 30);
        panelInputs.add(courseID);
        
        cID_txt = new JTextField();
        cID_txt.setBounds(150, 35, 250, 20);
        panelInputs.add(cID_txt);
        
        courseName = new JLabel("Course Name: ");
        courseName.setBounds(30, 60, 100, 30);
        panelInputs.add(courseName);
        
        cNM_txt = new JTextField();
        cNM_txt.setBounds(150, 65, 250, 20);
        panelInputs.add(cNM_txt);
        
        enrllStud = new JLabel("Enrolled Students: ");
        enrllStud.setBounds(30, 90, 120, 30);
        panelInputs.add(enrllStud);
        
        noStudenr = new JLabel("0 Students");
        noStudenr.setBounds(5, -5, 150, 30);
        
        panelEnr = new JPanel();
        panelEnr.setBounds(152, 95, 245, 20);
        panelEnr.setBackground(Color.white);
        panelEnr.setLayout(null);
        panelEnr.add(noStudenr);
        panelInputs.add(panelEnr);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(30, 190, 100, 40);
        addBtn.addActionListener(this); 
        panelInputs.add(addBtn);
        
        updBtn = new JButton("Update");
        updBtn.setBounds(140, 190, 100, 40);
        updBtn.addActionListener(this); 
        panelInputs.add(updBtn);
        
        delBtn = new JButton("Delete");
        delBtn.setBounds(250, 190, 100, 40);
        delBtn.addActionListener(this); 
        panelInputs.add(delBtn);
        
        add(panelInputs);
        
        // --- 3. Search & Sort Panel ---
        panelSort = new JPanel();
        panelSort.setBackground(Color.LIGHT_GRAY);
        panelSort.setBounds(790, 30, 200, 250);
        panelSort.setLayout(null);
        
        sortC = new JLabel("Sort Courses");
        sortC.setBounds(30, 0, 150, 30);
        
        pinkSort = new JPanel();
        pinkSort.setBounds(0, 0, 200, 30);
        pinkSort.setBackground(Color.pink);
        pinkSort.setLayout(null);
        pinkSort.add(sortC);
        panelSort.add(pinkSort);
        
        srchLbl = new JLabel("Search Course");
        srchLbl.setBounds(30, 60, 100, 30);
        panelSort.add(srchLbl);
        
        inputCourse = new JTextField();
        inputCourse.setBounds(30, 90, 150, 20);
        panelSort.add(inputCourse);
        
        srch2Btn = new JButton("Search");
        srch2Btn.setBounds(30, 120, 100, 40);
        srch2Btn.addActionListener(this);
        panelSort.add(srch2Btn);
        
        sortBtn = new JButton("Sort by Name(A-Z)");
        sortBtn.setBounds(20, 190, 150, 40);
        sortBtn.addActionListener(this);
        panelSort.add(sortBtn);
        
        add(panelSort);
        
        // --- 4. Table Setup ---
        tblM = new DefaultTableModel();
        tblM.addColumn("Course ID");
        tblM.addColumn("Course Name");
        tblM.addColumn("Enrolled Students");
        
        table = new JTable(tblM);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    cID_txt.setText(tblM.getValueAt(selectedRow, 0).toString());
                    cNM_txt.setText(tblM.getValueAt(selectedRow, 1).toString());
                    noStudenr.setText(tblM.getValueAt(selectedRow, 2).toString());
                }
            }
        });
        
        scroll = new JScrollPane(table);
        scroll.setBounds(180, 290, 700, 200);
        add(scroll);

        // Load existing courses from MySQL on launch
        refreshTable(courseManager.getAllCourses());
    }
    
    // Renders table rows with LIVE enrolled count from DB
    private void refreshTable(List<Course> listToDisplay) {
        tblM.setRowCount(0);
        for (Course c : listToDisplay) {
            int enrolled = courseManager.getEnrolledCount(c.getcName());
            tblM.addRow(new Object[]{c.getcID(), c.getcName(), enrolled + " Students"});
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // --- ADD BUTTON ---
        if (e.getSource() == addBtn) {
            String id = cID_txt.getText().trim();
            String name = cNM_txt.getText().trim();
            
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both Course ID and Course Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean added = courseManager.addCourse(new Course(id, name));
            if (!added) {
                JOptionPane.showMessageDialog(this, "A course with this ID already exists.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            refreshTable(courseManager.getAllCourses());
            cID_txt.setText("");
            cNM_txt.setText("");
            cID_txt.requestFocus();
            JOptionPane.showMessageDialog(this, "Course added successfully to database!");
        } 
        
        // --- UPDATE BUTTON ---
        else if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a course from the table to update.", "Selection Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String oldId = tblM.getValueAt(selectedRow, 0).toString();
            String newId = cID_txt.getText().trim();
            String newName = cNM_txt.getText().trim();
            
            if (newId.isEmpty() || newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both Course ID and Course Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean updated = courseManager.updateCourse(oldId, newId, newName);
            if (updated) {
                refreshTable(courseManager.getAllCourses());
                cID_txt.setText("");
                cNM_txt.setText("");
                JOptionPane.showMessageDialog(this, "Course updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. ID might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // --- DELETE BUTTON ---
        else if (e.getSource() == delBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String id = tblM.getValueAt(selectedRow, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this course?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                courseManager.deleteCourse(id);
                refreshTable(courseManager.getAllCourses());
                cID_txt.setText("");
                cNM_txt.setText("");
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
            }
        }
        
        // --- SEARCH BUTTON ---
        else if (e.getSource() == srch2Btn) {
            String query = inputCourse.getText().trim();
            if (query.isEmpty()) {
                refreshTable(courseManager.getAllCourses());
            } else {
                refreshTable(courseManager.searchCourses(query));
            }
        }
        
        // --- SORT BUTTON ---
        else if (e.getSource() == sortBtn) {
            refreshTable(courseManager.sortCByName());
            JOptionPane.showMessageDialog(this, "Courses sorted alphabetically!");
        }
        
        // --- NAVIGATION BUTTONS ---
        else if (e.getSource() == courses) {
            // Already inside CourseBranch
        }
        else if (e.getSource() == grades) {
            new GradesBranch().setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        }
    }
}