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

public class ManageShipments extends Frame implements ActionListener {
    private Label shipmentLabel, parcelLabel, statusLabel, weightLabel; // Added statusLabel and weightLabel
    private TextField shipmentTextField, parcelTextField, statusTextField, weightTextField; // Added statusTextField and weightTextField
    private Button addButton, updateButton, deleteButton, backButton;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/courier";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public ManageShipments() {
        setLayout(new BorderLayout());

        Panel topPanel = new Panel(new GridLayout(4, 2)); // Adjusted layout to accommodate status and weight
        shipmentLabel = new Label("Shipment:");
        topPanel.add(shipmentLabel);
        shipmentTextField = new TextField(20);
        topPanel.add(shipmentTextField);

        parcelLabel = new Label("Parcel Number:");
        topPanel.add(parcelLabel);
        parcelTextField = new TextField(20);
        topPanel.add(parcelTextField);

        statusLabel = new Label("Status:"); // Added statusLabel
        topPanel.add(statusLabel);
        statusTextField = new TextField(20); // Added statusTextField
        topPanel.add(statusTextField);

        weightLabel = new Label("Weight:"); // Added weightLabel
        topPanel.add(weightLabel);
        weightTextField = new TextField(20); // Added weightTextField
        topPanel.add(weightTextField);

        add(topPanel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridLayout(4, 1));

        addButton = new Button("Add Shipment");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        updateButton = new Button("Update Shipment");
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        deleteButton = new Button("Delete Shipment");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        backButton = new Button("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Courier Service Management");
        setSize(300, 300); // Increased height to accommodate the additional text fields
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        String shipment = shipmentTextField.getText();
        int parcelNumber = Integer.parseInt(parcelTextField.getText());
        String status = statusTextField.getText(); // Retrieve status text
        double weight = Double.parseDouble(weightTextField.getText()); // Retrieve weight value

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (action.equals("Add Shipment")) {
                // Code to add shipment to database
                String sql = "INSERT INTO shipments (shipment, parcel_number, status, weight) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, shipment);
                    statement.setInt(2, parcelNumber);
                    statement.setString(3, status);
                    statement.setDouble(4, weight);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Added shipment: " + shipment + " with parcel number: " + parcelNumber);
                    } else {
                        System.out.println("Failed to add shipment.");
                    }
                }
            } else if (action.equals("Update Shipment")) {
                // Code to update shipment in database
                String sql = "UPDATE shipments SET shipment = ?, status = ?, weight = ? WHERE parcel_number = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, shipment);
                    statement.setString(2, status);
                    statement.setDouble(3, weight);
                    statement.setInt(4, parcelNumber);
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Updated shipment: " + shipment + " for parcel number: " + parcelNumber);
                    } else {
                        System.out.println("Failed to update shipment.");
                    }
                }
            } else if (action.equals("Delete Shipment")) {
                // Code to delete shipment from database
                String sql = "DELETE FROM shipments WHERE parcel_number = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, parcelNumber);
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Deleted shipment with parcel number: " + parcelNumber);
                    } else {
                        System.out.println("Failed to delete shipment.");
                    }}
                }else if (action.equals("Back")) {
                // Handle back button action (e.g., return to the previous screen)
                System.out.println("Back button clicked");
                dispose(); // Close the current window
                new AdminDashboard(); // Open the admin dashboard window
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Database error.");
        }
    }

    public static void main(String[] args) {
        new ManageShipments();
    }
}
