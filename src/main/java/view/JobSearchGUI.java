package view;

import view.ApplicantLoggedInUI.UserAccountInfo;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JobSearchGUI extends JFrame {

    public JobSearchGUI() {
        super("JobGPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Setting a larger default size the UI
        setLocationRelativeTo(null); // Center the window

        // Main content pane with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15)); // Add spacing between regions
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Outer padding
        add(mainPanel); // Add mainPanel to the JFrame

        // 1. Top Bar (Image, Search, Profile)
        mainPanel.add(createTopBar(), BorderLayout.NORTH);

        // 2. Main Content Area (Filters + Recommended Jobs)
        JPanel contentArea = new JPanel(new BorderLayout(20, 0)); // Spacing between filters and jobs
        contentArea.add(createFiltersPanel(), BorderLayout.WEST);
        contentArea.add(createRecommendedJobsPanel(), BorderLayout.CENTER);
        mainPanel.add(contentArea, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel topBarPanel = new JPanel(new BorderLayout(10, 0)); // Main panel for top bar

        Creating_Logo(topBarPanel);

        // --- Center Section: Search Bar and Search Button ---
        JPanel searchAreaPanel = new JPanel(new BorderLayout(5, 0)); // 5px spacing between bar and button

        // Search Bar (Occupies the CENTER of the searchAreaPanel)
        JTextField searchBar = new JTextField("Enter keywords or job title...");
        searchBar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        searchBar.setHorizontalAlignment(JTextField.CENTER); // Align text left

        // Search Button (Occupies the EAST of the searchAreaPanel)
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        searchButton.setPreferredSize(new Dimension(120, 50)); // Give the button a fixed size

        // *** ADDING THE ACTION LISTENER HERE ***
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchBar.getText();
                JOptionPane.showMessageDialog(JobSearchGUI.this,
                        "Searching for: \"" + searchTerm + "\"",
                        "Search Initiated",
                        JOptionPane.INFORMATION_MESSAGE);
                // TODO: Here you would call your backend method to filter job listings
            }
        });

        searchAreaPanel.add(searchBar, BorderLayout.CENTER);
        searchAreaPanel.add(searchButton, BorderLayout.EAST);

        // Add the combined search area to the CENTER of the main top bar
        topBarPanel.add(searchAreaPanel, BorderLayout.CENTER);
        // --------------------------------------------------------

        // Right: Profile Button
        JButton profileButton = new JButton("Profile");
        profileButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        profileButton.setPreferredSize(new Dimension(100, 50));
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAccountInfo ProfileWindow = new UserAccountInfo();
                ProfileWindow.setVisible(true);
            }
        });
        topBarPanel.add(profileButton, BorderLayout.EAST);

        return topBarPanel;
    }

    private static void Creating_Logo(JPanel topBarPanel) {
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        ImageIcon originalIcon = new ImageIcon("JobGPT image.png");
        Image originalImage = originalIcon.getImage();

        // Define the target dimensions (100 wide x 50 high)
        int targetWidth = 80;
        int targetHeight = 80;

        // 2. Scale the image
        // SCALE_SMOOTH is a high-quality scaling algorithm that looks better
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // 3. Create a new ImageIcon from the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // 4. Set the new, scaled icon to the label
        imageLabel.setIcon(scaledIcon);
        imageLabel.setPreferredSize(new Dimension(targetWidth, targetHeight));
        // imageLabel.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Font is irrelevant for just an image icon
        topBarPanel.add(imageLabel, BorderLayout.WEST);
    }

    private JPanel createFiltersPanel() {
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS)); // Stack vertically
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Right padding for separation
        filtersPanel.setPreferredSize(new Dimension(200, 0)); // Fixed width for filters

        JLabel filtersTitle = new JLabel("Filters:");
        filtersTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        filtersTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Align text left
        filtersPanel.add(filtersTitle);
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer

        // --- Working Schedule ---
        filtersPanel.add(createFilterSection("Working schedule", new String[]{"Full Time", "Part Time", "Internship"}));
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // --- Types of Jobs ---
        filtersPanel.add(createFilterSection("Types of Jobs", new String[]{"Computer Science", "Life Science", "Math", "Other"}));
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // --- Month Salary ---
        filtersPanel.add(createFilterSection("Month salary", new String[]{"4000 or more", "2000 to 4000", "Less than 2000"}));
        filtersPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        //TODO: add other qualifaction if you want, delete this if you think it's enough.
        filtersPanel.add(createFilterSection("Other qualifacations", new String[]{"other 1", "other 2", "other 3"}));

        filtersPanel.add(Box.createVerticalGlue()); // Pushes all components to the top

        return filtersPanel;
    }

    private JPanel createFilterSection(String title, String[] options) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sectionTitle = new JLabel(title + ":");
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sectionPanel.add(sectionTitle);
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer

        // Use a ButtonGroup for radio-button like behavior if only one can be selected
        // For checkboxes as shown in sketch, no ButtonGroup needed unless you want exclusive selection
        // ButtonGroup group = new ButtonGroup(); // Uncomment if you want radio button behavior
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            // group.add(checkBox); // Uncomment if using ButtonGroup
            sectionPanel.add(checkBox);
        }
        return sectionPanel;
    }

    private JPanel createRecommendedJobsPanel() {
        JPanel jobsPanel = new JPanel(new BorderLayout());

        JLabel recommendedTitle = new JLabel("Recommended jobs:", SwingConstants.LEFT);
        recommendedTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        recommendedTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Bottom padding
        jobsPanel.add(recommendedTitle, BorderLayout.NORTH);

        // Grid for job cards
        JPanel jobCardsGrid = new JPanel(new GridLayout(0, 2, 20, 20)); // 2 columns, auto rows, spacing
        jobCardsGrid.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // No extra padding here

        // Add sample job cards
        String[] companies = {"Company A", "Company B", "Company C", "Company D", "Company E", "Company F"};
        for (String company : companies) {
            jobCardsGrid.add(createJobCard(company, "Description of Job", "- location", "- monthly salary"));
        }

        // Wrap the grid in a JScrollPane to handle many job listings
        JScrollPane scrollPane = new JScrollPane(jobCardsGrid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border

        jobsPanel.add(scrollPane, BorderLayout.CENTER);

        return jobsPanel;
    }

    private JPanel createJobCard(String companyName, String description, String location, String salary) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Vertical stack
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), // Outer border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Inner padding
        ));
        cardPanel.setBackground(Color.WHITE); // Make cards distinct

        JLabel companyLabel = new JLabel(companyName);
        companyLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        companyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(companyLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(descLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel locLabel = new JLabel(location);
        locLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        locLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(locLabel);

        JLabel salaryLabel = new JLabel(salary);
        salaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        salaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(salaryLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer before button

        JButton viewButton = new JButton("View");
        viewButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        viewButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align button with other text
        viewButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing details for " + companyName));
        cardPanel.add(viewButton);

        cardPanel.add(Box.createVerticalGlue()); // Push content to top of card

        return cardPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JobSearchGUI::new);
    }
}