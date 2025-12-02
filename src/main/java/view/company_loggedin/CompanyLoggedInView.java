package view.company_loggedin;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountState;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.post_job.PostJobViewModel;
import interface_adapter.pull_company_data.PullCompanyDataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CompanyLoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "company logged in";
    private final JLabel companyNameLabel = new JLabel("Google");
    private final JButton editAccountButton = new JButton("Edit Account");
    private final JButton createJobButton = new JButton("Create A Job Posting");
    private final CompanyLoggedInViewModel companyLoggedInViewModel;
    private PostJobViewModel postJobViewModel;
    private CompanyAccountViewModel companyAccountViewModel;
    private ViewManagerModel viewManagerModel;
    private final DefaultListModel<JobData> defaultListModel = new DefaultListModel<>();
    private PullCompanyDataController pullCompanyDataController;

    public CompanyLoggedInView(CompanyLoggedInViewModel companyLoggedInViewModel) {
        this.companyLoggedInViewModel = companyLoggedInViewModel;
        this.companyLoggedInViewModel.addPropertyChangeListener(this);

        //Panel for Company Name
        final JPanel companyNamePanel = new JPanel();
        companyNamePanel.setLayout(new BoxLayout(companyNamePanel, BoxLayout.X_AXIS));
        companyNamePanel.add(new JLabel("Company Name:"));
        companyNamePanel.add(companyNameLabel);

        //JList, ListModel, Renderer for current postings

        //Extract job postings data from the state and store the values into the model
//        CompanyLoggedInState state = companyLoggedInViewModel.getState();
//        ArrayList<ArrayList<String>> jobListings = state.getJobListings();
//        for (int i = 0; i < jobListings.size(); i++) {
//            String title = jobListings.get(i).get(0);
//            String description = jobListings.get(i).get(1);
//            String location = jobListings.get(i).get(2);
//            defaultListModel.addElement(new JobData(title, description, location));
//        }
        //For testing purposes.delete once data access is working
        defaultListModel.addElement(new JobData("Data Scientist", "Full-Time", "Hybrid"));
        defaultListModel.addElement(new JobData("Product Manager", "Internship", "New York"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Intern", "drink matcha", "Toronto"));
//        defaultListModel.addElement(new JobData("Dj", "drink black coffee", "Miami"));
//        defaultListModel.addElement(new JobData("Intern", "drink water", "Vancouver"));

        //Create JList
        JList<JobData> list = new JList<>(defaultListModel);
//        list.setFixedCellWidth(300);
        //Add Renderer to JList
        list.setCellRenderer(new JobPanelRenderer());

        //Scrollable Panel displaying current job postings
        final JScrollPane manageJobsPane = new JScrollPane(list);
        manageJobsPane.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        manageJobsPane.setColumnHeaderView(new JLabel("Manage Jobs"));
        manageJobsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Panel for edit account and create job posting buttons
        final JPanel buttons =  new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setMaximumSize(new Dimension(300, 1000));
//        buttons.setPreferredSize(new Dimension(200, 400));

        editAccountButton.setMaximumSize(new Dimension(150, 60));
        createJobButton.setMaximumSize(new Dimension(150, 60));
//        editAccountButton.setPreferredSize(new Dimension(100, 100));
//        createJobButton.setPreferredSize(new Dimension(100, 100));

        //Adding the buttons to the buttons panel
        buttons.add(editAccountButton);
        buttons.add(createJobButton);

        //Add action listener to createJobButton
        createJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(createJobButton)) {
                    viewManagerModel.setState(postJobViewModel.getViewName());
                    viewManagerModel.firePropertyChange();
                }
            }
        });

        //Add action listener to editAccountButton
        editAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(editAccountButton)) {
                    viewManagerModel.setState(companyAccountViewModel.getViewName());
                    final CompanyAccountState state = companyAccountViewModel.getState();
                    pullCompanyDataController.execute(state.getIdentifier());
                    viewManagerModel.firePropertyChange();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(companyNamePanel);

        JPanel lowerHalf = new JPanel();
        lowerHalf.setLayout(new BoxLayout(lowerHalf, BoxLayout.X_AXIS));
        lowerHalf.add(manageJobsPane);
        lowerHalf.add(buttons);
        this.add(lowerHalf);
    }
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("state")) {
            final CompanyLoggedInState state = (CompanyLoggedInState) e.getNewValue();
            this.companyNameLabel.setText(state.getCompanyName());
        }
        else if (e.getPropertyName().equals("post job")) {
            final CompanyLoggedInState state = (CompanyLoggedInState) e.getNewValue();
            final ArrayList<ArrayList<String>> jobListings = state.getJobListings();
            List<String> job = jobListings.get(jobListings.size() - 1);
            String title = job.get(job.size() - 3);
            String description = job.get(job.size() - 2);
            String location = job.get(job.size() - 1);
            defaultListModel.addElement(new JobData(title, description, location));
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setCompanyAccountViewModel(CompanyAccountViewModel companyAccountViewModel) {
        this.companyAccountViewModel = companyAccountViewModel;
    }

    public void setPostJobViewModel(PostJobViewModel postJobViewModel) {
        this.postJobViewModel = postJobViewModel;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void setPullCompanyDataController(PullCompanyDataController pullCompanyDataController) {
        this.pullCompanyDataController = pullCompanyDataController;
    }
}






