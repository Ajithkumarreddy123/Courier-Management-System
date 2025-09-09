/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author ajith
 */
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ParcelBooking extends JFrame implements ActionListener {
    private TextField senderNameField, senderAddressField, recipientNameField, recipientAddressField;
    private TextField weightField, lengthField, widthField, heightField;
    private JButton calculateButton, backButton, receiptButton; // Added receiptButton
    private JLabel resultLabel;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/courier";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public ParcelBooking() {
        setTitle("Parcel Booking Module");
        setSize(400, 400);
        setLayout(new GridLayout(12, 1));

        initializeComponents();
        addComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        // Initialize components
        senderNameField = new TextField();
        senderAddressField = new TextField();
        recipientNameField = new TextField();
        recipientAddressField = new TextField();
        weightField = new TextField();
        lengthField = new TextField();
        widthField = new TextField();
        heightField = new TextField();
        calculateButton = new JButton("Calculate Shipping Charges");
        calculateButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        receiptButton = new JButton("Receipt"); // Added receiptButton
        receiptButton.addActionListener(this); // Added receiptButton action listener
        resultLabel = new JLabel("");
    }

    private void addComponents() {
        // Add components to the frame
        add(new JLabel("Sender Name:"));
        add(senderNameField);
        add(new JLabel("Sender Address:"));
        add(senderAddressField);
        add(new JLabel("Recipient Name:"));
        add(recipientNameField);
        add(new JLabel("Recipient Address:"));
        add(recipientAddressField);
        add(new JLabel("Weight (in kg):"));
        add(weightField);
        add(new JLabel("Length:"));
        add(lengthField);
        add(new JLabel("Width:"));
        add(widthField);
        add(new JLabel("Height:"));
        add(heightField);
        add(calculateButton);
        add(backButton);
        add(receiptButton); // Added receiptButton
        add(resultLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double weight = Double.parseDouble(weightField.getText());
            double length = Double.parseDouble(lengthField.getText());
            double width = Double.parseDouble(widthField.getText());
            double height = Double.parseDouble(heightField.getText());

            double volume = length * width * height;
            double shippingCharge = calculateShippingCharges(weight, volume);

            resultLabel.setText("Shipping Charges: $" + shippingCharge);

            // Insert data into the database
            insertData(senderNameField.getText(), senderAddressField.getText(),
                    recipientNameField.getText(), recipientAddressField.getText(),
                    weight, length, width, height, shippingCharge);
        } else if (e.getSource() == backButton) {
            dispose();
            new Dashboard();
            // Navigate back to the previous screen or perform other actions
        } else if (e.getSource() == receiptButton) { // Added receiptButton action
            // Display parcel booking details in a dialog or another window
            displayReceipt();
        }
    }

    private double calculateShippingCharges(double weight, double volume) {
        // Dummy calculation
        return weight * volume * 0.1;
    }

    private void insertData(String senderName, String senderAddress, String recipientName, String recipientAddress,
                            double weight, double length, double width, double height, double shippingCharge) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Insert sender details
            int senderId = insertSender(connection, senderName, senderAddress);

            // Insert recipient details
            int recipientId = insertRecipient(connection, recipientName, recipientAddress);

            // Insert parcel details
            int parcelId = insertParcel(connection, weight, length, width, height);

            // Insert booking details
            insertBooking(connection, senderId, recipientId, parcelId, shippingCharge);

            System.out.println("Data inserted into the database successfully.");
        } catch (SQLException ex) {
            System.err.println("Error inserting data: " + ex.getMessage());
        }
    }

    private int insertSender(Connection connection, String senderName, String senderAddress) throws SQLException {
        String query = "INSERT INTO SenderDetails (sender_name, sender_address) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, senderName);
            statement.setString(2, senderAddress);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }

    private int insertRecipient(Connection connection, String recipientName, String recipientAddress) throws SQLException {
        String query = "INSERT INTO RecipientDetails (recipient_name, recipient_address) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, recipientName);
            statement.setString(2, recipientAddress);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }

    private int insertParcel(Connection connection, double weight, double length, double width, double height) throws SQLException {
        String query = "INSERT INTO ParcelDetails (weight, length, width, height) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, weight);
            statement.setDouble(2, length);
            statement.setDouble(3, width);
            statement.setDouble(4, height);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }

    private void insertBooking(Connection connection, int senderId, int recipientId, int parcelId, double shippingCharge) throws SQLException {
        String query = "INSERT INTO BookingDetails (sender_id, recipient_id, parcel_id, shipping_charge) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, senderId);
            statement.setInt(2, recipientId);
            statement.setInt(3, parcelId);
            statement.setDouble(4, shippingCharge);
            statement.executeUpdate();
        }
    }

    private void displayReceipt() {
        StringBuilder receiptDetails = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT b.booking_id, s.sender_name, s.sender_address, r.recipient_name, r.recipient_address, "
                         + "p.weight, p.length, p.width, p.height, b.shipping_charge, b.booking_date "
                         + "FROM BookingDetails b "
                         + "INNER JOIN SenderDetails s ON b.sender_id = s.sender_id "
                         + "INNER JOIN RecipientDetails r ON b.recipient_id = r.recipient_id "
                         + "INNER JOIN ParcelDetails p ON b.parcel_id = p.parcel_id "
                         + "ORDER BY b.booking_date DESC LIMIT 1"; // Fetch only the most recent booking

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    receiptDetails.append("Booking ID: ").append(resultSet.getInt("booking_id")).append("\n");
                    receiptDetails.append("Sender Name: ").append(resultSet.getString("sender_name")).append("\n");
                    receiptDetails.append("Sender Address: ").append(resultSet.getString("sender_address")).append("\n");
                    receiptDetails.append("Recipient Name: ").append(resultSet.getString("recipient_name")).append("\n");
                    receiptDetails.append("Recipient Address: ").append(resultSet.getString("recipient_address")).append("\n");
                    receiptDetails.append("Weight: ").append(resultSet.getDouble("weight")).append(" kg\n");
                    receiptDetails.append("Dimensions: ").append(resultSet.getDouble("length")).append("x")
                            .append(resultSet.getDouble("width")).append("x").append(resultSet.getDouble("height")).append(" cm\n");
                    receiptDetails.append("Shipping Charge: $").append(resultSet.getDouble("shipping_charge")).append("\n");
                    receiptDetails.append("Booking Date: ").append(resultSet.getTimestamp("booking_date")).append("\n\n");
                } else {
                    receiptDetails.append("No active bookings found.");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching receipt details: " + ex.getMessage());
        }

        JTextArea receiptTextArea = new JTextArea(receiptDetails.toString());
        receiptTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(receiptTextArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Receipt Details", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        new ParcelBooking();
    }
}
