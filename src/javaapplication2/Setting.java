/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author ajith
 */
/*


/**
 *
 * @author ajith
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class Setting extends Frame {
    private TextField nameField;
    private TextArea addressTextArea;
    private TextField passwordField;
    private CheckboxGroup notificationGroup;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/courier";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public Setting() {
        setTitle("Settings");
        setSize(400, 450);
        setLayout(new GridLayout(6, 1)); // Reduced the grid layout to 5 rows

        // Name section
        Panel namePanel = new Panel(new BorderLayout());
        Label nameLabel = new Label("Username:"); // Changed label to Username
        nameField = new TextField(); // Removed default value
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        add(namePanel);

        // Address section
        Panel addressPanel = new Panel(new BorderLayout());
        Label addressLabel = new Label("Address:");
        addressTextArea = new TextArea(5, 30);
        // Removed default value
        Button changeAddressButton = new Button("Change Address");
        changeAddressButton.addActionListener(new ChangeAddressListener());
        addressPanel.add(addressLabel, BorderLayout.NORTH);
        addressPanel.add(addressTextArea, BorderLayout.CENTER);
        addressPanel.add(changeAddressButton, BorderLayout.SOUTH);
        add(addressPanel);

        // Password section
        Panel passwordPanel = new Panel(new BorderLayout());
        Label passwordLabel = new Label("Password:");
        passwordField = new TextField(); // Removed default value
        passwordField.setEchoChar('*'); // Mask the password
        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.addActionListener(new ChangePasswordListener());
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(changePasswordButton, BorderLayout.SOUTH);
        add(passwordPanel);

        // Notification preferences section
        Panel notificationPanel = new Panel(new BorderLayout());
        Label notificationLabel = new Label("Notification Preferences:");
        notificationGroup = new CheckboxGroup();
        Checkbox emailCheckbox = new Checkbox("Email", notificationGroup, true);
        Checkbox smsCheckbox = new Checkbox("SMS", notificationGroup, false);
        notificationPanel.add(notificationLabel, BorderLayout.NORTH);
        notificationPanel.add(emailCheckbox, BorderLayout.WEST);
        notificationPanel.add(smsCheckbox, BorderLayout.CENTER);
        add(notificationPanel);

        // Delete account section
        Panel deleteAccountPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.addActionListener(new DeleteAccountListener());
        deleteAccountPanel.add(deleteAccountButton);
        add(deleteAccountPanel);

        // Back button panel
        Panel backButtonPanel = new Panel();
        backButtonPanel.setLayout(new BorderLayout());
        Button backButton = new Button("\u2190 Back");
        backButton.addActionListener(new BackButtonListener());
        backButtonPanel.add(backButton, BorderLayout.WEST);
        add(backButtonPanel);

        // Handle window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    // Listener for changing the user's address
    class ChangeAddressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newAddress = addressTextArea.getText();
            String username = nameField.getText(); // Get the username from the nameField

            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                String sql = "UPDATE users SET address = ? WHERE username = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set parameters for the query
                    statement.setString(1, newAddress);
                    statement.setString(2, username); // Set the username parameter

                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Address changed to: " + newAddress);
                    } else {
                        System.out.println("Failed to update address.");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Database error: Failed to update address.");
            }
        }
    }

    // Listener for changing the user's password
    class ChangePasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newPassword = passwordField.getText();
            // Connect to the database and update password
        }
    }

    // Listener for deleting the user's account
    // Listener for deleting the user's account
class DeleteAccountListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String username = nameField.getText(); // Get the username from the nameField

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "DELETE FROM users WHERE username = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameter for the query
                statement.setString(1, username); // Set the username parameter

                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Account deleted for user: " + username);
                } else {
                    System.out.println("No account found for user: " + username);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Database error: Failed to delete account.");
        }
    }
}


    // Listener for back button
    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Handle back button action (e.g., navigate back to the previous screen)
            dispose();
            new AdminDashboard();
        }
    }

    public static void main(String[] args) {
        new Setting();
    }
}
