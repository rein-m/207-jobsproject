package view;

//import org.icepdf.ri.common.SwingController;
//import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;
import java.awt.*;

public class QualificationsEditer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //TODO remove this code later that's just for testing and compiling this file
            //String filePath = "C:/Users/unwor/OneDrive/Documents/resume.pdf";
            JFrame frame = new JFrame("Qualifications Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // main panel
            JPanel editQualificationsPanel = new JPanel(new GridLayout(1, 2));
            //TODO remove this code later that's just for testing and compiling this file
            frame.setContentPane(editQualificationsPanel);

            // left panel code:
            JPanel resumePanel = new JPanel();
            resumePanel.add(new JLabel("PDF Viewer Goes Here")); // placeholder

            // ICEpdf controller + view builder
            //SwingController controller = new SwingController();
            //SwingViewBuilder factory = new SwingViewBuilder(controller);

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
            editQualificationsPanel.add(resumePanel);
            editQualificationsPanel.add(qualificationsPanel);

            //TODO remove this code later that's just for testing and compiling this file
            frame.pack();
            frame.setVisible(true);
            //controller.openDocument(filePath);
        });
    }
    private static JPanel makeRow(String labelText) {
        JPanel row = new JPanel(new GridLayout(1, 2));
        row.add(new JLabel(labelText));
        row.add(new JTextField(20));
        return row;
    }
}
