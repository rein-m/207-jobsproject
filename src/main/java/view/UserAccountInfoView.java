package view;//package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;
import interface_adapter.ResumeShit.resumeUI.ResumeUIViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.userAccountInfo.AccountInfoController;
import interface_adapter.userAccountInfo.AccountInfoState;
import interface_adapter.ResumeShit.resumeUI.ResumeUIControler;
import interface_adapter.userAccountInfo.AccountInforViewModel;
import use_case.ResumeShit.ResumeUI.ResumeUIInteractor;

public class UserAccountInfoView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "account info";

    private final AccountInforViewModel accountinfoViewModel;
    private ResumeUIControler resumeUIControler;

    public UserAccountInfoView(AccountInforViewModel accountInforViewModel) {
        this.accountinfoViewModel = accountInforViewModel;
        this.accountinfoViewModel.addPropertyChangeListener(this);

//        setTitle("User Information");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        // Overview
        JLabel nameLabel = new JLabel("Account Info");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createVerticalStrut(25)); // space under name

        // Contact Information
        JPanel contactLabel = new JPanel(new BorderLayout());
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel iconLabel = new JLabel("Basic Information:");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftSide.add(iconLabel);
        JButton btn = new JButton(">");
        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(30, 5));
        contactLabel.add(leftSide, BorderLayout.WEST);
        contactLabel.add(btn, BorderLayout.EAST);
        leftPanel.add(contactLabel);
        leftPanel.add(Box.createVerticalStrut(25));

        // Row helper
        leftPanel.add(createInfoRow("✉", "User Email"));
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(createInfoRow("☎", "User Phone Number"));
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(createInfoRow("📍", "User Address"));
        leftPanel.add(Box.createVerticalStrut(5));

        // Resume Title
        JPanel resume = new JPanel(new BorderLayout());
        JPanel res_leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel res_iconLabel = new JLabel("Resumes:");
        res_iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        res_leftSide.add(res_iconLabel);
        JButton res_btn = new JButton("Add New Resume");
        res_btn.setFocusable(false);
        res_btn.setPreferredSize(new Dimension(125, 5));
        resume.add(res_leftSide, BorderLayout.WEST);
        resume.add(res_btn, BorderLayout.EAST);
        leftPanel.add(resume);
        leftPanel.add(Box.createVerticalStrut(25));

        // Adding the actionperformed for add new resume
        res_btn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(res_btn)) {
                            final AccountInfoState currentState = accountinfoViewModel.getState();
                            resumeUIControler.execute();
                        }
                    }
                }
        );

        // Resume File
        // Main resume panel
        JPanel main = new JPanel(new BorderLayout());
        main.setPreferredSize(new Dimension(500, 250));
        main.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel all_resumes = new JPanel();
        all_resumes.setLayout(new BoxLayout(all_resumes, BoxLayout.Y_AXIS));

        for (int i = 0; i < 20; i++) {
            JPanel single_resume = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            single_resume.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            JLabel resume_name = new JLabel("File Name" + i);
            resume_name.setFont(new Font("SansSerif", Font.ITALIC, 16));

            JLabel date_added = new JLabel("Date Added");
            date_added.setFont(new Font("SansSerif", Font.ITALIC, 16));

            single_resume.add(resume_name);
            single_resume.add(date_added);
            single_resume.setPreferredSize(new Dimension(300, 50));

            all_resumes.add(single_resume, BorderLayout.SOUTH);
            all_resumes.add(Box.createVerticalStrut(10));
        }
        JScrollPane scrollPane = new JScrollPane(all_resumes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        main.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(main);
        leftPanel.add(Box.createVerticalStrut(25));

        // Qualifications Title
        JPanel qualifications = new JPanel(new BorderLayout());
        JPanel qua_leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel qua_iconLabel = new JLabel("Qualifications:");
        qua_iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        qua_leftSide.add(qua_iconLabel);
        JButton qua_btn = new JButton(">");
        qua_btn.setFocusable(false);
        qua_btn.setPreferredSize(new Dimension(30, 5));
        qualifications.add(qua_leftSide, BorderLayout.WEST);
        qualifications.add(qua_btn, BorderLayout.EAST);
        leftPanel.add(qualifications);

//        setLocationRelativeTo(null);
    }

    private JPanel createInfoRow(String icon, String text) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        // Info
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftSide.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftSide.add(iconLabel);
        leftSide.add(textLabel);

        row.add(leftSide, BorderLayout.WEST);
//        row.add(btn, BorderLayout.EAST);

        return row;
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        final AccountInfoState state = (AccountInfoState) evt.getNewValue();
//        setFields(state);
//        usernameErrorField.setText(state.getLoginError());
    }

    public String getViewName() {
        return viewName;
    }

    public void setAccountInfoControler(ResumeUIControler loginController) {
        this.resumeUIControler = loginController;
    }

}
