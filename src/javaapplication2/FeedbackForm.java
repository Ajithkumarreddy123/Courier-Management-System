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

public class FeedbackForm extends Frame implements ActionListener {
    private Label nameLabel, emailLabel, feedbackLabel;
    private TextField nameTextField, emailTextField;
    private TextArea feedbackTextArea;
    private Button submitButton, clearButton, backButton;

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/courier";
    private final String user = "root";
    private final String password = "12345";

    public FeedbackForm() {
        setTitle("Courier Service Feedback Form");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Initialize UI components
        Panel inputPanel = new Panel(new GridLayout(3, 2));

        nameLabel = new Label("Name:");
        inputPanel.add(nameLabel);
        nameTextField = new TextField(20);
        inputPanel.add(nameTextField);

        emailLabel = new Label("Email:");
        inputPanel.add(emailLabel);
        emailTextField = new TextField(20);
        inputPanel.add(emailTextField);

        feedbackLabel = new Label("Feedback:");
        inputPanel.add(feedbackLabel);
        feedbackTextArea = new TextArea(5, 20);
        inputPanel.add(feedbackTextArea);

        add(inputPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());

        submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        clearButton = new Button("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        backButton = new Button("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        establishConnection(); // Establish database connection

        // Window closing listener...
    }

    private void establishConnection() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent event) {
    if (event.getSource() == submitButton) {
        // Insert feedback into the database
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String feedback = feedbackTextArea.getText();

        try {
            // Create a PreparedStatement to insert feedback data
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO feedback (name, email, feedback) VALUES (?, ?, ?)");

            // Set values for parameters in the PreparedStatement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, feedback);

            // Execute the PreparedStatement to insert the data
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Feedback inserted successfully.");
            } else {
                System.out.println("Failed to insert feedback.");
            }
            
            // Close the PreparedStatement
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else if (event.getSource() == clearButton) {
        // Clear all fields
        nameTextField.setText("");
        emailTextField.setText("");
        feedbackTextArea.setText("");
    } else if (event.getSource() == backButton) {
        // Handle back button action (e.g., return to the previous screen)
        dispose(); // Close the feedback form
        // Add code to navigate back to the previous screen
        new Dashboard();
    }
}


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FeedbackForm().setVisible(true);
        });
    }
}
