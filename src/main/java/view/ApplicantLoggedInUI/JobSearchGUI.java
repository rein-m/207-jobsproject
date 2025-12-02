package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Objects;

public class JobSearchGUI extends JFrame {
    private static final int JOBS_PER_PAGE = 4; // Number of JobPanel in one page, could always change it

    //TODO: this should be from the database, change it to the actual data.
    private final List<Map<String, String>> ALL_COMPANIES = List.of(
            Map.of(
                    "id", "4297602754",
                    "url", "https://www.linkedin.com/jobs/view/4297602754/",
                    "title", "Software Engineering Intern (Summer)",
                    "company", "Datadog",
                    "location", "Boston, MA",
                    "description", "About the job\n\n We’re looking for interns to join us to help collect, aggregate, visualize, and analyze high-scale metrics, logs, and application data.netes across tens of thousands of nodes? Looking to build out the platform that powers Datadog’s newest offerings, such as BitsAI or App Builder? Want to see your work actually impact and improve the product in a meaningful way?",
                    "salary", "$4,800/month"
            ),
            Map.of(
                    "id", "4336331206",
                    "url", "https://www.linkedin.com/jobs/view/4336331206/",
                    "title", "Software Engineer Intern (Remote)",
                    "company", "Taskify AI",
                    "location", "United States",
                    "description", "About the job\nWe’re looking for a dependable, adaptable professional to support day-to-day tasks and ensure smooth operations. This role is straightforward, structured, and easy to learn, making it ideal for anyone who wants a stable income, flexibility, and real-world experience.\\nLocation:\\nRemote (US, UK, Canada, Australia, New Zealand, Ireland)\\nPay:\\nEarn between $5,000 - $8,000 per month\\nFlexible Hours | Weekly Pay | No Experience Needed | Work From Anywhere\\",
                    "salary", "$4,480/month"
            ),
            Map.of(
                    "id", "4309853502",
                    "url", "https://www.linkedin.com/jobs/view/4309853502/",
                    "title", "Undergrad Software Engineering Intern",
                    "company", "Twitch",
                    "location", "San Francisco, CA",
                    "description", "About the job\nPlease apply to this job with your personal email address (not your University or Program account) to avoid important Twitch emails hitting your spam inbox.\\nAbout Us:\\nLaunched in 2011, Twitch is a global community that comes together each day to create multiplayer entertainment: unique, live, unpredictable experiences created by the interactions of millions. ",
                    "salary", "$5,600/month"
            ),
            Map.of(
                    "id", "4307571879",
                    "url", "https://www.linkedin.com/jobs/view/4307571879/",
                    "title", "Software Engineer - Internship",
                    "company", "SeatGeek",
                    "location", "New York, NY",
                    "description", "About the job\nSeatGeek believes live events are powerful experiences that unite humans. With our technological savvy and fan-first attitude we’re simplifying and modernizing the ticketing industry.\\nBy catering to both consumers and enterprises, we’re powering a new, open entertainment ecosystem where fans have effortless access to experiences, and teams, venues, and shows have seamless access to their audiences.",
                    "salary", "$5,120/month"
            ),
            Map.of(
                    "id", "4309202142",
                    "url", "https://www.linkedin.com/jobs/view/4309202142/",
                    "title", "Software Engineer I, Entry-Level (Graduation Date: Fall 2025-Summer 2026)",
                    "company", "DoorDash",
                    "location", "San Francisco, CA",
                    "description", "About the job\nAbout The Team\nDoorDash is building the world’s most reliable on-demand logistics engine for delivery! We’re looking for experienced engineers to join our fast-growing engineering team to help us develop a 24x7 global infrastructure system that powers DoorDash’s three-sided marketplace of consumers, merchants, and dashers.",
                    "salary", "$10,000/month"
            ),
            Map.of(
                    "id", "4336463938",
                    "url", "https://www.linkedin.com/jobs/view/4336463938/",
                    "title", "AI Assessment Specialist – Remote $100/hr",
                    "company", "Mercor",
                    "location", "United States",
                    "description", "About the job\nAbout The Job\nMercor\\nconnects elite creative and technical talent with leading AI research labs. Headquartered in San Francisco, our investors include\\nBenchmark\\n,\\nGeneral Catalyst\\n,\\nPeter Thiel\\n,\\nAdam D'Angelo\\n,\\nLarry Summers\\n, and\\nJack Dorsey\\n.\\nPosition:\\nMercor Generalist\\nType:\\nIndependent Contractor\\nCompensation:\\n$30–$100/hour\\nLocation:\\nRemote",
                    "salary", "$16,000/month"
            ),
            Map.of(
                    "id", "4322361530",
                    "url", "https://www.linkedin.com/jobs/view/4322361530/",
                    "title", "Software Engineer Intern",
                    "company", "Docusign",
                    "location", "Seattle, WA",
                    "description", "About the job\nCompany Overview\nDocusign brings agreements to life. Over 1.5 million customers and more than a billion people in over 180 countries use Docusign solutions to accelerate the process of doing business and simplify people’s lives. With intelligent agreement management, Docusign unleashes business-critical data that is trapped inside of documents. ",
                    "salary", "$4,800/month"
            ),
            Map.of(
                    "id", "4299615390",
                    "url", "https://www.linkedin.com/jobs/view/4299615390/",
                    "title", "Spring/Summer 2026 University Entry-level STEM Pipeline",
                    "company", "Peraton",
                    "location", "Reston, VA",
                    "description", "About the job\nAbout Peraton\nPeraton is a next-generation national security company that drives missions of consequence spanning the globe and extending to the farthest reaches of the galaxy. As the world's leading mission capability integrator and transformative enterprise IT provider, we deliver trusted, highly differentiated solutions and technologies to protect our nation and allies. Peraton operates at the critical nexus between traditional and nontraditional threats across all domains: land, sea, space, air, and cyberspace. ",
                    "salary", "$4,000/month"
            )
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
        setSize(1200, 800); // Setting a larger default size for the UI
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15)); // Add spacing between regions
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        // Top Bar (Image, Search, Profile)
        mainPanel.add(createTopBar(), BorderLayout.NORTH);

        // Main Content Area (Filters + Recommended Jobs)
        JPanel contentArea = new JPanel(new BorderLayout(20, 0));
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

        // viewAndApplyButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
        //       companyName + "\n\n" + description + "\n\nLocation: " + location + "\nSalary: " + salary,
        //       "Job Details",
        //      JOptionPane.INFORMATION_MESSAGE));

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

                JOptionPane.showMessageDialog(JobSearchGUI.this, "DataDog" + "\n\n"  +
                             "About the job\\n\\n We’re looking for interns to join us to help collect, aggregate, visualize, and analyze high-scale metrics, logs, and application data.netes across tens of thousands of nodes? Looking to build out the platform that powers Datadog’s newest offerings, such as BitsAI or App Builder? Want to see your work actually impact and improve the product in a meaningful way?\""
                                      + "\n\nLocation: " + "Boston, MA" + "\nSalary: "
                                     + "$4,800/month",
                        "Job Details",
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

        //TODO: wait for merging and uncomment it.
//        profileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                UserAccountInfoView ProfileWindow = new UserAccountInfoView();
//                ProfileWindow.setVisible(true);
//            }
//        });
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
        ImageIcon originalIcon = new ImageIcon("pic.png");
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

        // Use JTextArea for description so it wraps and doesn't overflow
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setOpaque(false); // makes background match panel
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(descArea);
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

        // Optional: Show full job details in a popup
        viewAndApplyButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                companyName + "\n\n" + description + "\n\nLocation: " + location + "\nSalary: " + salary,
                "Job Details",
                JOptionPane.INFORMATION_MESSAGE));

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

        if (startIndex < ALL_COMPANIES.size()) {
            for (int i = startIndex; i < endIndex; i++) {

                Map<String, String> job = ALL_COMPANIES.get(i);

                String company = job.get("company");
                String title = job.get("title");
                String location = job.get("location");
                String description = job.get("description");
                String salary = job.getOrDefault("salary", "Salary info unavailable");

                jobCardsGrid.add(
                        createJobCard(
                                company,
                                description,
                                location,
                                salary
                        )
                );
            }
        } else {
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
