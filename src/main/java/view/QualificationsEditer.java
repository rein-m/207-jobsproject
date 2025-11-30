package view;

import javax.swing.*;
import java.awt.*;

public class QualificationsEditer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //String filePath = "C:/Users/unwor/OneDrive/Documents/resume.pdf";
            //TODO remove this code later that's just for testing and compiling this file
            JFrame frame = new JFrame("Qualifications Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // main panel
            JPanel editQualificationsPanel = new JPanel(new GridLayout(1, 2));
            //TODO remove this code later that's just for testing and compiling this file
            frame.setContentPane(editQualificationsPanel);

//            // left panel code:
//            JPanel resumePanel = new JPanel();
//            resumePanel.add(new JLabel("PDF Viewer Goes Here")); // placeholder

            // right panel code:
            JPanel qualificationsPanel = new JPanel();
            qualificationsPanel.setLayout(new BoxLayout(qualificationsPanel, BoxLayout.Y_AXIS));
            // Adds rows
            qualificationsPanel.add(makeRow("Skills:"));
            qualificationsPanel.add(makeRow("Programming Languages:"));
            qualificationsPanel.add(makeRow("Tools & Technologies:"));
            qualificationsPanel.add(makeRow("Frameworks & Libraries:"));
            qualificationsPanel.add(makeRow("Education:"));
            qualificationsPanel.add(makeRow("Work Experience:"));
            qualificationsPanel.add(makeRow("Projects:"));


            // compile left and right to main panel:
//            editQualificationsPanel.add(resumePanel);
            editQualificationsPanel.add(qualificationsPanel);

            //TODO remove this code later that's just for testing and compiling this file
            frame.pack();
            frame.setVisible(true);
        });
    }
    private static JPanel makeRow(String labelText) {
        JPanel row = new JPanel(new GridLayout(1, 1));
        row.add(new JLabel(labelText));
        row.add(new JTextField(20));
        return row;
    }
}
