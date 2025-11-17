package view;

import interface_adapter.post_job.PostJobController;
import interface_adapter.post_job.PostJobState;
import interface_adapter.post_job.PostJobViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostJobView extends JPanel {
    private PostJobController postJobController = null;
    private final JTextField jobTitleInputField = new JTextField();
    private final JTextField jobDescriptionInputField = new JTextField();
    private final JButton postJob = new JButton("Post Job");
    private final PostJobViewModel postJobViewModel;

    public PostJobView(PostJobViewModel postJobViewModel) {
        this.postJobViewModel = postJobViewModel;

        //text field for typing down job title
        final JPanel jobTitleInfoPanel = new JPanel();
        jobTitleInfoPanel.add(new JLabel("Job Title"));
        jobTitleInfoPanel.add(jobTitleInputField);

        //text field for typing down job description
        final JPanel jobDescriptionInfoPanel = new JPanel();
        jobDescriptionInfoPanel.add(new JLabel("Job Description"));
        jobDescriptionInfoPanel.add(jobDescriptionInputField);

        //adding an action listener to postJob button. when clicked postJobController starts off the use-case execution
        //and UI switches to CompanyLoggedInView
        postJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(postJob)) {
                    final PostJobState currentState = postJobViewModel.getState();
                    postJobController.execute(currentState.getJobTitle(), currentState.getJobDescription());
                    postJobController.switchToCompanyLoggedInView();
                }
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

        this.add(jobTitleInfoPanel);
        this.add(jobDescriptionInfoPanel);
    }

    public void setPostJobController(PostJobController postJobController) {
        this.postJobController = postJobController;
    }
}
