






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
import java.sql.*;
import javax.swing.*;

public class Login extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton, cancelButton, newUserButton; // Added newUserButton
    Connection connection;

    public Login() {
        setTitle("Login");
        setSize(300, 180); // Increased height to accommodate the new button
        setLayout(new FlowLayout());

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField(20);

        Label passwordLabel = new Label("Password:");
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        newUserButton = new Button("New User"); // Created newUserButton
        newUserButton.addActionListener(this); // Added ActionListener to newUserButton

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(cancelButton);
        add(newUserButton); // Added newUserButton

        setVisible(true);
        establishConnection(); // Establish database connection
    }

    private void establishConnection() {
        try {
            // Connect to your database
            String dbURL = "jdbc:mysql://localhost:3306/courier";
            String username = "root";
            String password = "12345";
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check credentials in the database
            if (validateLogin(username, password)) {
                // Open home page
                new Home().setVisible(true);
                dispose(); // Close login frame
            } else {
                // Show error message
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close login frame when Cancel button is clicked
        } else if (e.getSource() == newUserButton) {
            // Open a new window or dialog for user registration
            new NewUser().setVisible(true);
        }
    }

    private boolean validateLogin(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a row is found, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Login();
        });
    }
}
