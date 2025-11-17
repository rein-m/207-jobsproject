package view;
import javax.swing.*;
import java.awt.*;

public class UserAccountInfoView extends JFrame {

    public UserAccountInfoView() {
        setTitle("Contact Card");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        // Overview
        JLabel nameLabel = new JLabel("Account Info");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createVerticalStrut(25)); // space under name

        // Contact Information
        JPanel contactLabel = new JPanel(new BorderLayout());
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel iconLabel = new JLabel("Basic Information:");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftSide.add(iconLabel);
        JButton btn = new JButton(">");
        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(30, 5));
        contactLabel.add(leftSide, BorderLayout.WEST);
        contactLabel.add(btn, BorderLayout.EAST);
        leftPanel.add(contactLabel);

//        JPanel contactLabel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
//        JLabel text = new JLabel("Contact Information:");
//        text.setFont(new Font("SansSerif", Font.BOLD, 20));
//        contactLabel.add(text);
//        leftPanel.add(contactLabel);

        // Row helper
        leftPanel.add(createInfoRow("✉", "User Email"));
        leftPanel.add(createInfoRow("☎", "User Phone Number"));
        leftPanel.add(createInfoRow("📍", "User Address"));

        // Resume Title
        JLabel resume = new JLabel("Resumes:");
        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        resume.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftPanel.add(resume);
        leftPanel.add(Box.createVerticalStrut(25)); // space under name

        // Resume File
        JPanel curr_resume = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        // curr_resume.setLayout(new BoxLayout(curr_resume, BoxLayout.Y_AXIS));
        curr_resume.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel resume_name = new JLabel("File Name");
        resume_name.setFont(new Font("SansSerif", Font.ITALIC, 16));

        JLabel date_added = new JLabel("Date Added");
        date_added.setFont(new Font("SansSerif", Font.ITALIC, 16));

        curr_resume.add(resume_name); curr_resume.add(date_added);
        leftPanel.add(curr_resume);
        leftPanel.add(Box.createVerticalStrut(25));

        // Qualifications Title
        JLabel qualifications = new JLabel("Qualifications:");
        qualifications.setAlignmentX(Component.CENTER_ALIGNMENT);
        qualifications.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftPanel.add(qualifications);
        leftPanel.add(Box.createVerticalStrut(25)); // space under name

        leftPanel.add(createInfoRow("📚", "Example Qualification 1"));
        leftPanel.add(createInfoRow("💻", "Example Qualification 2"));
        leftPanel.add(createInfoRow("⚽", "Example Qualification 3"));


        setLocationRelativeTo(null);
    }

    private JPanel createInfoRow(String icon, String text) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        // Info
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftSide.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftSide.add(iconLabel);
        leftSide.add(textLabel);

        // Button
//        JButton btn = new JButton(">");
//        btn.setFocusable(false);
//        btn.setPreferredSize(new Dimension(30, 5)); // optional styling


        row.add(leftSide, BorderLayout.WEST);
//        row.add(btn, BorderLayout.EAST);

        return row;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserAccountInfoView().setVisible(true);
        });
    }
}
