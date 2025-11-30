package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EditUserAccountQualificationsView extends JFrame {
    public EditUserAccountQualificationsView() {
        super("Qualifications");

        // ---------- Main container ----------
        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(24, 24, 24, 24));
        content.setLayout(new BorderLayout(0, 20));
        setContentPane(content);

        // ---------- Top title + subtitle ----------
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

        // ---------- Form area ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Education
        formPanel.add(createLabeledTextArea("Education", false,
                "e.g. BSc Computer Science, University of Toronto"), gbc);

        // Work Experience
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Work Experience", false,
                "e.g. Software Developer Intern at XYZ Corp"), gbc);

        // Projects
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Projects", false,
                "e.g. Personal portfolio website, job search app"), gbc);

        // Skills
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Skills", false,
                "e.g. Java, problem solving, communication"), gbc);

        // Programming Languages
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Programming Languages", false,
                "e.g. Java, Python, JavaScript"), gbc);

        // Frameworks & Libraries
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Frameworks & Libraries", false,
                "e.g. Spring, React, Swing"), gbc);

        // Tools & Technologies
        gbc.gridy++;
        formPanel.add(createLabeledTextArea("Tools & Technologies", false,
                "e.g. Git, IntelliJ, Docker"), gbc);

        content.add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // ---------- Frame settings ----------
        // Do NOT exit the whole app when this closes:
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
    }

    // --- helper methods ---

    /**
     * Creates a label + multi-line text area stack:
     *  Label
     *  [ text area ]
     */
    private JPanel createLabeledTextArea(String labelText, boolean required, String placeholder) {
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

        JTextArea area = new JTextArea(placeholder, 3, 20);
        styleTextArea(area);

        JScrollPane scrollPane = new JScrollPane(
                area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Applies rounded-ish look to text areas.
     */
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
