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

public class NewUser extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button registerButton, cancelButton;
    Connection connection;

    public NewUser() {
        setTitle("New User Registration");
        setSize(300, 150);
        setLayout(new FlowLayout());

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField(20);

        Label passwordLabel = new Label("Password:");
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');

        registerButton = new Button("Register");
        registerButton.addActionListener(this);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(cancelButton);

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
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check if username already exists
            if (usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert new user into the database
                if (registerNewUser(username, password)) {
                    JOptionPane.showMessageDialog(this, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close registration frame
                } else {
                    JOptionPane.showMessageDialog(this, "Error registering user", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close registration frame when Cancel button is clicked
        }
    }

    private boolean usernameExists(String username) {
        try {
            String query = "SELECT * FROM users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a row is found (username exists), false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // Assume error occurred, so return true to prevent registration
        }
    }

    private boolean registerNewUser(String username, String password) {
        try {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Returns true if at least one row was inserted, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new NewUser();
    }
}

