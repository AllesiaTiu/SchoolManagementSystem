package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.manager.GradesManager;
import com.mycompany.schoolmanagementproject.manager.StudentManager;
import com.mycompany.schoolmanagementproject.model.Grades;
import com.mycompany.schoolmanagementproject.model.Student;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GradesBranch extends JFrame implements ActionListener {
    
    private GradesManager gradesManager = new GradesManager();
    private ArrayList<Grades> gradesList;
    private ArrayList<Student> allStudents;

    // Navigation Buttons
    JButton students, courses, grades, transcript, report, attendance;
    JButton addBtn, updBtn, deleteBtn, computeAve;
    
    JTextField searchTxt;
    JButton searchBtn;
    
    JLabel title, studentLbl, courseLbl, scoreLbl, gradesListTitle;
    
    // SMART DROPDOWNS
    JComboBox<String> studentCmb; // Shows "ID - Name"
    JComboBox<String> courseCmb;  // Shows ONLY that student's enrolled courses!
    JTextField scoreInput;
    
    JPanel panelInputs, panelTitle, listTitle;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
            
    public GradesBranch() {
        setTitle("School Management System - Grades Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        gradesList = new ArrayList<>();
        allStudents = new ArrayList<>();

        // --- 1. Sidebar Navigation ---
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
        
        // --- 2. Input Panel ---
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setSize(600, 30);
        panelTitle.setLayout(null);
        
        title = new JLabel("Grades Management");
        title.setBounds(30, 0, 150, 30);
        panelTitle.add(title);
        
        studentLbl = new JLabel("Select Student:");
        studentLbl.setBounds(30, 35, 110, 25);
        
        studentCmb = new JComboBox<>();
        studentCmb.setBounds(140, 35, 280, 25);
        studentCmb.addActionListener(this); // Listens for student selection!
        
        courseLbl = new JLabel("Enrolled Course:");
        courseLbl.setBounds(30, 68, 110, 25);
        
        courseCmb = new JComboBox<>();
        courseCmb.setBounds(140, 68, 280, 25);
        courseCmb.addActionListener(this); // Listens for course selection to auto-fill score!
        
        scoreLbl = new JLabel("Score:");
        scoreLbl.setBounds(30, 100, 100, 25);
        
        scoreInput = new JTextField();
        scoreInput.setBounds(140, 100, 280, 22);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(50, 135, 100, 40);
        
        updBtn = new JButton("Update");
        updBtn.setBounds(160, 135, 100, 40);
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(270, 135, 100, 40);
        
        computeAve = new JButton("Average");
        computeAve.setBounds(380, 135, 100, 40);
       
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 200);
        panelInputs.setLayout(null);
        panelInputs.add(panelTitle);
        panelInputs.add(studentLbl);
        panelInputs.add(studentCmb);
        panelInputs.add(courseLbl);
        panelInputs.add(courseCmb);
        panelInputs.add(scoreLbl);
        panelInputs.add(scoreInput);
        panelInputs.add(addBtn);
        panelInputs.add(updBtn);
        panelInputs.add(deleteBtn);
        panelInputs.add(computeAve);
        add(panelInputs);
        
        // --- 3. Table Header & Search ---
        gradesListTitle = new JLabel("Grades List");
        gradesListTitle.setBounds(30, 0, 150, 30);
        
        listTitle = new JPanel();
        listTitle.setBackground(Color.pink);
        listTitle.setBounds(180, 240, 600, 30);
        listTitle.setLayout(null);
        listTitle.add(gradesListTitle);
        add(listTitle);

        searchTxt = new JTextField();
        searchTxt.setBounds(790, 245, 110, 22);
        add(searchTxt);

        searchBtn = new JButton("Search ID");
        searchBtn.setBounds(905, 245, 80, 22);
        searchBtn.addActionListener(this);
        add(searchBtn);
        
        // --- 4. Table ---
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Score");
        
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 280, 800, 350);
        add(scrollPane);
        
        // Listeners
        grades.addActionListener(this);
        students.addActionListener(this);
        courses.addActionListener(this);
        attendance.addActionListener(this);
        report.addActionListener(this);
        transcript.addActionListener(this);
        
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        computeAve.addActionListener(this);
        
        // Clicking a row in the table automatically selects that student and course
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String selectedId = tableModel.getValueAt(row, 0).toString();
                    String selectedCourse = tableModel.getValueAt(row, 1).toString();
                    String score = tableModel.getValueAt(row, 2).toString();

                    selectStudentInDropdown(selectedId);
                    courseCmb.setSelectedItem(selectedCourse);
                    scoreInput.setText(score);
                }
            }
        });

        // Initialize Student list and Table data
        loadStudentDropdown();
        refreshTable();
    }

    // Populate student dropdown from database
    private void loadStudentDropdown() {
        studentCmb.removeActionListener(this); // Temporarily pause listener while loading
        studentCmb.removeAllItems();
        studentCmb.addItem("-- Select Student --");
        
        allStudents = StudentManager.getAllStudents();
        for (Student s : allStudents) {
            studentCmb.addItem(s.getID() + " - " + s.getName());
        }
        studentCmb.addActionListener(this);
    }

    // Extract student ID from the combo item "2026-XXXX-BN-0 - Name"
    private String getSelectedStudentId() {
        if (studentCmb.getSelectedIndex() <= 0) return null;
        String item = studentCmb.getSelectedItem().toString();
        return item.split(" - ")[0].trim();
    }

    // Automatically loads ONLY the courses the selected student enrolled in!
    private void updateCoursesForSelectedStudent() {
        courseCmb.removeActionListener(this);
        courseCmb.removeAllItems();
        scoreInput.setText("");

        String studentId = getSelectedStudentId();
        if (studentId == null) {
            courseCmb.addItem("-- Select a student first --");
            courseCmb.addActionListener(this);
            return;
        }

        ArrayList<String> enrolled = StudentManager.getEnrolledCourses(studentId);
        if (enrolled.isEmpty()) {
            courseCmb.addItem("-- No Enrolled Courses --");
        } else {
            for (String cName : enrolled) {
                courseCmb.addItem(cName);
            }
            checkAndPreloadGrade();
        }
        courseCmb.addActionListener(this);
    }

    // Preloads existing score if grade is already recorded
    private void checkAndPreloadGrade() {
        String studentId = getSelectedStudentId();
        if (studentId == null || courseCmb.getSelectedItem() == null) return;
        String selectedCourse = courseCmb.getSelectedItem().toString();

        for (Grades g : gradesManager.getAllGrades()) {
            if (g.getStudentId().equalsIgnoreCase(studentId) && g.getCourseId().equalsIgnoreCase(selectedCourse)) {
                scoreInput.setText(String.format("%.2f", g.getScore()));
                return;
            }
        }
        scoreInput.setText(""); // Blank if not graded yet
    }

    private void selectStudentInDropdown(String studentId) {
        for (int i = 1; i < studentCmb.getItemCount(); i++) {
            if (studentCmb.getItemAt(i).startsWith(studentId)) {
                studentCmb.setSelectedIndex(i);
                break;
            }
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        gradesList = gradesManager.getAllGrades();
        for (Grades g : gradesList) {
            tableModel.addRow(new Object[]{g.getStudentId(), g.getCourseId(), g.getScore()});
        }
    }

    public int binarySearchGrades(ArrayList<Grades> list, String targetId) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getStudentId().compareToIgnoreCase(targetId);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- AUTO-UPDATE COURSES WHEN STUDENT DROPDOWN CHANGES ---
        if (e.getSource() == studentCmb) {
            updateCoursesForSelectedStudent();
            return;
        }

        // --- AUTO-FILL SCORE WHEN COURSE DROPDOWN CHANGES ---
        if (e.getSource() == courseCmb) {
            checkAndPreloadGrade();
            return;
        }

        // --- NAVIGATION BUTTONS ---
        if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == students) {
            new StudentBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        } else if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        }

        // --- BINARY SEARCH ---
        if (e.getSource() == searchBtn) {
            String query = searchTxt.getText().trim();
            if (query.isEmpty()) {
                refreshTable();
                return;
            }

            gradesList = gradesManager.getAllGrades();
            gradesManager.sortGradesByStudentId(gradesList);

            int idx = binarySearchGrades(gradesList, query);
            if (idx != -1) {
                Grades match = gradesList.get(idx);
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{match.getStudentId(), match.getCourseId(), match.getScore()});
                selectStudentInDropdown(match.getStudentId());
                JOptionPane.showMessageDialog(this, "Grade found via Binary Search!");
            } else {
                JOptionPane.showMessageDialog(this, "No grades found for Student ID: " + query);
                refreshTable();
            }
        }

        // --- ADD BUTTON ---
        if (e.getSource() == addBtn) {
            String id = getSelectedStudentId();
            if (id == null) {
                JOptionPane.showMessageDialog(this, "Please select a student from the dropdown.");
                return;
            }

            if (courseCmb.getSelectedItem() == null || courseCmb.getSelectedItem().toString().startsWith("--")) {
                JOptionPane.showMessageDialog(this, "This student has no enrolled subjects to grade.");
                return;
            }

            String course = courseCmb.getSelectedItem().toString();
            String scoreStr = scoreInput.getText().trim();

            if (scoreStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid score.");
                return;
            }

            double scoreVal;
            try {
                scoreVal = Double.parseDouble(scoreStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Score must be a valid number.");
                return;
            }

            Grades newGrade = new Grades(id, course, scoreVal);
            if (gradesManager.addGrade(newGrade)) {
                gradesManager.calculateGWA(id); // Auto-updates GWA in MySQL
                refreshTable();
                JOptionPane.showMessageDialog(this, "Grade saved & GWA automatically updated!");
            }
        }

        // --- UPDATE BUTTON ---
        if (e.getSource() == updBtn) {
            String id = getSelectedStudentId();
            if (id == null || courseCmb.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Select a student and course to update.");
                return;
            }

            String course = courseCmb.getSelectedItem().toString();
            double newScore;
            try {
                newScore = Double.parseDouble(scoreInput.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Score must be a valid number.");
                return;
            }

            if (gradesManager.updateGrade(id, course, newScore)) {
                gradesManager.calculateGWA(id);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Grade updated & GWA automatically recalculated!");
            }
        }

        // --- DELETE BUTTON ---
        if (e.getSource() == deleteBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = tableModel.getValueAt(selectedRow, 0).toString();
                String courseName = tableModel.getValueAt(selectedRow, 1).toString();

                if (gradesManager.deleteGrade(studentId, courseName)) {
                    gradesManager.calculateGWA(studentId);
                    refreshTable();
                    scoreInput.setText("");
                    JOptionPane.showMessageDialog(this, "Grade deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please click a row in the table to delete.");
            }
        }

        // --- COMPUTE AVERAGE BUTTON ---
        if (e.getSource() == computeAve) {
            String id = getSelectedStudentId();
            if (id == null) {
                JOptionPane.showMessageDialog(this, "Please select a student first.");
                return;
            }

            double gwa = gradesManager.calculateGWA(id);
            if (gwa > 0) {
                JOptionPane.showMessageDialog(this, "Student ID: " + id + "\nCalculated GWA: " + String.format("%.2f", gwa) + "\n(Updated in MySQL database!)");
            } else {
                JOptionPane.showMessageDialog(this, "No grades found for this student.");
            }
        }
    }
}