package view;

import javax.swing.*;
import java.awt.*;

public class ApplicantLoggedInPage {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {


            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


            JFrame frame = new JFrame("Applicant Main Login Page");
            frame.setMinimumSize(new java.awt.Dimension(300,200));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

    }

}
