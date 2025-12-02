package view;

import interface_adapter.post_job.PostJobController;
import interface_adapter.post_job.PostJobState;
import interface_adapter.post_job.PostJobViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostJobView extends JPanel {

    private String viewName = "post job";
    private PostJobController postJobController = null;
    private final JTextField jobTitleInputField = new JTextField(20);
    private final JTextField jobDescriptionInputField = new JTextField(20);
    private final JTextField jobLocationInputField = new JTextField(20);
    private final JButton postJob = new JButton("Post Job");
    private final JButton exit  = new JButton("Exit");
    private final PostJobViewModel postJobViewModel;

    public PostJobView(PostJobViewModel postJobViewModel) {
        this.postJobViewModel = postJobViewModel;

//        this.jobTitleInputField.setMinimumSize(new Dimension(Integer.MAX_VALUE,50));
//        this.jobDescriptionInputField.setMinimumSize(new Dimension(Integer.MAX_VALUE,50));
//        this.jobLocationInputField.setMinimumSize(new Dimension(Integer.MAX_VALUE,50));

        //text field for typing down job title
        final JPanel jobTitleInfoPanel = new JPanel();
        jobTitleInfoPanel.add(new JLabel("Job Title"));
        jobTitleInfoPanel.add(jobTitleInputField);

        //text field for typing down job description
        final JPanel jobDescriptionInfoPanel = new JPanel();
        jobDescriptionInfoPanel.add(new JLabel("Job Description"));
        jobDescriptionInfoPanel.add(jobDescriptionInputField);

        //text field for typing down job location
        final JPanel jobLocationInfoPanel = new JPanel();
        jobLocationInfoPanel.add(new JLabel("Job Location"));
        jobLocationInfoPanel.add(jobLocationInputField);

        //adding an action listener to postJob button. when clicked postJobController starts off the use-case execution
        //and UI switches to CompanyLoggedInView
        postJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(postJob)) {
                    final PostJobState currentState = postJobViewModel.getState();
                    postJobController.execute(currentState.getIdentifier(), currentState.getJobTitle(), currentState.getJobDescription()
                            , currentState.getJobLocation());
                    postJobController.switchToCompanyLoggedInView();
                    jobTitleInputField.setText("");
                    jobDescriptionInputField.setText("");
                    jobLocationInputField.setText("");
                }
            }
        });

        //adding an action listener to exit button
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(exit)) {
                    postJobController.switchToCompanyLoggedInView();
                }
                jobTitleInputField.setText("");
                jobDescriptionInputField.setText("");
                jobLocationInputField.setText("");
            }
        });

        //adding a document listener to the document of jobTitleInputField
        jobTitleInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final PostJobState currentState = postJobViewModel.getState();
                currentState.setJobTitle(jobTitleInputField.getText());
                postJobViewModel.setState(currentState);
            }
            //since document listener is an interface, an instantiation of it must contain the following methods
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

        //adding a document listener to the document of jobDescriptionInputField
        jobDescriptionInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final PostJobState currentState = postJobViewModel.getState();
                currentState.setJobDescription(jobDescriptionInputField.getText());
                postJobViewModel.setState(currentState);
            }
            //since document listener is an interface, an instantiation of it must contain the following methods
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

        jobLocationInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final PostJobState currentState = postJobViewModel.getState();
                currentState.setJobLocation(jobLocationInputField.getText());
                postJobViewModel.setState(currentState);
            }
            //since document listener is an interface, an instantiation of it must contain the following methods
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

        this.add(jobTitleInfoPanel);
        this.add(jobDescriptionInfoPanel);
        this.add(jobLocationInfoPanel);
        this.add(postJob);
        this.add(exit);
    }

    public void setPostJobController(PostJobController postJobController) {
        this.postJobController = postJobController;
    }

    public String getViewName() {
        return this.viewName;
    }
}
