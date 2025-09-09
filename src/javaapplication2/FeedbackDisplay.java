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

public class FeedbackDisplay extends Frame {
    private TextArea feedbackDisplayArea;
    private Button backButton;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/courier";
    private final String user = "root";
    private final String password = "12345";

    public FeedbackDisplay() {
        setTitle("Feedback Display");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Initialize the text area for displaying feedback
        feedbackDisplayArea = new TextArea(10, 30);
        feedbackDisplayArea.setEditable(false); // Make it read-only

        add(feedbackDisplayArea, BorderLayout.CENTER);

        // Add the back button to the bottom
        backButton = new Button("Back");
        add(backButton, BorderLayout.SOUTH);

        // Add action listener to handle back button click
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the feedback display window
                // Add code to navigate back to the previous screen (e.g., Dashboard)
                new AdminDashboard().setVisible(true); // Assuming Dashboard is the previous screen
            }
        });

        establishConnection(); // Establish database connection

        // Load and display feedback
        loadFeedback();

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

    private void loadFeedback() {
        try {
            // Query to retrieve feedback from the database
            String query = "SELECT * FROM feedback";

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set and display feedback
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String feedback = resultSet.getString("feedback");

                // Append feedback to the text area
                feedbackDisplayArea.append("Name: " + name + "\n");
                feedbackDisplayArea.append("Email: " + email + "\n");
                feedbackDisplayArea.append("Feedback: " + feedback + "\n\n");
            }

            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FeedbackDisplay().setVisible(true);
        });
    }
}
