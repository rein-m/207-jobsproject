package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingView extends JPanel implements ActionListener {

    public final String viewName = "landingView";
    private final ViewManagerModel viewManagerModel;

    public LandingView (ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.25;
        c.weighty = 0.25;
        c.insets = new Insets(40, 10, 40, 10);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        JButton userButton = new JButton("User");
        styleButton(userButton);

        JButton companyButton = new JButton("Company");
        styleButton(companyButton);

        this.add(userButton, c);
        this.add(companyButton, c);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("user login");
                viewManagerModel.firePropertyChange();
            }
        });

        companyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("company login");
                viewManagerModel.firePropertyChange();
            }
        });
    }

    private void styleButton(JButton btn) {
        btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setBackground(Color.WHITE);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}