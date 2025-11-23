package view;

import javax.swing.*;
import java.awt.*;
public class ApplicantLoggedInView {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Applicant GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10,10));

        // Top title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField companyName = new JTextField("Applicant Name", 20);
        companyName.setFont(new Font("SansSerif", Font.PLAIN, 20));
        titlePanel.add(companyName);

// Left list column (scrollable)
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(6, 1, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        String[] letters = {"Company A", "Company B", "Company C", "Company D", "Company E", "Company F"};
        for (String s : letters) {
            JTextField tf = new JTextField(s);
            tf.setHorizontalAlignment(JTextField.CENTER);
            tf.setFont(new Font("SansSerif", Font.PLAIN, 26));
            tf.setPreferredSize(new Dimension(200, 50));
            listPanel.add(tf);
        }

// Put listPanel inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(listPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// FIXED SIZE for the scrolling list
        scrollPane.setPreferredSize(new Dimension(150, 300));


        // Right buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 20, 20));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton yesBtn = new JButton("View");
        yesBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton noBtn = new JButton("Exit");
        noBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));

        rightPanel.add(yesBtn);
        rightPanel.add(noBtn);

        // Main center panel (list + buttons)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(listPanel, BorderLayout.WEST);
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

