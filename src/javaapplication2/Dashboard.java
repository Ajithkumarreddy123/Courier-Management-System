package javaapplication2;


import java.awt.*;
import java.awt.event.*;

public class Dashboard extends Frame implements ActionListener {

    public Dashboard() {
        setTitle("Dashboard");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Title bar
        Panel titlePanel = new Panel(new BorderLayout());
        Label titleLabel = new Label("Dashboard", Label.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Buttons panel
        Panel buttonsPanel = new Panel(new GridLayout(7, 1));
        Button parcelBookingButton = new Button("Parcel Booking ");
        Button parcelTrackingButton = new Button("Parcel Tracking");
        Button customerSupportButton = new Button("Customer Support ");
        Button FeedbacksButton = new Button("Feedbacks");
        Button settingsButton = new Button("Settings");
        Button backToHomeButton = new Button("Back to Home");

        parcelBookingButton.addActionListener(this);
        parcelTrackingButton.addActionListener(this);
        customerSupportButton.addActionListener(this);
        FeedbacksButton.addActionListener(this);
        settingsButton.addActionListener(this);
        backToHomeButton.addActionListener(this);

        buttonsPanel.add(parcelBookingButton);
        buttonsPanel.add(parcelTrackingButton);
        buttonsPanel.add(customerSupportButton);
        buttonsPanel.add(FeedbacksButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(backToHomeButton);

        // Add components to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String module = e.getActionCommand();
        System.out.println("Opening " + module);
        // Add actions for each button here
        if (module.equals("Parcel Booking ")) {
            // Open parcel booking module
            new ParcelBooking();
        } else if (module.equals("Parcel Tracking")) {
            // Open parcel tracking module
            new ParcelTracking();
        } else if (module.equals("Customer Support ")) {
            // Open customer support module
            new CustomerSupportGUI();
        } else if (module.equals("Feedbacks")) {
            // Open feedbacks module
            new FeedbackForm().setVisible(true);
        } else if (module.equals("Settings")) {
            // Open settings module
            new Setting();
        } else if (module.equals("Back to Home")) {
            // Add your code here to navigate back to the home screen
            // For example:
            // dispose(); // Close the current window
            new Home(); // Open the home screen
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
