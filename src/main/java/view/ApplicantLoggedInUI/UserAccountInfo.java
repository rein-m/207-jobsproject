package view.ApplicantLoggedInUI;

import interface_adapter.account.AccountController;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Read-only account profile view for the logged-in applicant.
 * This is a View layer class that depends on the interface_adapter layer
 */
public class UserAccountInfo extends JFrame implements PropertyChangeListener {

    private static final String EDUCATION_PREFIX = "Education: ";
    private static final String WORK_PREFIX = "Work Experience: ";
    private static final String PROJECTS_PREFIX = "Projects: ";
    private static final String SKILLS_PREFIX = "Skills: ";
    private static final String LANGUAGES_PREFIX = "Programming Languages: ";
    private static final String FRAMEWORKS_PREFIX = "Frameworks & Libraries: ";
    private static final String TOOLS_PREFIX = "Tools & Technologies: ";

    // --- interface-adapter collaborators ---

    private final AccountController accountController;
    private final AccountViewModel accountViewModel;

    // --- UI labels that get updated from AccountState ---

    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;

    private JLabel educationLabel;
    private JLabel workLabel;
    private JLabel projectsLabel;
    private JLabel skillsLabel;
    private JLabel languagesLabel;
    private JLabel frameworksLabel;
    private JLabel toolsLabel;

    /**
     * @param accountController controller to call when user wants to edit
     * @param accountViewModel  view model that holds the current account state
     */
    public UserAccountInfo(AccountController accountController,
                           AccountViewModel accountViewModel) {
        super("User Information");

        this.accountController = accountController;
        this.accountViewModel = accountViewModel;

        // Listenw for state changes
        this.accountViewModel.addPropertyChangeListener(this);

        // ---------- layout + UI ----------

        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        // Title
        JLabel nameLabel = new JLabel("Account Info");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createVerticalStrut(25));

        // --- Basic Information section ---

        JPanel contactLabel = new JPanel(new BorderLayout());
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel iconLabel = new JLabel("Basic Information:");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftSide.add(iconLabel);

        JButton editInfoBtn = new JButton("Edit Account Info");
        editInfoBtn.setFocusable(false);
        editInfoBtn.setPreferredSize(new Dimension(200, 30));
        editInfoBtn.addActionListener(e -> {
            // Opens the edit-info window; it uses the ViewModel as the source of truth
            EditUserAccountInfoView editView =
                    new EditUserAccountInfoView(accountController, accountViewModel);
            editView.setVisible(true);
        });

        contactLabel.add(leftSide, BorderLayout.WEST);
        contactLabel.add(editInfoBtn, BorderLayout.EAST);
        leftPanel.add(contactLabel);

        // Email / phone / address rows (plain labels, updated via the ViewModel)
        emailLabel = new JLabel("");
        leftPanel.add(createInfoRow("✉", emailLabel));
        leftPanel.add(Box.createVerticalStrut(5));

        phoneLabel = new JLabel("");
        leftPanel.add(createInfoRow("☎", phoneLabel));
        leftPanel.add(Box.createVerticalStrut(5));

        addressLabel = new JLabel("");
        leftPanel.add(createInfoRow("📍", addressLabel));
        leftPanel.add(Box.createVerticalStrut(20));

        // --- Resumes section (pure UI; use case is implemented in other branch) ---

        JPanel resumeHeader = new JPanel(new BorderLayout());
        JPanel resumeLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel resumeLabel = new JLabel("Resumes:");
        resumeLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        resumeLeft.add(resumeLabel);

        JButton addResumeBtn = new JButton("Add New Resume");
        addResumeBtn.setFocusable(false);
        addResumeBtn.setPreferredSize(new Dimension(200, 30));
        addResumeBtn.addActionListener(e -> {
            PDFProcessorUI frame = new PDFProcessorUI();
            frame.setVisible(true);
        });

        resumeHeader.add(resumeLeft, BorderLayout.WEST);
        resumeHeader.add(addResumeBtn, BorderLayout.EAST);
        leftPanel.add(resumeHeader);
        leftPanel.add(Box.createVerticalStrut(10));

