package view.ApplicantLoggedInUI;

import interface_adapter.account.AccountController;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Edit dialog for the user's qualifications.
 * View layer: calls AccountController and reads from AccountViewModel.
 */
public class EditUserAccountQualificationsView extends JFrame {

    private final AccountController accountController;
    private final AccountViewModel accountViewModel;

    private JTextArea educationArea;
    private JTextArea workArea;
    private JTextArea projectsArea;
    private JTextArea skillsArea;
    private JTextArea languagesArea;
    private JTextArea frameworksArea;
    private JTextArea toolsArea;

    // --- placeholder examples (not actually saved) ---
    private static final String EDU_PLACEHOLDER       = "e.g. BSc Computer Science, University of Toronto";
    private static final String WORK_PLACEHOLDER      = "e.g. Software Developer Intern at XYZ Corp";
    private static final String PROJECTS_PLACEHOLDER  = "e.g. Personal portfolio website, job search app";
    private static final String SKILLS_PLACEHOLDER    = "e.g. Java, problem solving, communication";
    private static final String LANG_PLACEHOLDER      = "e.g. Java, Python, JavaScript";
    private static final String FRAME_PLACEHOLDER     = "e.g. Spring, React, Swing";
    private static final String TOOLS_PLACEHOLDER     = "e.g. Git, IntelliJ, Docker";

    public EditUserAccountQualificationsView(AccountController accountController,
                                             AccountViewModel accountViewModel) {
        super("Qualifications");

        this.accountController = accountController;
        this.accountViewModel = accountViewModel;

        AccountState state = accountViewModel.getState();

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Each area: if state has real value, uses it; otherwise shows placeholder
        educationArea = new JTextArea(
                initialText(state.getEducation(), EDU_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Education", false, educationArea), gbc);

        gbc.gridy++;
        workArea = new JTextArea(
                initialText(state.getWorkExperience(), WORK_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Work Experience", false, workArea), gbc);

        gbc.gridy++;
        projectsArea = new JTextArea(
                initialText(state.getProjects(), PROJECTS_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Projects", false, projectsArea), gbc);

        gbc.gridy++;
        skillsArea = new JTextArea(
                initialText(state.getSkills(), SKILLS_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Skills", false, skillsArea), gbc);

        gbc.gridy++;
        languagesArea = new JTextArea(
                initialText(state.getProgrammingLanguages(), LANG_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Programming Languages", false, languagesArea), gbc);

        gbc.gridy++;
        frameworksArea = new JTextArea(
                initialText(state.getFrameworksAndLibraries(), FRAME_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Frameworks & Libraries", false, frameworksArea), gbc);

        gbc.gridy++;
        toolsArea = new JTextArea(
                initialText(state.getToolsAndTechnologies(), TOOLS_PLACEHOLDER), 3, 20);
        formPanel.add(createLabeledTextArea("Tools & Technologies", false, toolsArea), gbc);

        content.add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // ---------- Buttons ----------
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelBtn = new JButton("Cancel");
        JButton saveBtn = new JButton("Save");

        cancelBtn.addActionListener(e -> dispose());
        saveBtn.addActionListener(e -> {
            AccountState s = accountViewModel.getState();
            String identifier = s.getIdentifier();
            if (identifier == null || identifier.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No user identifier set in AccountState.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Strip placeholders before saving
            String education = valueOrEmpty(educationArea.getText(), EDU_PLACEHOLDER);
            String work      = valueOrEmpty(workArea.getText(), WORK_PLACEHOLDER);
            String projects  = valueOrEmpty(projectsArea.getText(), PROJECTS_PLACEHOLDER);
            String skills    = valueOrEmpty(skillsArea.getText(), SKILLS_PLACEHOLDER);
            String langs     = valueOrEmpty(languagesArea.getText(), LANG_PLACEHOLDER);
            String frames    = valueOrEmpty(frameworksArea.getText(), FRAME_PLACEHOLDER);
            String tools     = valueOrEmpty(toolsArea.getText(), TOOLS_PLACEHOLDER);

            // Uses existing basic info from the state
            accountController.editAccount(
                    identifier,
                    s.getEmail(),
                    s.getPhone(),
                    s.getAddress(),
                    education,
                    work,
                    projects,
                    skills,
                    langs,
                    frames,
                    tools
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

    // --- helper methods ---

    private JPanel createLabeledTextArea(String labelText,
                                         boolean required,
                                         JTextArea area) {
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
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(new LineBorder(new Color(0xC4C4C4)));
        panel.add(scroll);

        return panel;
    }

    private void styleTextArea(JTextArea area) {
        area.setFont(area.getFont().deriveFont(16f));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /**
     * If stateVal is null/empty, returns the placeholder. Otherwise returns the actual value.
     */
    private static String initialText(String stateVal, String placeholder) {
        if (stateVal == null || stateVal.trim().isEmpty()) {
            return placeholder;
        }
        return stateVal;
    }

    /**
     * If the user left the placeholder untouched, or cleared the field, treats it as empty.
     */
    private static String valueOrEmpty(String text, String placeholder) {
        if (text == null) return "";
        String trimmed = text.trim();
        if (trimmed.isEmpty() || trimmed.equals(placeholder)) {
            return "";
        }
        return trimmed;
    }
}
