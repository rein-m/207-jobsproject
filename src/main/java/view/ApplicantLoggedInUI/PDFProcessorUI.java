package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PDFProcessorUI extends JFrame {

    private JTextField filePathField;
    private JButton browseButton;
    private JButton processButton;
    private JButton doneButton;
    private JTextArea statusArea;
    private JProgressBar progressBar;
    private File selectedPdfFile;

    public PDFProcessorUI() {
        initializeUI();
    }

    private void initializeUI() {
        // Frame setup
        setTitle("PDF Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Top panel for file selection
        JPanel fileSelectionPanel = createFileSelectionPanel();

        // Center panel for status display
        JPanel statusPanel = createStatusPanel();

        // Bottom panel for progress bar and process button
        JPanel controlPanel = createControlPanel();

        // Add panels to main panel
        mainPanel.add(fileSelectionPanel, BorderLayout.NORTH);
        mainPanel.add(statusPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createFileSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Select PDF File"));

        // File path text field
        filePathField = new JTextField();
        filePathField.setEditable(false);
        filePathField.setPreferredSize(new Dimension(400, 30));

        // Browse button
        browseButton = new JButton("Browse...");
        browseButton.setPreferredSize(new Dimension(100, 30));
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPdfFile();
            }
        });

        panel.add(filePathField, BorderLayout.CENTER);
        panel.add(browseButton, BorderLayout.EAST);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Status"));

        // Status text area
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statusArea.setText("Ready. Please select a PDF file to process.\n");

        JScrollPane scrollPane = new JScrollPane(statusArea);
        scrollPane.setPreferredSize(new Dimension(650, 300));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        // Process button
        processButton = new JButton("Process PDF");
        processButton.setPreferredSize(new Dimension(150, 35));
        processButton.setEnabled(false);
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPdf();
            }
        });

        doneButton = new JButton("Done");
        doneButton.setPreferredSize(new Dimension(150, 35));
        doneButton.setEnabled(true);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(processButton, BorderLayout.EAST);
        panel.add(doneButton, BorderLayout.SOUTH);

        return panel;
    }

    private void selectPdfFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select PDF File");

        // Sets filter for PDF files only
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF Files (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedPdfFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedPdfFile.getAbsolutePath());
            processButton.setEnabled(true);
            appendStatus("PDF file selected: " + selectedPdfFile.getName() + "\n");
            appendStatus("File size: " + (selectedPdfFile.length() / 1024) + " KB\n");
        }
    }

    private void processPdf() {
        if (selectedPdfFile == null || !selectedPdfFile.exists()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a valid PDF file first.",
                    "No File Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Disables buttons during processing
        processButton.setEnabled(false);
        browseButton.setEnabled(false);

        appendStatus("\n--- Starting PDF Processing ---\n");
        appendStatus("Processing file: " + selectedPdfFile.getName() + "\n");

        // Creates a SwingWorker for background processing
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {

                // Simulated processing with progress updates
                for (int i = 0; i <= 100; i += 10) {
                    Thread.sleep(300); // Simulate work
                    publish(i);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Update progress bar
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);
                appendStatus("Progress: " + progress + "%\n");
            }

            @Override
            protected void done() {
                try {
                    get(); // Check for exceptions
                    appendStatus("--- PDF Processing Complete! ---\n");
                    JOptionPane.showMessageDialog(PDFProcessorUI.this,
                            "PDF processed successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    appendStatus("ERROR: " + e.getMessage() + "\n");
                    JOptionPane.showMessageDialog(PDFProcessorUI.this,
                            "Error processing PDF: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Re-enable buttons
                processButton.setEnabled(true);
                browseButton.setEnabled(true);
                progressBar.setValue(0);
            }
        };

        worker.execute();
    }

    private void appendStatus(String message) {
        statusArea.append(message);
        statusArea.setCaretPosition(statusArea.getDocument().getLength());
    }

    // Method to get the selected PDF file (for external access)
    public File getSelectedPdfFile() {
        return selectedPdfFile;
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Creates and show UI on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PDFProcessorUI frame = new PDFProcessorUI();
                frame.setVisible(true);
            }
        });
    }
}