        // Placeholder resume list
        JPanel resumeListPanel = new JPanel();
        resumeListPanel.setLayout(new BoxLayout(resumeListPanel, BoxLayout.Y_AXIS));
        resumeListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        resumeListPanel.add(new JLabel("Your uploaded resumes will appear here..."));
        JScrollPane resumeScroll = new JScrollPane(resumeListPanel);
        resumeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(resumeScroll);
        leftPanel.add(Box.createVerticalStrut(25));

        // --- Qualifications section ---

        JPanel qualifications = new JPanel(new BorderLayout());
        JPanel quaLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel quaLabel = new JLabel("Qualifications:");
        quaLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        quaLeft.add(quaLabel);

        JButton editQualBtn = new JButton("Edit Qualifications");
        editQualBtn.setFocusable(false);
        editQualBtn.setPreferredSize(new Dimension(200, 30));
        editQualBtn.addActionListener(e -> {
            EditUserAccountQualificationsView editQualView =
                    new EditUserAccountQualificationsView(accountController, accountViewModel);
            editQualView.setVisible(true);
        });

        qualifications.add(quaLeft, BorderLayout.WEST);
        qualifications.add(editQualBtn, BorderLayout.EAST);
        leftPanel.add(qualifications);
        leftPanel.add(Box.createVerticalStrut(15));

        // Qualification labels (with prefixes)
        educationLabel = new JLabel(EDUCATION_PREFIX);
        leftPanel.add(createInfoRow("🎓", educationLabel));

        workLabel = new JLabel(WORK_PREFIX);
        leftPanel.add(createInfoRow("💼", workLabel));

        projectsLabel = new JLabel(PROJECTS_PREFIX);
        leftPanel.add(createInfoRow("📁", projectsLabel));

        skillsLabel = new JLabel(SKILLS_PREFIX);
        leftPanel.add(createInfoRow("🛠️", skillsLabel));

        languagesLabel = new JLabel(LANGUAGES_PREFIX);
        leftPanel.add(createInfoRow("💻", languagesLabel));

        frameworksLabel = new JLabel(FRAMEWORKS_PREFIX);
        leftPanel.add(createInfoRow("📚", frameworksLabel));

        toolsLabel = new JLabel(TOOLS_PREFIX);
        leftPanel.add(createInfoRow("⚙️", toolsLabel));

        // Initializes labels from current state
        updateFromState(accountViewModel.getState());
    }

    // --- PropertyChangeListener for AccountViewModel ---

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (AccountViewModel.ACCOUNT_PROPERTY.equals(evt.getPropertyName())) {
            AccountState state = accountViewModel.getState();
            updateFromState(state);
        }
    }

    private void updateFromState(AccountState state) {
        if (state == null) {
            return;
        }

        if (state.getEmail() != null) {
            emailLabel.setText(state.getEmail());
        }
        if (state.getPhone() != null) {
            phoneLabel.setText(state.getPhone());
        }
        if (state.getAddress() != null) {
            addressLabel.setText(state.getAddress());
        }

        String education = state.getEducation() == null ? "" : state.getEducation();
        String work = state.getWorkExperience() == null ? "" : state.getWorkExperience();
        String projects = state.getProjects() == null ? "" : state.getProjects();
        String skills = state.getSkills() == null ? "" : state.getSkills();
        String languages = state.getProgrammingLanguages() == null ? "" : state.getProgrammingLanguages();
        String frameworks = state.getFrameworksAndLibraries() == null ? "" : state.getFrameworksAndLibraries();
        String tools = state.getToolsAndTechnologies() == null ? "" : state.getToolsAndTechnologies();

        educationLabel.setText(EDUCATION_PREFIX + education);
        workLabel.setText(WORK_PREFIX + work);
        projectsLabel.setText(PROJECTS_PREFIX + projects);
        skillsLabel.setText(SKILLS_PREFIX + skills);
        languagesLabel.setText(LANGUAGES_PREFIX + languages);
        frameworksLabel.setText(FRAMEWORKS_PREFIX + frameworks);
        toolsLabel.setText(TOOLS_PREFIX + tools);
    }

    private JPanel createInfoRow(String icon, JLabel textLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftSide.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftSide.add(iconLabel);
        leftSide.add(textLabel);

        row.add(leftSide, BorderLayout.WEST);
        return row;
    }
}
