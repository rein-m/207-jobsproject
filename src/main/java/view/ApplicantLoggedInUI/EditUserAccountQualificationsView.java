package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EditUserAccountQualificationsView extends JFrame {

    private final UserAccountInfo parent;

    private JTextArea educationArea;
    private JTextArea workArea;
    private JTextArea projectsArea;
    private JTextArea skillsArea;
    private JTextArea languagesArea;
    private JTextArea frameworksArea;
    private JTextArea toolsArea;

    public EditUserAccountQualificationsView(UserAccountInfo parent,
                                             String education,
                                             String work,
                                             String projects,
                                             String skills,
                                             String languages,
                                             String frameworks,
                                             String tools) {
        super("Qualifications");
        this.parent = parent;

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(24, 24, 24, 24));
        content.setLayout(new BorderLayout(0, 20));
        setContentPane(content);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Qualifications");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 26f));
        headerPanel.add(titleLabel);

        JPanel subtitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        subtitlePanel.setOpaque(false);

        JLabel subtitleText = new JLabel("Update your qualifications below.");
        JLabel subtitleReq = new JLabel("*required");
        subtitleReq.setForeground(new Color(0xB00020));

        subtitlePanel.add(subtitleText);
        subtitlePanel.add(subtitleReq);

        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitlePanel);

        content.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        educationArea = new JTextArea(
                education.isEmpty() ? "e.g. BSc Computer Science, University of Toronto" : education,
                3, 20);
        formPanel.add(createLabeledTextArea("Education", false, educationArea), gbc);

        gbc.gridy++;
        workArea = new JTextArea(
                work.isEmpty() ? "e.g. Software Developer Intern at XYZ Corp" : work,
                3, 20);
        formPanel.add(createLabeledTextArea("Work Experience", false, workArea), gbc);

        gbc.gridy++;
        projectsArea = new JTextArea(
                projects.isEmpty() ? "e.g. Personal portfolio website, job search app" : projects,
                3, 20);
        formPanel.add(createLabeledTextArea("Projects", false, projectsArea), gbc);

        gbc.gridy++;
        skillsArea = new JTextArea(
                skills.isEmpty() ? "e.g. Java, problem solving, communication" : skills,
                3, 20);
        formPanel.add(createLabeledTextArea("Skills", false, skillsArea), gbc);

        gbc.gridy++;
        languagesArea = new JTextArea(
                languages.isEmpty() ? "e.g. Java, Python, JavaScript" : languages,
                3, 20);
        formPanel.add(createLabeledTextArea("Programming Languages", false, languagesArea), gbc);

        gbc.gridy++;
        frameworksArea = new JTextArea(
                frameworks.isEmpty() ? "e.g. Spring, React, Swing" : frameworks,
                3, 20);
        formPanel.add(createLabeledTextArea("Frameworks & Libraries", false, frameworksArea), gbc);

        gbc.gridy++;
        toolsArea = new JTextArea(
                tools.isEmpty() ? "e.g. Git, IntelliJ, Docker" : tools,
                3, 20);
        formPanel.add(createLabeledTextArea("Tools & Technologies", false, toolsArea), gbc);

        content.add(new JScrollPane(formPanel), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelBtn = new JButton("Cancel");
        JButton saveBtn = new JButton("Save");

        cancelBtn.addActionListener(e -> dispose());
        saveBtn.addActionListener(e -> {
            parent.updateQualifications(
                    educationArea.getText().trim(),
                    workArea.getText().trim(),
                    projectsArea.getText().trim(),
                    skillsArea.getText().trim(),
                    languagesArea.getText().trim(),
                    frameworksArea.getText().trim(),
                    toolsArea.getText().trim()
            );
            dispose();
        });

        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(saveBtn);
        content.add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createLabeledTextArea(String labelText, boolean required, JTextArea area) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JPanel labelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelRow.setOpaque(false);

        JLabel label = new JLabel(labelText + " ");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));
        labelRow.add(label);

        if (required) {
            JLabel star = new JLabel("*");
            star.setForeground(new Color(0xB00020));
            star.setFont(star.getFont().deriveFont(Font.BOLD, 16f));
            labelRow.add(star);
        }

        panel.add(labelRow);
        panel.add(Box.createVerticalStrut(4));

        styleTextArea(area);

        JScrollPane scrollPane = new JScrollPane(
                area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        panel.add(scrollPane);

        return panel;
    }

    private void styleTextArea(JTextArea area) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(area.getFont().deriveFont(16f));
        area.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }
}
