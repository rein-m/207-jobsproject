package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSearchGUI extends JFrame {
    private static final int JOBS_PER_PAGE = 8; // Number of JobPanel in one page, could always change it

    //TODO: this should be from the database, change it to the actual data.
    private final List<String> ALL_COMPANIES = Arrays.asList(
            "Company 1", "Company 2", "Company 3", "Company 4", "Company 5",
            "Company 6", "Company 7", "Company 8", "Company 9", "Company 10",
            "Company 11", "Company 12", "Company 13", "Company 14", "Company 15","Company 16",
            "Company 17", "Company 18", "Company 19", "Company 20",
            "Company 21", "Company 22", "Company 23", "Company 24", "Company 25"
    );
    private int currentPage = 0; // Starts at index 0 (Page 1)
    private JPanel jobCardsGrid;
    private JButton nextPageButton;
    private JButton prevPageButton;

    //TODO: Add or remove list if createFiltersPanel is changing.
    private List<JCheckBox> scheduleFilters = new ArrayList<>();
    private List<JCheckBox> typeFilters = new ArrayList<>();
    private List<JCheckBox> salaryFilters = new ArrayList<>();

// -----------------------------------
    public JobSearchGUI() {
        super("JobGPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Setting a larger default size for the UI
        setLocationRelativeTo(null); // Center the window

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15)); // Add spacing between regions
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Outer padding
        add(mainPanel);

        // 1. Top Bar (Image, Search, Profile)
        mainPanel.add(createTopBar(), BorderLayout.NORTH);

        // 2. Main Content Area (Filters + Recommended Jobs)
        JPanel contentArea = new JPanel(new BorderLayout(20, 0)); // Spacing between filters and jobs
        contentArea.add(createFiltersPanel(), BorderLayout.WEST);
        contentArea.add(createRecommendedJobsPanel(), BorderLayout.CENTER);
        mainPanel.add(contentArea, BorderLayout.CENTER);

        // update when next page and prev page button clicked
        mainPanel.add(createPaginationPanel(), BorderLayout.SOUTH);
        updateJobDisplay();

        this.setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel topBarPanel = new JPanel(new BorderLayout(10, 0));

        Creating_Logo(topBarPanel);

        // Center Section: Search Bar and Search Button
        JPanel searchAreaPanel = new JPanel(new BorderLayout(5, 0)); // 5px spacing between bar and button

        // Search Bar
        JTextField searchBar = new JTextField("Enter keywords or job title...");
        searchBar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        searchBar.setHorizontalAlignment(JTextField.CENTER); // Align text left

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        searchButton.setPreferredSize(new Dimension(120, 50)); // Give the button a fixed size

        // The Action Listener for the searchButton
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchBar.getText();

                // 1. Gather all selected filters into a single String and put it tgt.
                String filters = collectSelectedFilters();
                String message = "Search results for: \"" + searchTerm + "\"\n\n" + filters;

                // 3. Display the message
                JOptionPane.showMessageDialog(JobSearchGUI.this,
                        message,
                        "Search Initiated",
                        JOptionPane.INFORMATION_MESSAGE);

                // TODO: Call the actual job filtering and search method here
            }
        });

        searchAreaPanel.add(searchBar, BorderLayout.CENTER);
        searchAreaPanel.add(searchButton, BorderLayout.EAST);

        // Add the combined search area to the CENTER of the main top bar
        topBarPanel.add(searchAreaPanel, BorderLayout.CENTER);

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

    /**
     * Helper method for gathering all fliter object to the search button.
     * @return A formatted String of all selected filters.
     */
    private String collectSelectedFilters() {
        StringBuilder sb = new StringBuilder("Selected Filters:\n");
        int selectedCount = 0;
        //TODO: Add or remove list if createFiltersPanel is changing.
        List<List<JCheckBox>> allFilterLists = List.of(scheduleFilters, typeFilters, salaryFilters);

        for (List<JCheckBox> filterList : allFilterLists) {
            for (JCheckBox cb : filterList) {
                if (cb.isSelected()) {
                    sb.append(" • ").append(cb.getText()).append("\n");
                    selectedCount++;
                }
            }
        }

        if (selectedCount == 0) {
            return "No filters selected. Displaying all relevant jobs.";
        } else {
            return sb.toString();
        }
    }

    private static void Creating_Logo(JPanel topBarPanel) {
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        //TODO: This should be inside the project, if not download and put it in project from discord.
        ImageIcon originalIcon = new ImageIcon("JobGPT image.png");
        Image originalImage = originalIcon.getImage();

        //Scale the image
        int targetWidth = 80;
        int targetHeight = 80;
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);


        //Create and set the new, scaled icon to the label
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);
        imageLabel.setPreferredSize(new Dimension(targetWidth, targetHeight));

        topBarPanel.add(imageLabel, BorderLayout.WEST);
    }

    private JPanel createFiltersPanel() {
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS)); // Stack vertically
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Right padding for separation
        filtersPanel.setPreferredSize(new Dimension(200, 0)); // Fixed width for filters

        JLabel filtersTitle = new JLabel("Filters:");
        filtersTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        filtersTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        sectionPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        //TODO: Change the titles if createFiltersPanel is changing.
        List<JCheckBox> targetList;
        if (title.equals("Working schedule")) {
            targetList = scheduleFilters;
        } else if (title.equals("Types of Jobs")) {
            targetList = typeFilters;
        } else if (title.equals("Month salary")) {
            targetList = salaryFilters;
        } else {
            targetList = new ArrayList<>(); // Default fallback
        }

        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            sectionPanel.add(checkBox);

            // *** ADD THE CHECKBOX TO THE MEMBER LIST ***
            targetList.add(checkBox);
        }
        return sectionPanel;
    }

    private JPanel createRecommendedJobsPanel() {
        JPanel jobsPanel = new JPanel(new BorderLayout());

        JLabel recommendedTitle = new JLabel("Recommended jobs:", SwingConstants.LEFT);
        recommendedTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        recommendedTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        jobsPanel.add(recommendedTitle, BorderLayout.NORTH);

        // Initialize the job card grid panel (it will be populated later)
        jobCardsGrid = new JPanel(new GridLayout(0, 2, 20, 20));
        jobCardsGrid.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(jobCardsGrid,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        jobsPanel.add(scrollPane, BorderLayout.CENTER);

        return jobsPanel;
    }


    //TODO: this should be from the database, change it to the actual data.
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

        JButton viewAndApplyButton = new JButton("View and Apply");
        viewAndApplyButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        viewAndApplyButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        //TODO: implement this method. You might wanna create a new UI or new Apply button for this.
        viewAndApplyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing details for " + companyName));
        cardPanel.add(viewAndApplyButton);

        cardPanel.add(Box.createVerticalGlue());

        return cardPanel;
    }

    private JPanel createPaginationPanel() {
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        prevPageButton = new JButton("Previous Page");
        prevPageButton.setEnabled(false);
        prevPageButton.addActionListener(e -> {
            currentPage--;
            updateJobDisplay();
        });

        nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(e -> {
            currentPage++;
            updateJobDisplay();
        });

        paginationPanel.add(prevPageButton);
        paginationPanel.add(nextPageButton);

        return paginationPanel;
    }
    /**
     * Core logic to clear the grid, load the correct subset of jobs, and refresh the UI.
     */
    private void updateJobDisplay() {
        // 1. Calculate the start and end index for the current page
        int startIndex = currentPage * JOBS_PER_PAGE;
        int endIndex = Math.min(startIndex + JOBS_PER_PAGE, ALL_COMPANIES.size());

        // 2. Clear the existing job cards
        jobCardsGrid.removeAll();

        // 3. Check if there are results for this page
        if (startIndex < ALL_COMPANIES.size()) {
            // 4. Populate the grid with job cards for the current page
            for (int i = startIndex; i < endIndex; i++) {
                String company = ALL_COMPANIES.get(i);
                //TODO: Switch it to the actual data.
                jobCardsGrid.add(createJobCard(company, "Role at " + company, "location would be at (Actual loction)", "Salary: $5000+"));
            }
        } else {
            // Handle case where page index is out of bounds (shouldn't happen with button logic)
            jobCardsGrid.add(new JLabel("No more job listings.", SwingConstants.CENTER));
        }

        // Prev and Next button
        prevPageButton.setEnabled(currentPage != 0);
        nextPageButton.setEnabled(endIndex < ALL_COMPANIES.size());

        // Refresh the layout to show new components
        jobCardsGrid.revalidate();
        jobCardsGrid.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JobSearchGUI::new);
    }
}
