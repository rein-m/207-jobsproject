package view.ApplicantLoggedInUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;


public class ApplicantLoggedInView extends JFrame {

    private String selectedCompany = null;

    // Map to simulate which companies have a PDF available
    private final Map<String, String> companyPdfFiles = new HashMap<>();
    public final String viewName = "user loggedin";
    public String getViewName(){
        return viewName;
    }

    public ApplicantLoggedInView() {

        super("Applicant GUI");
        this.setSize(500, 600);
        this.setLayout(new BorderLayout(10, 10));

        //TODO: delete this when database is done, implemant the new function.
        // Replace the boolean map setup with file paths
        companyPdfFiles.put("Datadog", "no_file");
        companyPdfFiles.put("Taskify AI", "no_file");
        // IMPORTANT: Use the actual path to your PDF file here.
        companyPdfFiles.put("LARANA, INC.", "/Users/xuan/207-jobsproject/src/main/java/view/ApplicantLoggedInUI/LARANA, INC..pdf");
        companyPdfFiles.put("SeatGeek", "no_file");
        companyPdfFiles.put("DoorDash", "no_file");
        companyPdfFiles.put("Mercor", "no_file");

        // Top title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel applicantNameLabel = new JLabel("SpongeBob SquarePants");
        applicantNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(applicantNameLabel);

        //  Left list column
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //TODO: Change this to the get.data function when everything done, the companyNamse should be list of
        // company objects.
        // --- Simulated Data Setup ---
        //  For demonstration: only Company C have "PDFs"
        String[] companyNames = {"Datadog", "Taskify AI", "LARANA, INC.", "SeatGeek", "DoorDash", "Mercor"};


        //TODO: implemant this new function when database is done.
//        for (String Company : companyNames){
//            hasPdfData.put(Company, CompanyHasPDF(Company));
//        }

        // Use a ButtonGroup to ensure only one company is selected visually
        ButtonGroup companyButtonGroup = new ButtonGroup();

        // Create JRadioButtons instead of JButtons for easy selection/tracking
        for (String company : companyNames) {
            JRadioButton rb = new JRadioButton(company);
            rb.setFont(new Font("SansSerif", Font.PLAIN, 18));

            // Console check for JRadioButtons
            rb.addActionListener(e -> {
                selectedCompany = rb.getText();
                System.out.println("Selected: " + selectedCompany); // Console check
            });

            companyButtonGroup.add(rb);
            listPanel.add(rb);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(250, 400));

        // --- Right buttons ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 20, 20));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

        JButton yesBtn = new JButton("View");
        yesBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Implement the logic for the "View" button, jump to helper function addActionListener
        yesBtn.addActionListener(e -> handleViewAction());

        JButton noBtn = new JButton("Exit");
        noBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        // Use this.dispose() since the class now extends JFrame
        noBtn.addActionListener(e -> this.dispose());

        rightPanel.add(yesBtn);
        rightPanel.add(noBtn);


        // --- Main center panel (list + buttons) ---
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
        centerPanel.add(scrollPane, BorderLayout.WEST);
        centerPanel.add(rightPanel, BorderLayout.CENTER);

        // --- Add components to the frame ---
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        // Do NOT call setVisible(true) here; it will be called externally
    }

    /**
     * Handles the logic for the "View" button click.
     */
    private void handleViewAction() {
        if (selectedCompany == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a company from the list first.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String filePath = companyPdfFiles.get(selectedCompany);

        // 2. Check if a file exists for this company (using the "no_file" placeholder)
        if (filePath == null || filePath.equals("no_file")) {
            // No PDF Found
            JOptionPane.showMessageDialog(this,
                    "There is no information (PDF) available for: " + selectedCompany + ".",
                    "No Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Attempt to open the file
        try {
            File pdfFile = new File(filePath);

            // IMPORTANT: Check if the file actually exists before trying to open it
            if (!pdfFile.exists()) {
                JOptionPane.showMessageDialog(this,
                        "Error: File not found at the specified location: " + filePath,
                        "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Use the Desktop class to open the file with the system's default PDF viewer
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
                JOptionPane.showMessageDialog(this,
                        "PDF for " + selectedCompany + ".",
                        "File Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Handle environment where Desktop is not supported (e.g., headless server)
                JOptionPane.showMessageDialog(this,
                        "Error: Automatic file opening is not supported on this system.",
                        "System Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            // Handle errors like permission denied, or file not found even after the check
            JOptionPane.showMessageDialog(this,
                    "An error occurred while trying to open the file: " + e.getMessage(),
                    "Open Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
