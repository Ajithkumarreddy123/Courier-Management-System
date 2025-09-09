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
import javax.swing.*;
import java.sql.*;

public class ViewShipments extends Frame implements ActionListener {
    private TextArea statusTextArea;
    private Button backButton;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/courier";
    private static final String USER = "root";
    private static final String PASSWORD = "12345"; // Change this to your database password

    public ViewShipments() {
        setLayout(new BorderLayout());

        statusTextArea = new TextArea(10, 30);
        statusTextArea.setEditable(false); // Make text area read-only
        add(statusTextArea, BorderLayout.CENTER);

        backButton = new Button("Back");
        backButton.addActionListener(this);
        add(backButton, BorderLayout.SOUTH);

        setTitle("View Shipment Status");
        setSize(400, 300);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Fetch shipment status data from the database
        fetchShipmentStatusFromDB();
    }

    private void fetchShipmentStatusFromDB() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT shipment, parcel_number, status FROM shipments";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                StringBuilder statusData = new StringBuilder();

                while (resultSet.next()) {
                    String shipmentID = resultSet.getString("shipment");
                    String parcelNumber = resultSet.getString("parcel_number");
                    String status = resultSet.getString("status");

                    statusData.append("Shipment ID: ").append(shipmentID)
                              .append("\nParcel Number: ").append(parcelNumber)
                              .append("\nStatus: ").append(status).append("\n\n");
                }

                statusTextArea.setText(statusData.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch shipment status data from the database.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if (action.equals("Back")) {
            // Handle back button action
            dispose();
            new AdminDashboard();
        }
    }

    public static void main(String[] args) {
        new ViewShipments();
    }
}
