package com.mycompany.schoolmanagementproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


 
public class ReportBranch extends JFrame{
    private JTextField txtId, txtName, txtStatus;
    private JTable table;
    private DefaultTableModel tableModel;
            
    
    ReportBranch(){
        setTitle("Student Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Student ID: "));
        txtId = new JTextField();
        formPanel.add(txtName);
        
        
        
        
        
        
         
    }
}
