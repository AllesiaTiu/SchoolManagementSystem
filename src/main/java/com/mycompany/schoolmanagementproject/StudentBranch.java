package com.mycompany.schoolmanagementproject;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class StudentBranch extends JFrame implements ActionListener {

    JButton students;
    JButton courses;
    JButton grades;
    JButton transcript;
    JButton report;
    JButton attendance;
    JButton addBtn;
    JButton updBtn;
    JButton deleteBtn;
    JButton searchBtn;
    JButton sortBtn;
    
    JPanel panelInputs;
    JPanel panelTitle;
    JPanel courseBox;
    
    JLabel title;
    JLabel studentId;
    JLabel studentName;
    JLabel studentCourse;
    
    JTextField nameInput;
    JTextField idInput;
    JTextField searchInput;
    
    //Table
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    JScrollPane scrollCourse;
    
    //ArrayLists
    ArrayList<Student> studentList;
    ArrayList<JCheckBox> courseCheckBox;
    
    //count
    int courseCount;
    
    StudentBranch(){
        setTitle("School Management System");
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
        
        studentId = new JLabel("Student ID: ");
        studentId.setBounds(30, 30, 100, 30);
        
        idInput = new JTextField();
        idInput.setBounds(140, 35, 250, 20);
        
        studentName = new JLabel("Name:");
        studentName.setBounds(30, 60, 100, 30);
        
        nameInput = new JTextField();
        nameInput.setBounds(140, 65, 250, 20);
        
        studentCourse = new JLabel("Courses: ");
        studentCourse.setBounds(30, 90, 100, 30);
       
        title = new JLabel("Student Management");
        title.setBounds(30, 0, 150, 30);
        
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.PINK);
        panelTitle.setSize(600, 30);
        panelTitle.setLayout(null);
        panelTitle.add(title);
        
        courseCheckBox = new ArrayList<>();
        
        courseBox = new JPanel();
        courseBox.setBackground(Color.WHITE);
        courseBox.setLayout(null);

        // Dynamically load checkboxes from available courses in database
        ArrayList<String> availableCourses = StudentManager.getAvailableCourses();
        int yPos = 10;
        for (String cName : availableCourses) {
            JCheckBox cb = new JCheckBox(cName);
            cb.setBounds(10, yPos, 200, 20);
            cb.setBackground(Color.WHITE);
            courseCheckBox.add(cb);
            courseBox.add(cb);
            yPos += 25;
        }
        courseBox.setPreferredSize(new java.awt.Dimension(230, Math.max(200, yPos + 10)));
        
        scrollCourse = new JScrollPane(courseBox);
        scrollCourse.setBounds(140, 95, 250, 100);
        
        addBtn = new JButton("Add");
        addBtn.setBounds(50, 205, 100, 40);
        
        updBtn = new JButton("Update");
        updBtn.setBounds(160, 205, 100, 40);
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(270, 205, 100, 40);
        
        panelInputs = new JPanel();
        panelInputs.setBackground(Color.LIGHT_GRAY);
        panelInputs.setBounds(180, 30, 600, 300);
        panelInputs.setLayout(null);
        panelInputs.add(panelTitle);
        panelInputs.add(studentId);
        panelInputs.add(idInput);
        panelInputs.add(studentName);
        panelInputs.add(nameInput);
        panelInputs.add(studentCourse);
        panelInputs.add(addBtn);
        panelInputs.add(updBtn);
        panelInputs.add(deleteBtn);
        panelInputs.add(scrollCourse);
        
        add(panelInputs);
        
        searchInput = new JTextField();
        searchInput.setBounds(180, 340, 250, 20);
        add(searchInput);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(440, 340, 100, 20);
        add(searchBtn);
        
        sortBtn = new JButton("Sort A-Z");
        sortBtn.setBounds(550, 340, 100, 20);
        add(sortBtn);
        
        studentList = new ArrayList<>();
     
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Enrolled Courses");
        tableModel.addColumn("GWA");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(180, 370, 700, 200);
        add(scrollPane);

        // Click row in table to populate textboxes (makes update/delete easy)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    idInput.setText(tableModel.getValueAt(row, 0).toString());
                    nameInput.setText(tableModel.getValueAt(row, 1).toString());
                }
            }
        });
        
        students.addActionListener(this);
        courses.addActionListener(this);
        grades.addActionListener(this);
        transcript.addActionListener(this);
        report.addActionListener(this);
        attendance.addActionListener(this);
        addBtn.addActionListener(this);
        updBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        sortBtn.addActionListener(this);

        // Load existing students from DB on startup
        studentList = StudentManager.getAllStudents();
        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameText = nameInput.getText().trim();
        String idText = idInput.getText().trim();
        
        // --- ADD BUTTON ---
        if(e.getSource() == addBtn){
            if (nameText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter student name.");
                return;
            }

            Student student = new Student(nameText);
            
            // Collect checked courses
            for (JCheckBox cb : courseCheckBox) {
                if (cb.isSelected()) {
                    student.enrollCourse(cb.getText());
                }
            }

            // Save to MySQL via StudentManager
            if (StudentManager.addStudent(student)) {
                studentList.add(student);
                refreshTable();
                nameInput.setText("");
                idInput.setText("");
                for (JCheckBox cb : courseCheckBox) cb.setSelected(false);
                JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Database Error: Could not save student.");
            }
        }

        // --- UPDATE BUTTON ---
        if (e.getSource() == updBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a student from the table first.");
                return;
            }
            if (nameText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name to update.");
                return;
            }

            String currentId = tableModel.getValueAt(selectedRow, 0).toString();
            if (StudentManager.updateStudent(currentId, nameText)) {
                for (Student s : studentList) {
                    if (s.getID().equals(currentId)) {
                        s.setName(nameText);
                        break;
                    }
                }
                refreshTable();
                nameInput.setText("");
                idInput.setText("");
                JOptionPane.showMessageDialog(this, "Student Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        }

        // --- DELETE BUTTON ---
        if (e.getSource() == deleteBtn) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a student from the table first.");
                return;
            }

            String currentId = tableModel.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Delete student " + currentId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (StudentManager.deleteStudent(currentId)) {
                    studentList.removeIf(s -> s.getID().equals(currentId));
                    refreshTable();
                    nameInput.setText("");
                    idInput.setText("");
                    JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Delete failed.");
                }
            }
        }

        // --- SORT BUTTON ---
        if(e.getSource() == sortBtn){
            bubbleSort(studentList);
            refreshTable();
        }
        
        // --- SEARCH BUTTON ---
        if(e.getSource() == searchBtn){
            String query = searchInput.getText().trim().toLowerCase();
            if (query.isEmpty()) {
                refreshTable();
                return;
            }

            tableModel.setRowCount(0);
            for (Student s : studentList) {
                if (s.getName().toLowerCase().contains(query) || s.getID().toLowerCase().contains(query)) {
                    tableModel.addRow(new Object[]{
                        s.getID(),
                        s.getName(),
                        s.getEnrolledCourses().size(),
                        "0.0"
                    });
                }
            }
        }

        // --- NAVIGATION BUTTONS ---
        if(e.getSource() == courses){
            new CourseBranch().setVisible(true);
            this.dispose();
        }
        if(e.getSource() == grades){
            new GradesBranch().setVisible(true);
            this.dispose();
        }
        if(e.getSource() == students){
            // Already on students
        }
        if(e.getSource() == attendance){
            new AttendanceBranch().setVisible(true);
            this.dispose();
        }
        if(e.getSource() == report){
            new ReportBranch().setVisible(true);
            this.dispose();
        }
    }
    
    public int countCourses(){
        int count = 0;
        for(int i = 0; i < courseCheckBox.size(); i++){
            if(courseCheckBox.get(i).isSelected()){
                count++;
            }
        }
        return count;
    }

    public void bubbleSort(ArrayList<Student> studentList){
        for(int i = 0; i < studentList.size() - 1; i++){
            for(int j = 0; j < studentList.size() - i - 1; j++){
                if(studentList.get(j).getName().compareToIgnoreCase(studentList.get(j + 1).getName()) > 0){
                    Student temp = studentList.get(j);
                    studentList.set(j, studentList.get(j + 1));
                    studentList.set(j + 1, temp);
                }
            }
        }
    }

    public void refreshTable(){
        tableModel.setRowCount(0);
        for(Student list : studentList){
            tableModel.addRow(new Object[] {
                list.getID(), 
                list.getName(), 
                list.getEnrolledCourses() != null ? list.getEnrolledCourses().size() : 0, 
                "0.0"
            });
        }
    }
}