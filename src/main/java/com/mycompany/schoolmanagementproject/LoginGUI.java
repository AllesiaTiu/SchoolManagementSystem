package com.mycompany.schoolmanagementproject;

import javax.swing.*;

public class LoginGUI extends JFrame{
    JLabel idlbl;
    JLabel passwordlbl;
    
    JTextField idtxt;
    JTextField passtxt;
    
    JButton logInBtn;
    
    LoginGUI(){
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        idlbl = new JLabel("Student ID: ");
        idlbl.setBounds(100,100, 100, 30);
        add(idlbl);
        
        idtxt = new JTextField();
        idtxt.setBounds(325, 100, 250, 30);
        add(idtxt);
        
        
        
        
        
    }
    
}
