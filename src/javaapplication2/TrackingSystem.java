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

public class TrackingSystem extends Frame implements ActionListener {
    TextField parcelNumberField;
    Button trackButton, backButton; // Added backButton
    TextArea trackingInfoArea;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/courier";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public TrackingSystem() {
        setLayout(new FlowLayout());

        Label parcelNumberLabel = new Label("Enter Parcel Number:");
        parcelNumberField = new TextField(20);
        trackButton = new Button("Track");
        backButton = new Button("Back"); // Added backButton
        trackingInfoArea = new TextArea(10, 30);

        add(parcelNumberLabel);
        add(parcelNumberField);
        add(trackButton);
        add(backButton); // Added backButton
        add(trackingInfoArea);

        trackButton.addActionListener(this);
        backButton.addActionListener(this); // Added action listener for backButton

        setTitle("Parcel Tracking");
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == trackButton) {
            int parcelNumber = Integer.parseInt(parcelNumberField.getText());
            // Retrieve tracking information from the database
            String trackingInfo = retrieveTrackingInfo(parcelNumber);
            trackingInfoArea.setText(trackingInfo);
        } else if (ae.getSource() == backButton) { // Handle backButton click
            dispose(); // Close the current window
            new AdminDashboard(); // Open the admin dashboard window
        }
    }

    // Method to retrieve tracking information from the database
    private String retrieveTrackingInfo(int parcelNumber) {
        String trackingInfo = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM shipments WHERE parcel_number = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, parcelNumber);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    trackingInfo += "Parcel Number: " + resultSet.getInt("parcel_number") + "\n";
                    trackingInfo += "Shipment: " + resultSet.getString("shipment") + "\n";
                    trackingInfo += "Status: " + resultSet.getString("status") + "\n";
                    trackingInfo += "Weight: " + resultSet.getInt("weight") + " kg\n";
                    trackingInfo += "Expected Delivery: " + resultSet.getDate("expected_delivery_date") + "\n";
                } else {
                    trackingInfo = "Tracking information not found for Parcel Number: " + parcelNumber;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            trackingInfo = "Error: Unable to retrieve tracking information.";
        }
        return trackingInfo;
    }

    public static void main(String[] args) {
        new TrackingSystem();
    }
}
