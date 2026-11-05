package com.mycompany.schoolmanagementproject.gui;

import com.mycompany.schoolmanagementproject.databaseAccess.AdminDAO;
import com.mycompany.schoolmanagementproject.gui.StudentBranch;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginGUI extends JFrame implements ActionListener {

    private JLabel titleLbl, roleLbl, userLbl, passLbl;
    private JComboBox<String> roleCombo;
    private JTextField userTxt;
    private JPasswordField passTxt;
    private JButton loginBtn, clearBtn;
    private JPanel headerPanel, mainPanel;

    private AdminDAO adminDAO;

    public LoginGUI() {

        setTitle("School Management System - Portal Login");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        adminDAO = new AdminDAO();

   
        headerPanel = new JPanel();
        headerPanel.setBackground(Color.PINK);
        headerPanel.setBounds(0, 0, 450, 60);
        headerPanel.setLayout(null);

        titleLbl = new JLabel("School Portal Login");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 18));
        titleLbl.setBounds(130, 15, 200, 30);
        headerPanel.add(titleLbl);
        add(headerPanel);

        // Main Form Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBounds(40, 80, 355, 270);
        mainPanel.setLayout(null);

        // Role Selector (Admin vs User)
        roleLbl = new JLabel("Login As:");
        roleLbl.setBounds(30, 25, 80, 25);
        mainPanel.add(roleLbl);

        roleCombo = new JComboBox<>(new String[]{"Admin", "User (Student)"});
        roleCombo.setBounds(120, 25, 190, 25);
        roleCombo.addActionListener(this);
        mainPanel.add(roleCombo);

        // Username
        userLbl = new JLabel("Username:");
        userLbl.setBounds(30, 75, 80, 25);
        mainPanel.add(userLbl);

        userTxt = new JTextField();
        userTxt.setBounds(120, 75, 190, 25);
        mainPanel.add(userTxt);

        // Password
        passLbl = new JLabel("Password:");
        passLbl.setBounds(30, 125, 80, 25);
        mainPanel.add(passLbl);

        passTxt = new JPasswordField();
        passTxt.setBounds(120, 125, 190, 25);
        mainPanel.add(passTxt);

        // Buttons
        loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 190, 100, 35);
        loginBtn.addActionListener(this);
        mainPanel.add(loginBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(180, 190, 100, 35);
        clearBtn.addActionListener(this);
        mainPanel.add(clearBtn);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == roleCombo) {
            String selectedRole = roleCombo.getSelectedItem().toString();
            if (selectedRole.equals("User (Student)")) {
                userTxt.setText("");
                passTxt.setText("");
                userTxt.setEnabled(false);
                passTxt.setEnabled(false);
            } else {
                userTxt.setEnabled(true);
                passTxt.setEnabled(true);
            }
            return;
        }

      
        if (e.getSource() == clearBtn) {
            userTxt.setText("");
            passTxt.setText("");
            userTxt.requestFocus();
            return;
        }


        if (e.getSource() == loginBtn) {
            String selectedRole = roleCombo.getSelectedItem().toString();

           
            if (selectedRole.equals("User (Student)")) {
                JOptionPane.showMessageDialog(this,
                        "User / Student Portal is currently under development (Phase 2).\nPlease select 'Admin' to continue.",
                        "Notice",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String username = userTxt.getText().trim();
            String password = new String(passTxt.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter both Username and Password.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

         
            boolean isValid = adminDAO.validateAdmin(username, password);

            if (isValid) {
                JOptionPane.showMessageDialog(this, "Login Successful! Welcome, Admin.");
                
      
                StudentBranch studentBranch = new StudentBranch();
                studentBranch.setVisible(true);
                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
}