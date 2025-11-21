package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantLoggedInView extends JFrame {

    public ApplicantLoggedInView() {

        JFrame frame = new JFrame("Applicant GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10,10));

        // --- Top title ---
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Use JLabel for display, not JTextField, unless you intend for it to be editable
        JLabel applicantNameLabel = new JLabel("Applicant Name");
        applicantNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(applicantNameLabel);

        // --- Left list column (scrollable) ---
        // The listPanel uses GridLayout to stack the items vertically
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 0 rows means infinite vertical stacking
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] letters = {"Company A", "Company B", "Company C", "Company D", "Company E", "Company F", "Company G", "Company H", "Company I", "Company J"};
        for (String s : letters) {
            JTextField tf = new JTextField(s);
            tf.setHorizontalAlignment(JTextField.CENTER);
            tf.setFont(new Font("SansSerif", Font.PLAIN, 20));
            tf.setEditable(false); // Make it non-editable like a list item
            listPanel.add(tf);
        }

        // Put listPanel inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(listPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, // Only show scrollbar when needed
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set the size constraint on the scroll pane itself
        scrollPane.setPreferredSize(new Dimension(250, 400)); // Adjusted size

        // --- Right buttons ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 20, 20));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20)); // Adjusted padding

        JButton yesBtn = new JButton("View");
        yesBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton noBtn = new JButton("Exit");
        noBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        noBtn.addActionListener(e -> frame.dispose()); // Added a functional exit action

        rightPanel.add(yesBtn);
        rightPanel.add(noBtn);

        // --- Main center panel (list + buttons) ---
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0)); // Added horizontal spacing

        // ** FIX APPLIED HERE: ADD SCROLLPANE, NOT listPanel **
        centerPanel.add(scrollPane, BorderLayout.WEST);
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        // --- Add components to the frame ---
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // The main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicantLoggedInView::new); // Creates and displays the GUI
    }
}