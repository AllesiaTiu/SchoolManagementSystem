package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.manager.StudentManager;
import com.mycompany.schoolmanagementproject.model.Student;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentBranch extends JFrame implements ActionListener {

    JButton students, courses, grades, transcript, report, attendance;
    JButton addBtn, updBtn, deleteBtn, searchBtn, sortBtn;
    
    JPanel panelInputs, panelTitle, courseBox;
    JLabel title, studentId, studentName, studentCourse;
    JTextField nameInput, idInput, searchInput;
    
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane, scrollCourse;
    
    ArrayList<Student> studentList;
    ArrayList<JCheckBox> courseCheckBox;

    public StudentBranch() {
        setTitle("School Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        idInput.setEditable(false);
        
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String selectedId = tableModel.getValueAt(row, 0).toString();
                    idInput.setText(selectedId);
                    nameInput.setText(tableModel.getValueAt(row, 1).toString());

                    for (Student s : studentList) {
                        if (s.getID().equals(selectedId)) {
                            for (JCheckBox cb : courseCheckBox) {
                                cb.setSelected(s.getEnrolledCourses().contains(cb.getText()));
                            }
                            break;
                        }
                    }
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

        studentList = StudentManager.getAllStudents();
        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameText = nameInput.getText().trim();
        String idText = idInput.getText().trim();
        
        if (e.getSource() == addBtn) {
            if (nameText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter student name.");
                return;
            }

            Student student = new Student(nameText);
            for (JCheckBox cb : courseCheckBox) {
                if (cb.isSelected()) {
                    student.enrollCourse(cb.getText());
                }
            }

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
                for (JCheckBox cb : courseCheckBox) cb.setSelected(false);
                JOptionPane.showMessageDialog(this, "Student Updated Successfully!");
            }
        }

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
                    for (JCheckBox cb : courseCheckBox) cb.setSelected(false);
                    JOptionPane.showMessageDialog(this, "Student Deleted Successfully!");
                }
            }
        }

        if (e.getSource() == sortBtn) {
            bubbleSort(studentList);
            refreshTable();
        }
        
     
        if (e.getSource() == searchBtn) {
            String query = searchInput.getText().trim();
            if (query.isEmpty()) {
                refreshTable();
                return;
            }

            bubbleSort(studentList);

    
            int foundIndex = binarySearchByName(studentList, query);
            if (foundIndex == -1) {
                foundIndex = binarySearchById(studentList, query);
            }


            if (foundIndex != -1) {
                Student s = studentList.get(foundIndex);
                tableModel.setRowCount(0);
                String gwaDisplay = (s.getGwa() > 0.0) ? String.format("%.2f", s.getGwa()) : "N/A";
                tableModel.addRow(new Object[]{s.getID(), s.getName(), s.getEnrolledCourses().size(), gwaDisplay});
                JOptionPane.showMessageDialog(this, "Student found using Binary Search at sorted position [" + foundIndex + "]!");
            } else {
                JOptionPane.showMessageDialog(this, "No student found matching query (Binary Search).");
                refreshTable();
            }
        }

        if (e.getSource() == courses) {
            new CourseBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == grades) {
            new GradesBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == attendance) {
            new AttendanceBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == report) {
            new ReportBranch().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == transcript) {
            new TranscriptBranch().setVisible(true);
            this.dispose();
        }
    }

  
    public int binarySearchByName(ArrayList<Student> list, String target) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public int binarySearchById(ArrayList<Student> list, String target) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getID().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public void bubbleSort(ArrayList<Student> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getName().compareToIgnoreCase(list.get(j + 1).getName()) > 0) {
                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Student list : studentList) {
            String gwaDisplay = (list.getGwa() > 0.0) ? String.format("%.2f", list.getGwa()) : "N/A";
            tableModel.addRow(new Object[] {
                list.getID(), 
                list.getName(), 
                list.getEnrolledCourses() != null ? list.getEnrolledCourses().size() : 0, 
                gwaDisplay
            });
        }
    }
}