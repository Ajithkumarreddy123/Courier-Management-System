/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author ajith
 */
import java.awt.*;
import java.awt.event.*;

public class Home extends Frame implements ActionListener {
    private Label welcomeLabel;
    private Button logoutButton, adminOptionsButton, userOptionsButton;

    public Home() {
        setTitle("Home Page");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1)); // Set GridLayout for top-to-bottom layout

        welcomeLabel = new Label("Welcome to Courier Service");
        add(welcomeLabel);

        logoutButton = new Button("Logout");
       
        adminOptionsButton = new Button("Admin Options");
        userOptionsButton = new Button("User Options");

        logoutButton.addActionListener(this);
     
        adminOptionsButton.addActionListener(this);
        userOptionsButton.addActionListener(this);

        add(logoutButton);
       
        add(adminOptionsButton);
        add(userOptionsButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            dispose(); // Close the current window
            new Login(); // Open the login page again
        } else if (e.getSource() == adminOptionsButton) {
            // Open admin options page
            new AdminDashboard();
        } else if (e.getSource() == userOptionsButton) {
            // Open user options page
            // Example: new UserOptions();
            new Dashboard();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}