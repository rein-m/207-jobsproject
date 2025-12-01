package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountState;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.edit_company_account.EditCompanyAccountController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

    private final JPanel exitPanel = new JPanel();
    private final CardLayout exitCardLayout = new CardLayout();
    private final JButton exitButton = new JButton("Exit");
    private final JButton cancelButton = new JButton("Cancel");

    private final JPanel editPanel = new JPanel();
    private final CardLayout editCardLayout = new CardLayout();
    private final JButton editButton = new JButton("Edit");
    private final JButton saveButton = new JButton("Save");

    private CompanyAccountViewModel companyAccountViewModel;
    private CompanyLoggedInViewModel companyLoggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private EditCompanyAccountController editCompanyAccountController;

    public CompanyAccountView(CompanyAccountViewModel companyAccountViewModel) {
        this.companyAccountViewModel = companyAccountViewModel;

        final JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JLabel("Name:"));
        this.nameTextField.setEditable(false);
        this.nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        namePanel.add(nameTextField);
        namePanel.setMaximumSize(new Dimension(700,100));
        //delete later
        nameTextField.setText("AMD");

        final JPanel websitePanel = new JPanel();
        websitePanel.setLayout(new BoxLayout(websitePanel, BoxLayout.X_AXIS));
        websitePanel.add(new JLabel("Website:"));
        this.websiteTextField.setEditable(false);
        this.websiteTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        websitePanel.add(websiteTextField);
        websitePanel.setMaximumSize(new Dimension(700,100));
        //delete later
        websiteTextField.setText("AMD");

        final JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.add(new JLabel("Email:"));
        this.emailTextField.setEditable(false);
        this.emailTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        emailPanel.add(emailTextField);
        emailPanel.setMaximumSize(new Dimension(700,100));
        //delete later
        emailTextField.setText("AMD");

        final JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.X_AXIS));
        numberPanel.add(new JLabel("Number:"));
        this.numberTextField.setEditable(false);
        this.numberTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        numberPanel.add(numberTextField);
        numberPanel.setMaximumSize(new Dimension(700,100));
        //delete later
        numberTextField.setText("AMD");

        final JPanel locationPanel = new JPanel();
        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.X_AXIS));
        locationPanel.add(new JLabel("Location:"));
        this.locationTextField.setEditable(false);
        this.locationTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        locationPanel.add(locationTextField);
        locationPanel.setMaximumSize(new Dimension(700,100));
        //delete later
        locationTextField.setText("AMD");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(exitButton)) {
                    final CompanyAccountState currentState = companyAccountViewModel.getState();
                    nameTextField.setText(currentState.getCompanyName());
                    websiteTextField.setText(currentState.getWebsite());
                    emailTextField.setText(currentState.getEmail());
                    numberTextField.setText(currentState.getNumber());
                    locationTextField.setText(currentState.getLocation());
                    viewManagerModel.setState(companyLoggedInViewModel.getViewName());
                    viewManagerModel.firePropertyChange();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancelButton)) {
                    nameTextField.setEditable(false);
                    websiteTextField.setEditable(false);
                    emailTextField.setEditable(false);
                    numberTextField.setEditable(false);
                    locationTextField.setEditable(false);
                    exitCardLayout.show(exitPanel, "exit");
                    editCardLayout.show(editPanel, "edit");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(editButton)) {
                    nameTextField.setEditable(true);
                    websiteTextField.setEditable(true);
                    emailTextField.setEditable(true);
                    numberTextField.setEditable(true);
                    locationTextField.setEditable(true);
                    exitCardLayout.show(exitPanel, "cancel");
                    editCardLayout.show(editPanel, "save");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(saveButton)) {
                    final CompanyAccountState currentState = companyAccountViewModel.getState();
                    editCompanyAccountController.execute(currentState.getCompanyName(), currentState.getWebsite()
                            , currentState.getEmail(), currentState.getNumber(), currentState.getLocation());
                    nameTextField.setEditable(false);
                    websiteTextField.setEditable(false);
                    emailTextField.setEditable(false);
                    numberTextField.setEditable(false);
                    locationTextField.setEditable(false);
                    exitCardLayout.show(exitPanel, "exit");
                    editCardLayout.show(editPanel, "edit");
                }
            }
        });
        saveButton.setMaximumSize(new Dimension(150,60));

        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CompanyAccountState currentState = companyAccountViewModel.getState();
                currentState.setCompanyName(nameTextField.getText());
                companyAccountViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        websiteTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CompanyAccountState currentState = companyAccountViewModel.getState();
                currentState.setWebsite(websiteTextField.getText());
                companyAccountViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        emailTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CompanyAccountState currentState = companyAccountViewModel.getState();
                currentState.setEmail(emailTextField.getText());
                companyAccountViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        numberTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CompanyAccountState currentState = companyAccountViewModel.getState();
                currentState.setNumber(numberTextField.getText());
                companyAccountViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        locationTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CompanyAccountState currentState = companyAccountViewModel.getState();
                currentState.setLocation(locationTextField.getText());
                companyAccountViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(accountinformationLabel);
        this.add(namePanel);
        this.add(websitePanel);
        this.add(emailPanel);
        this.add(numberPanel);
        this.add(locationPanel);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        this.exitPanel.setLayout(exitCardLayout);
        this.exitPanel.setMaximumSize(new Dimension(150,60));
        this.exitPanel.add(exitButton, "exit");
        this.exitPanel.add(cancelButton,"cancel");
        this.exitCardLayout.show(exitPanel, "exit");
        buttonsPanel.add(exitPanel);

        this.editPanel.setLayout(editCardLayout);
        this.editPanel.setMaximumSize(new Dimension(150,60));
        this.editPanel.add(editButton, "edit");
        this.editPanel.add(saveButton, "save");
        this.editCardLayout.show(editPanel, "edit");
        buttonsPanel.add(editPanel);
        this.add(buttonsPanel);
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

    public void setEditCompanyAccountController(EditCompanyAccountController editCompanyAccountController){
        this.editCompanyAccountController = editCompanyAccountController;
    }

    public String getViewName() {
        return viewName;
    }
}
