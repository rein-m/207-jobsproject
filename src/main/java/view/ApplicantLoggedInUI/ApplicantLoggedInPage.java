package view.ApplicantLoggedInUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantLoggedInPage extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Welcome Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null); // Center the window

        frame.setLayout(new BorderLayout(10, 10));

        createHeaderPanel(frame);

        createContentPanel(frame);

        frame.setVisible(true);
    }

    private static void createHeaderPanel(JFrame frame) {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel welcomeTextPanel = new JPanel(new GridLayout(2, 1));

        JLabel welcomeLabel = new JLabel("Welcome NAME", SwingConstants.LEFT);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        JLabel questionLabel = new JLabel("What do you want to do today?", SwingConstants.LEFT);
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));

        welcomeTextPanel.add(welcomeLabel);
        welcomeTextPanel.add(questionLabel);
        headerPanel.add(welcomeTextPanel, BorderLayout.CENTER);

        //  Right Side: Profile Button
        JButton profileButton = new JButton("Profile");
        profileButton.setPreferredSize(new Dimension(100, 80)); // Suggested size for the buttons
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAccountInfo ProfileWindow = new UserAccountInfo();
                ProfileWindow.setVisible(true);
            }
        });

        // Add some padding/margin around the button
        JPanel profileButtonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButtonWrapper.add(profileButton);
        profileButtonWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10)); // Top-Right margin

        headerPanel.add(profileButtonWrapper, BorderLayout.EAST);

        // Add the header to the top of the main frame
        frame.add(headerPanel, BorderLayout.NORTH);
    }

    private static void createContentPanel(JFrame frame) {
        // Center panel to hold the buttons
        JPanel contentPanel = new JPanel(new GridLayout(4, 1, 20, 30)); // 3 rows, 1 col, with vertical spacing

        // Add some padding around the content panel to prevent buttons from touching the edges
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // Button A
        JButton buttonA = createStyledButton("Search and Applying for Jobs.");
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button clicked!");
            }
        });

        contentPanel.add(buttonA);

        // Button B
        JButton buttonB = createStyledButton("Look at your application.");
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicantLoggedInView Application = new ApplicantLoggedInView();
                Application.setVisible(true);
            }
        });

        contentPanel.add(buttonB);

        // Button C
        JButton buttonC = createStyledButton("Chat with out AI !");
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button clicked!");
            }
        });

        contentPanel.add(buttonC);

        JButton buttonD = new JButton("Save Progress");
        buttonD.setFont(new Font("SansSerif", Font.BOLD, 18)); // Large font for visibility
        buttonD.setPreferredSize(new Dimension(10, 10)); // Suggested size for the buttons
        buttonD.setBackground(new Color(255, 255, 255)); // Light grey background
        buttonD.setOpaque(true);
        buttonD.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Black border like the sketch
        buttonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                JOptionPane.showMessageDialog(null, "Progress Saved!");
            }

        });
        contentPanel.add(buttonD);
        frame.add(contentPanel, BorderLayout.CENTER);
    }

    // Helper method to create a button with common styling
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18)); // Large font for visibility
        button.setPreferredSize(new Dimension(250, 80)); // Suggested size for the buttons
        button.setBackground(new Color(255, 255, 255)); // Light grey background
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Black border like the sketch

        return button;

    }
}
