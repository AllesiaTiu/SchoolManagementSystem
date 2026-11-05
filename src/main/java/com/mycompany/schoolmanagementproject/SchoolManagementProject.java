package com.mycompany.schoolmanagementproject;

import com.mycompany.schoolmanagementproject.gui.LoginGUI;

public class SchoolManagementProject {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}