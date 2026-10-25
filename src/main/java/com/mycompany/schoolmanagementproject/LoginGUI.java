package com.mycompany.schoolmanagementproject;

import javax.swing.*;

public class LoginGUI extends JFrame{
    JLabel idlbl;
    JLabel passwordlbl;
    
    JTextField nametxt;
    JTextField passtxt;
    
    JButton logInBtn;
    
    LoginGUI(){
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        idlbl = new JLabel("Student ID: ");
        idlbl.setBounds(100,50, 100, 80);
        add(idlbl);
        
        
        
    }
    
}
