package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountState;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CompanyAccountView extends Panel implements PropertyChangeListener {
    private final String viewName = "company account";
    private final JLabel accountinformationLabel = new JLabel("Company Account Information");
    private final JTextField nameTextField = new JTextField( 20);
    private final JTextField websiteTextField = new JTextField(20);
    private final JTextField emailTextField = new JTextField(20);
    private final JTextField numberTextField = new JTextField(20);
    private final JTextField locationTextField = new JTextField(20);
    private final JButton exitButton = new JButton("Exit");
    private CompanyAccountViewModel companyAccountViewModel;
    private CompanyLoggedInViewModel companyLoggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public CompanyAccountView(CompanyAccountViewModel companyAccountViewModel) {
        this.companyAccountViewModel = companyAccountViewModel;

        final JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JLabel("Name:"));
        this.nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        namePanel.add(nameTextField);
        namePanel.setMaximumSize(new Dimension(700,100));

        final JPanel websitePanel = new JPanel();
        websitePanel.setLayout(new BoxLayout(websitePanel, BoxLayout.X_AXIS));
        websitePanel.add(new JLabel("Website:"));
        this.websiteTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        websitePanel.add(websiteTextField);
        websitePanel.setMaximumSize(new Dimension(700,100));

        final JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.add(new JLabel("Email:"));
        this.emailTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        emailPanel.add(emailTextField);
        emailPanel.setMaximumSize(new Dimension(700,100));

        final JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.X_AXIS));
        numberPanel.add(new JLabel("Number:"));
        this.numberTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        numberPanel.add(numberTextField);
        numberPanel.setMaximumSize(new Dimension(700,100));

        final JPanel locationPanel = new JPanel();
        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.X_AXIS));
        locationPanel.add(new JLabel("Location:"));
        this.locationTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        locationPanel.add(locationTextField);
        locationPanel.setMaximumSize(new Dimension(700,100));

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(exitButton)) {
                    viewManagerModel.setState(companyLoggedInViewModel.getViewName());
                    viewManagerModel.firePropertyChange();
                }
            }
        });
        exitButton.setMaximumSize(new Dimension(150,60));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(accountinformationLabel);
        this.add(namePanel);
        this.add(websitePanel);
        this.add(emailPanel);
        this.add(numberPanel);
        this.add(locationPanel);
        this.add(exitButton);
    }
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final CompanyAccountState state = (CompanyAccountState) e.getNewValue();
            this.nameTextField.setText(state.getCompanyName());
            this.websiteTextField.setText(state.getWebsite());
            this.emailTextField.setText(state.getEmail());
            this.numberTextField.setText(state.getNumber());
            this.locationTextField.setText(state.getLocation());
        }
    }

    public void setCompanyLoggedInViewModel(CompanyLoggedInViewModel companyLoggedInViewModel) {
        this.companyLoggedInViewModel = companyLoggedInViewModel;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public String getViewName() {
        return viewName;
    }
}
