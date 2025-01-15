package src;

import javax.swing.*;
import java.awt.*;

public class HospitalQueueSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalQueueSystem().createGUI();
        });
    }

    public void createGUI() {
        // Create the main JFrame
        JFrame frame = new JFrame("Metropolitan Hospital Queue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLayout(new CardLayout());

        // Set custom icon for the JFrame
        ImageIcon icon = new ImageIcon("assets/app_icon.png");
        frame.setIconImage(icon.getImage());

        // Create all screens (panels)
        JPanel landingPageScreen = createLandingPageScreen(frame);
        JPanel queueMonitorScreen = createQueueMonitorScreen();
        JPanel loginScreen = createLoginScreen();

        // Add screens to the frame
        frame.add(landingPageScreen, "LandingPage");
        frame.add(queueMonitorScreen, "QueueMonitor");
        frame.add(loginScreen, "Login");

        // Show the initial screen
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "LandingPage");

        frame.setVisible(true);
    }

    private JPanel createLandingPageScreen(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(34, 139, 34)); // Green background

        // Hero section with hospital logo
        JLabel logoLabel = new JLabel(new ImageIcon("assets/hospital_logo.png"), SwingConstants.CENTER);
        panel.add(logoLabel, BorderLayout.CENTER);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Metropolitan Hospital", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // "Start Now" button
        JButton startButton = new JButton("Start Now");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(new Color(34, 139, 34));
        startButton.setFocusPainted(false);

        startButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Login");
        });

        panel.add(startButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createQueueMonitorScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(34, 139, 34)); // Green background

        JLabel title = new JLabel("METROPOLITAN HOSPITAL WAITING ROOM", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        gridPanel.setBackground(new Color(34, 139, 34));

        JButton registrationButton = createServiceButton("Registration");
        JButton paymentButton = createServiceButton("Payment");
        JButton consultationButton = createServiceButton("Consultation");
        JButton pharmacyButton = createServiceButton("Pharmacy");

        gridPanel.add(registrationButton);
        gridPanel.add(paymentButton);
        gridPanel.add(consultationButton);
        gridPanel.add(pharmacyButton);

        panel.add(gridPanel, BorderLayout.CENTER);

        JLabel queueMonitorLabel = new JLabel("QUEUE MONITOR", SwingConstants.CENTER);
        queueMonitorLabel.setForeground(Color.WHITE);
        queueMonitorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(queueMonitorLabel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createLoginScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("WELCOME TO METROPOLITAN HOSPITAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JTextField usernameField = new JTextField("admin");
        JPasswordField passwordField = new JPasswordField("password");
        JButton loginButton = new JButton("LOGIN");

        formPanel.add(usernameField);
        formPanel.add(passwordField);
        formPanel.add(loginButton);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createServiceButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
