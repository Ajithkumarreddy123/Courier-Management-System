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

public class AdminDashboard extends Frame implements ActionListener {
    

    public AdminDashboard() {
        setTitle("Dashboard");
        setSize(400, 350); // Increased height to accommodate the additional button
        setLayout(new BorderLayout());

        // Title bar
        Panel titlePanel = new Panel(new BorderLayout());
        Label titleLabel = new Label("Dashboard", Label.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // User and parcel info
        

        // Buttons panel
        Panel buttonsPanel = new Panel(new GridLayout(8, 1)); // Adjusted rows to accommodate the "Review" button
        Button viewShipmentsButton = new Button("View Shipments");
        Button trackingSystemButton = new Button("Tracking");
        Button manageShipmentsButton = new Button("Manage Shipments");
        Button settingsButton = new Button("Settings");
        Button reviewButton = new Button("Review"); // Added "Review" button
        Button backToHomeButton = new Button("Back to Home");

        viewShipmentsButton.addActionListener(this);
        trackingSystemButton.addActionListener(this);
        manageShipmentsButton.addActionListener(this);
        settingsButton.addActionListener(this);
        reviewButton.addActionListener(this); // Added action listener for "Review" button
        backToHomeButton.addActionListener(this);

        buttonsPanel.add(viewShipmentsButton);
        buttonsPanel.add(trackingSystemButton);
        buttonsPanel.add(manageShipmentsButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(reviewButton); // Added "Review" button to the panel
        buttonsPanel.add(backToHomeButton);

        // Add components to the frame
        add(titlePanel, BorderLayout.NORTH);
       
        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String module = e.getActionCommand();
        System.out.println("Opening " + module);
        // Add actions for each button here
        if (module.equals("View Shipments")) {
            // Open parcel booking module
            new ViewShipments();
        } else if (module.equals("Tracking")) {
            // Open parcel tracking module
            new TrackingSystem();
        } else if (module.equals("Manage Shipments")) {
            // Open delivery management module
            new ManageShipments();
        } else if (module.equals("Settings")) {
            // Open settings module
            new Setting1();
        } else if (module.equals("Review")) {
            // Open review module
            new FeedbackDisplay().setVisible(true);
        } else if (module.equals("Back to Home")) {
            // Add your code here to navigate back to the home screen
            // For example:
            // dispose(); // Close the current window
            new Home(); // Open the home screen
        }
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
