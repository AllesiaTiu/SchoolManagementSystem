package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseBranch extends JFrame implements ActionListener {
    
    private CourseManager courseManager;
    private ArrayList<Course> courseList; // Explicit ArrayList
    
    private JButton students, courses, grades, transcript, report, attendance;
    private JPanel panelInputs, panelTitle, panelEnr;
    private JLabel titleCM, courseID, courseName, enrllStud, noStudenr;
    private JTextField cID_txt, cNM_txt;
    private JButton addBtn, updBtn, delBtn;
    
    private JPanel panelSort, pinkSort;
    private JLabel sortC, srchLbl;
    private JTextField inputCourse;
    private JButton sortBtn, srch2Btn;
    
    private JTable table;
    private DefaultTableModel tblM;
    private JScrollPane scroll;
    
    public CourseBranch() {
        courseManager = new CourseManager();
        courseList = new ArrayList<>();
        
        setTitle("School Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
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
        transcript.addActionListener(this);
        add(transcript);
        
        report = new JButton("Report");
        report.setBounds(20, 270, 150, 50);
        report.addActionListener(this);
        add(report);
        
        attendance = new JButton("Attendance");
        attendance.setBounds(20, 330, 150, 50);
        attendance.addActionListener(this);
        add(attendance);
        
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

        courseList = courseManager.getAllCourses();
        refreshTable(courseList);
    }
    
    private void refreshTable(ArrayList<Course> listToDisplay) {
        tblM.setRowCount(0);
        for (Course c : listToDisplay) {
            int enrolled = courseManager.getEnrolledCount(c.getcName());
            tblM.addRow(new Object[]{c.getcID(), c.getcName(), enrolled + " Students"});
        }
    }
    
    // ==========================================
    // MANUAL BINARY SEARCH FOR COURSES
    // ==========================================
    public int binarySearchCourse(ArrayList<Course> list, String target) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getcName().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            
            courseList = courseManager.getAllCourses();
            refreshTable(courseList);
            cID_txt.setText("");
            cNM_txt.setText("");
            JOptionPane.showMessageDialog(this, "Course added successfully to database!");
        } 
        else if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a course from the table to update.");
                return;
            }
            
            String oldId = tblM.getValueAt(selectedRow, 0).toString();
            String newId = cID_txt.getText().trim();
            String newName = cNM_txt.getText().trim();
            
            if (courseManager.updateCourse(oldId, newId, newName)) {
                courseList = courseManager.getAllCourses();
                refreshTable(courseList);
                cID_txt.setText("");
                cNM_txt.setText("");
                JOptionPane.showMessageDialog(this, "Course updated successfully!");
            }
        }
        else if (e.getSource() == delBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String id = tblM.getValueAt(selectedRow, 0).toString();
                if (JOptionPane.showConfirmDialog(this, "Delete this course?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    courseManager.deleteCourse(id);
                    courseList = courseManager.getAllCourses();
                    refreshTable(courseList);
                    cID_txt.setText("");
                    cNM_txt.setText("");
                }
            }
        }
        // --- SEARCH VIA BINARY SEARCH ---
        else if (e.getSource() == srch2Btn) {
            String query = inputCourse.getText().trim();
            if (query.isEmpty()) {
                refreshTable(courseManager.getAllCourses());
                return;
            }

            // 1. Sort the ArrayList before binary searching
            courseList = courseManager.getAllCourses();
            courseManager.sortCoursesByName(courseList);

            // 2. Perform Binary Search
            int foundIdx = binarySearchCourse(courseList, query);

            if (foundIdx != -1) {
                ArrayList<Course> singleMatch = new ArrayList<>();
                singleMatch.add(courseList.get(foundIdx));
                refreshTable(singleMatch);
                JOptionPane.showMessageDialog(this, "Course found using Binary Search at sorted position [" + foundIdx + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "Course not found (Binary Search requires exact course name).");
                refreshTable(courseManager.getAllCourses());
            }
        }
        else if (e.getSource() == sortBtn) {
            courseList = courseManager.getAllCourses();
            courseManager.sortCoursesByName(courseList);
            refreshTable(courseList);
            JOptionPane.showMessageDialog(this, "Courses sorted alphabetically!");
        }
        else if (e.getSource() == grades) {
            new GradesBranch().setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        }
        else if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        }
    }
}