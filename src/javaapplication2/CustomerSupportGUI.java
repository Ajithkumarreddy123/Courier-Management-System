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
import java.util.ArrayList;
import java.util.List;

public class CustomerSupportGUI extends Frame {
    private List<FAQ> faqs;
    private List<Ticket> tickets;

    private TextArea ticketTextArea;

    public CustomerSupportGUI() {
        faqs = new ArrayList<>();
        tickets = new ArrayList<>();

        // Create and add sample FAQs
        addFAQ(new FAQ("How do I reset my password?", "You can reset your password by visiting our website and clicking on the 'Forgot Password' link."));
        addFAQ(new FAQ("How long does delivery take?", "Delivery times may vary depending on your location and the shipping method chosen. Please refer to our shipping policy for more details."));
        addFAQ(new FAQ("How can I track my order?", "You can track your order by logging into your account and navigating to the 'Order History' section."));

        setTitle("Customer Support");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Ticket submission panel
        Panel ticketPanel = new Panel();
        ticketPanel.setLayout(new BorderLayout());
        Label ticketLabel = new Label("Describe your issue:");
        ticketTextArea = new TextArea(3,5);
        Button submitButton = new Button("Submit Ticket");
        submitButton.addActionListener(new SubmitTicketListener());
        ticketPanel.add(ticketLabel, BorderLayout.NORTH);
        ticketPanel.add(ticketTextArea, BorderLayout.CENTER);
        ticketPanel.add(submitButton, BorderLayout.SOUTH);

        // FAQ panel
        Panel faqPanel = new Panel();
        faqPanel.setLayout(new GridLayout(faqs.size(), 1));
        for (FAQ faq : faqs) {
            Label faqLabel = new Label(faq.getQuestion(), Label.CENTER); // Center the FAQ questions
            faqPanel.add(faqLabel);
        }

        // Add components to the frame
        add(faqPanel, BorderLayout.WEST); // Move FAQ panel to the left
        add(ticketPanel, BorderLayout.CENTER); // Center ticket panel

        // Back button panel
        Panel backButtonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button backButton = new Button("\u2190 Back");
        backButton.addActionListener(new BackButtonListener());
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.SOUTH); // Move back button to the bottom right

        // Handle window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    // Method to add a new FAQ
    public void addFAQ(FAQ faq) {
        faqs.add(faq);
    }

    // Method to raise a new ticket
    public void raiseTicket(String issueDescription) {
        Ticket ticket = new Ticket(new User("Anonymous"), issueDescription);
        tickets.add(ticket);
        ticketTextArea.setText("");
        System.out.println("Ticket submitted: " + issueDescription);
    }

    // Listener for submitting a ticket
    class SubmitTicketListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String issueDescription = ticketTextArea.getText();
            if (!issueDescription.isEmpty()) {
                raiseTicket(issueDescription);
            }
        }
    }

    // Listener for back button
    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Handle back button action
            // For example, navigate back to the previous screen
            // You can implement your own logic here
            dispose();
            new Dashboard();
        }
    }

    public static void main(String[] args) {
        new CustomerSupportGUI();
    }
}

class FAQ {
    private String question;
    private String answer;

    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

class Ticket {
    private User user;
    private String issueDescription;

    public Ticket(User user, String issueDescription) {
        this.user = user;
        this.issueDescription = issueDescription;
    }

    public User getUser() {
        return user;
    }

    public String getIssueDescription() {
        return issueDescription;
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
