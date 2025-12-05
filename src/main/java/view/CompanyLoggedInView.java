package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.post_job.PostJobViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CompanyLoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "company logged in";
    private final JLabel companyName = new JLabel();
    private final JButton createJobButton = new JButton("Create A Job Posting");
    private final CompanyLoggedInViewModel companyLoggedInViewModel;
    private final PostJobViewModel postJobViewModel;
    private final ViewManagerModel viewManagerModel;

    public CompanyLoggedInView(CompanyLoggedInViewModel companyLoggedInViewModel, PostJobViewModel postJobViewModel, ViewManagerModel viewManagerModel) {
        this.companyLoggedInViewModel = companyLoggedInViewModel;
        this.companyLoggedInViewModel.addPropertyChangeListener(this);
        this.postJobViewModel = postJobViewModel;
        this.viewManagerModel = viewManagerModel;
        //Panel for Company Name
        final JPanel companyNameInfo = new JPanel();
        companyNameInfo.add(new JLabel("Company Name:"));
        companyNameInfo.add(companyName);

        //JPanel for current postings
        final JPanel allJobsPanel = new JPanel();

        //Scrollable Panel displaying current job postings
        final JScrollPane manageJobsPanel = new JScrollPane(allJobsPanel);
        manageJobsPanel.setColumnHeaderView(new JLabel("Manage Jobs"));
        manageJobsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Panel for edit account and create job posting buttons
        final JPanel buttons =  new JPanel();
        buttons.add(createJobButton);

        //Add action listener to createJobButton
        createJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(createJobButton)) {
                    viewManagerModel.setState(postJobViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.setLayout(new BorderLayout());

        this.add(companyNameInfo,  BorderLayout.NORTH);
        this.add(manageJobsPanel, BorderLayout.WEST);
        this.add(buttons, BorderLayout.EAST);

    }
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final CompanyLoggedInState state = (CompanyLoggedInState) e.getNewValue();
            this.companyName.setText(state.getCompanyName());
        }
    }

    public String  getViewName() {
        return viewName;
    }
}


