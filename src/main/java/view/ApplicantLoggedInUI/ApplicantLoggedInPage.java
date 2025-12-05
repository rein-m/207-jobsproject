package view.ApplicantLoggedInUI;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.account.EditAccountInteractor;

import javax.swing.*;
import java.awt.*;

/**
 * Main "home" screen for a logged-in applicant.
 * This is in the View layer and only talks to the AccountController and AccountViewModel that are passed in.
 */
public class ApplicantLoggedInPage extends JFrame {

    private final AccountController accountController;
    private final AccountViewModel accountViewModel;
    private final String identifier;

    // ---------- constructor ----------

    public ApplicantLoggedInPage(AccountController accountController,
                                 AccountViewModel accountViewModel,
                                 String identifier) {
        super("Welcome Application");

        this.accountController = accountController;
        this.accountViewModel = accountViewModel;
        this.identifier = identifier;

        initializeUI();
    }

    // ---------- Entry point / composition root ----------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // ----- Create DAO and seed a sample user -----
            InMemoryUserDataAccessObject userDao = new InMemoryUserDataAccessObject();

            // Here it is hard-coded because the database was not set up.
            User user = new User(
                    "Spongebob Applicant",          // identifier / name
                    "password123",              // password (demo only)
                    "Bikini Bottom",              // location
                    "spongebob.squarepants@gmail.com",        // email
                    "647-123-4567"              // phone
            );
            // Optionally seed some qualifications
//            user.updateQualifications(
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""
//            );
            userDao.addUser(user);

            // ----- Build the account use case stack -----

            AccountViewModel accountViewModel = new AccountViewModel();
            AccountPresenter accountPresenter = new AccountPresenter(accountViewModel);
            EditAccountInteractor interactor =
                    new EditAccountInteractor(userDao, accountPresenter);
            AccountController accountController =
                    new AccountController(interactor);

            // ----- Seed the AccountViewModel from the entity -----

            AccountState state = accountViewModel.getState();
            state.setIdentifier(user.getIdentifier());
            state.setEmail(user.getEmail());
            state.setPhone(user.getPhone());
            state.setAddress(user.getLocation());
            state.setEducation(user.getEducation());
            state.setWorkExperience(user.getWorkExperience());
            state.setProjects(user.getProjects());
            state.setSkills(user.getSkills());
            state.setProgrammingLanguages(user.getProgrammingLanguages());
            state.setFrameworksAndLibraries(user.getFrameworksAndLibraries());
            state.setToolsAndTechnologies(user.getToolsAndTechnologies());
            accountViewModel.setState(state);
            accountViewModel.firePropertyChanged();

            // ----- Start the UI -----

            ApplicantLoggedInPage frame =
                    new ApplicantLoggedInPage(accountController, accountViewModel, user.getIdentifier());
            frame.setVisible(true);
        });
    }

    // ---------- UI construction ----------

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        createHeaderPanel();
        createContentPanel();
    }

    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel welcomeTextPanel = new JPanel(new GridLayout(2, 1));

        JLabel welcomeLabel = new JLabel("Welcome " + identifier, SwingConstants.LEFT);
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

        // When clicked, open the Account Info window which in turn
        // can open the two edit dialogs and trigger the use case.
        profileButton.addActionListener(e -> {
            UserAccountInfo profileWindow =
                    new UserAccountInfo(accountController, accountViewModel);
            profileWindow.setVisible(true);
        });

        // Add some padding/margin around the button
        JPanel profileButtonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButtonWrapper.add(profileButton);
        profileButtonWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10)); // Top-Right margin

        headerPanel.add(profileButtonWrapper, BorderLayout.EAST);

        // Add the header to the top of the main frame
        add(headerPanel, BorderLayout.NORTH);
    }

    private void createContentPanel() {
        // Center panel to hold the buttons
        JPanel contentPanel = new JPanel(new GridLayout(4, 1, 20, 30)); // 4 rows, 1 col, with vertical spacing

        // Add some padding around the content panel to prevent buttons from touching the edges
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // Button A
        JButton buttonA = createStyledButton("Search and Apply for Jobs.");
        buttonA.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Search jobs feature coming soon!"));

        contentPanel.add(buttonA);

        // Button B
        JButton buttonB = createStyledButton("Look at your applications.");
        buttonB.addActionListener(e -> {
            ApplicantLoggedInView application = new ApplicantLoggedInView();
            application.setVisible(true);
        });

        contentPanel.add(buttonB);

        // Button C
        JButton buttonC = createStyledButton("Chat with our AI!");
        buttonC.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "AI chat coming soon!"));

        contentPanel.add(buttonC);

        // Button D - Save Progress
        JButton buttonD = new JButton("Save Progress");
        buttonD.setFont(new Font("SansSerif", Font.BOLD, 18)); // Large font for visibility
        buttonD.setPreferredSize(new Dimension(10, 10)); // Suggested size for the buttons
        buttonD.setBackground(new Color(255, 255, 255)); // Light grey background
        buttonD.setOpaque(true);
        buttonD.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Black border like the sketch
        buttonD.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Progress Saved!"));

        contentPanel.add(buttonD);

        add(contentPanel, BorderLayout.CENTER);
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
