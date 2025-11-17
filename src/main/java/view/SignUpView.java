package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeEvent;

import interface_adapter.ViewManagerModel;
import view.companyLoginView; // import companyLoginView  -- currently waiting for whoever is making loginView
import view.userLoginView; // import userLoginView -- waiting for whoever is making loginView



public class SignUpView {

    private final CompanyLoginView companyLoginView; // initialize the companyLoginview
    private final UserLoginView userLoginView; // initialize the userLoginView
    private final ViewManagerModel viewManagerModel; // initialize the ViewModel for switching between views/navigation
    public static void main (String[] args) {

        // FlatLightLaf.setup(); -- Optional if using FlatLightLaf for LookAndFeel/making GUI look cooler

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // set LookAndFeel
        }
        catch (UnsupportedLookAndFeelException e) {
            System.out.println("Sorry, that LookAndFeel is not supported. Please select another");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Sorry, that class was not found. Please check class declarations and naming carefully");
        }

        catch (InstantiationException e) {
            System.out.println("InstantiationException. Something was not correctly instantiated");
        }

        catch (IllegalAccessException e) {
            System.out.println("ILLEGAL ACCESS!!! GO AWAY");

        }

        SwingUtilities.invokeLater(() -> {

            JPanel signUpPanel = new JPanel(); // Create new signUpPanel
            signUpPanel.setLayout(new GridBagLayout()); // set signUpPanel layout to GridBagLayout (Can change if want different layoyt)
            GridBagConstraints c = new GridBagConstraints(); // new constraints for the GridBagLayout
            // Set GridBag constraints
            c.weightx = .25;
            c.weighty = .25;
            c.insets = new Insets(40, 10, 40, 10); // insets: gridx, gridy, gridwidth, gridheight
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.fill = GridBagConstraints.BOTH;

            // Create userButton
            JButton userButton = new JButton("User");
            userButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true)); //if true then rounded with light gray border
            userButton.setFocusPainted(false);
            userButton.setContentAreaFilled(true);
            userButton.setAlignmentX(Component.CENTER_ALIGNMENT); // align button in center

            JButton companyButton = new JButton("Company");
            companyButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true)); // if true then rounded with light gray border
            companyButton.setFocusPainted(false);
            companyButton.setContentAreaFilled(true);
            companyButton.setAlignmentX(Component.CENTER_ALIGNMENT); // align button in center

            signUpPanel.add(userButton, c); // add buttons to signUpPanel with parameters.
            signUpPanel.add(companyButton, c);

            // if user button is clicked, navigate to the userLoginView
            userButton.addActionLisener(actionPerformed(e) ->{
                if (e.getSource().equals(userButton)) {
                    viewManagerModel.setState(userLoginView); // will navigate to userLoginView once that's done :3
                   // viewManagerModel.firePropertyChanged(); not sure if I need this
                }
            });

            // if company button is clicked, navigate to companyLoginView
            companyButton.addActionListener(actionPerformed(e) -> {
                if (e.getSource().equals(companyButton)) {
                    viewManagerModel.setState(companyLoginView); // will navigate to companyLoginView once that's done :)
                    // viewManagerModel.firePropertyChanged(); //
                }
            });

            JFrame frame = new JFrame("Sign Up");
            frame.setLayout(new BorderLayout());
            frame.add(signUpPanel, BorderLayout.CENTER);

            // Set frame and add signUpPanel to frame
            frame.setContentPane(signUpPanel);
            frame.setMinimumSize(new java.awt.Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        });
    }
}
