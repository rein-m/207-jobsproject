package view.ApplicantLoggedInUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAccountInfo extends JFrame {

    private static final String EDUCATION_PREFIX = "Education: ";
    private static final String WORK_PREFIX = "Work Experience: ";
    private static final String PROJECTS_PREFIX = "Projects: ";
    private static final String SKILLS_PREFIX = "Skills: ";
    private static final String LANGUAGES_PREFIX = "Programming Languages: ";
    private static final String FRAMEWORKS_PREFIX = "Frameworks & Libraries: ";
    private static final String TOOLS_PREFIX = "Tools & Technologies: ";

    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;

    private JLabel educationLabel;
    private JLabel workLabel;
    private JLabel projectsLabel;
    private JLabel skillsLabel;
    private JLabel languagesLabel;
    private JLabel frameworksLabel;
    private JLabel toolsLabel;

    public UserAccountInfo() {
        setTitle("User Information");
        setSize(600, 800);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Account Info");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createVerticalStrut(25));

        JPanel contactLabel = new JPanel(new BorderLayout());
        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel iconLabel = new JLabel("Basic Information:");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftSide.add(iconLabel);

        JButton btn = new JButton("Edit Account Info");
        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(200, 5));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserAccountInfoView editUserInfoWindow =
                        new EditUserAccountInfoView(
                                UserAccountInfo.this,
                                emailLabel.getText(),
                                phoneLabel.getText(),
                                addressLabel.getText()
                        );
                editUserInfoWindow.setVisible(true);
            }
        });

        contactLabel.add(leftSide, BorderLayout.WEST);
        contactLabel.add(btn, BorderLayout.EAST);
        leftPanel.add(contactLabel);

        emailLabel = new JLabel("spongebob.squarepants@gmail.com");
        leftPanel.add(createInfoRow("✉", emailLabel));
        leftPanel.add(Box.createVerticalStrut(5));

        phoneLabel = new JLabel("647-123-4567");
        leftPanel.add(createInfoRow("☎", phoneLabel));
        leftPanel.add(Box.createVerticalStrut(5));

        addressLabel = new JLabel("Bikini Bottom");
        leftPanel.add(createInfoRow("📍", addressLabel));
        leftPanel.add(Box.createVerticalStrut(5));

        JPanel resume = new JPanel(new BorderLayout());
        JPanel res_leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel res_iconLabel = new JLabel("Resumes:");
        res_iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        res_leftSide.add(res_iconLabel);
        JButton res_btn = new JButton("Add New Resume");
        res_btn.setFocusable(false);
        res_btn.setPreferredSize(new Dimension(200, 5));
        res_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PDFProcessorUI frame = new PDFProcessorUI();
                frame.setVisible(true);
            }
        });
        resume.add(res_leftSide, BorderLayout.WEST);
        resume.add(res_btn, BorderLayout.EAST);
        leftPanel.add(resume);
        leftPanel.add(Box.createVerticalStrut(25));

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel all_resumes = new JPanel();
        all_resumes.setLayout(new BoxLayout(all_resumes, BoxLayout.Y_AXIS));

        JPanel single_resume1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name1 = new JLabel("SpongeBob_Resume_1_Fry_Cook");
        resume_name1.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume1.add(resume_name1);
        single_resume1.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume1, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name2 = new JLabel("SpongeBob_Resume_2_Customer_Service");
        resume_name2.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume2.add(resume_name2);
        single_resume2.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume2, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name3 = new JLabel("SpongeBob_Resume_3_Creative_Arts");
        resume_name3.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume3.add(resume_name3);
        single_resume3.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume3, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name4 = new JLabel("SpongeBob_Resume_4_Science_Assistant");
        resume_name4.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume4.add(resume_name4);
        single_resume4.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume4, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume5.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name5 = new JLabel("SpongeBob_Resume_5_Team_Player");
        resume_name5.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume5.add(resume_name5);
        single_resume5.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume5, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume6.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name6 = new JLabel("SpongeBob_Resume_6_Childcare");
        resume_name6.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume6.add(resume_name6);
        single_resume6.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume6, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JPanel single_resume7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        single_resume7.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel resume_name7 = new JLabel("SpongeBob_Resume_7_Will_Need_A_Drive");
        resume_name7.setFont(new Font("SansSerif", Font.ITALIC, 16));
        single_resume7.add(resume_name7);
        single_resume7.setPreferredSize(new Dimension(300, 50));
        all_resumes.add(single_resume7, BorderLayout.SOUTH);
        all_resumes.add(Box.createVerticalStrut(10));

        JScrollPane scrollPane = new JScrollPane(all_resumes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        main.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(main);
        leftPanel.add(Box.createVerticalStrut(25));

        JPanel qualifications = new JPanel(new BorderLayout());
        JPanel qua_leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel qua_iconLabel = new JLabel("Qualifications:");
        qua_iconLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        qua_leftSide.add(qua_iconLabel);
        JButton qua_btn = new JButton("Edit Qualifications");
        qua_btn.setFocusable(false);
        qua_btn.setPreferredSize(new Dimension(200, 5));
        qua_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserAccountQualificationsView editUserQualWindow =
                        new EditUserAccountQualificationsView(
                                UserAccountInfo.this,
                                stripPrefix(educationLabel.getText(), EDUCATION_PREFIX),
                                stripPrefix(workLabel.getText(), WORK_PREFIX),
                                stripPrefix(projectsLabel.getText(), PROJECTS_PREFIX),
                                stripPrefix(skillsLabel.getText(), SKILLS_PREFIX),
                                stripPrefix(languagesLabel.getText(), LANGUAGES_PREFIX),
                                stripPrefix(frameworksLabel.getText(), FRAMEWORKS_PREFIX),
                                stripPrefix(toolsLabel.getText(), TOOLS_PREFIX)
                        );
                editUserQualWindow.setVisible(true);
            }
        });
        qualifications.add(qua_leftSide, BorderLayout.WEST);
        qualifications.add(qua_btn, BorderLayout.EAST);
        leftPanel.add(qualifications);
        leftPanel.add(Box.createVerticalStrut(25));

        educationLabel = new JLabel(EDUCATION_PREFIX);
        leftPanel.add(createInfoRow("🎓", educationLabel));

        workLabel = new JLabel(WORK_PREFIX);
        leftPanel.add(createInfoRow("💼", workLabel));

        projectsLabel = new JLabel(PROJECTS_PREFIX);
        leftPanel.add(createInfoRow("📁", projectsLabel));

        skillsLabel = new JLabel(SKILLS_PREFIX);
        leftPanel.add(createInfoRow("🛠️", skillsLabel));

        languagesLabel = new JLabel(LANGUAGES_PREFIX);
        leftPanel.add(createInfoRow("💻", languagesLabel));

        frameworksLabel = new JLabel(FRAMEWORKS_PREFIX);
        leftPanel.add(createInfoRow("📚", frameworksLabel));

        toolsLabel = new JLabel(TOOLS_PREFIX);
        leftPanel.add(createInfoRow("⚙️", toolsLabel));

        setLocationRelativeTo(null);
    }

    private String stripPrefix(String text, String prefix) {
        if (text == null) return "";
        if (text.startsWith(prefix)) {
            return text.substring(prefix.length());
        }
        return text;
    }

    public void updateAccountInfo(String email, String phone, String address) {
        emailLabel.setText(email);
        phoneLabel.setText(phone);
        addressLabel.setText(address);
    }

    public void updateQualifications(String education,
                                     String work,
                                     String projects,
                                     String skills,
                                     String languages,
                                     String frameworks,
                                     String tools) {
        educationLabel.setText(EDUCATION_PREFIX + education);
        workLabel.setText(WORK_PREFIX + work);
        projectsLabel.setText(PROJECTS_PREFIX + projects);
        skillsLabel.setText(SKILLS_PREFIX + skills);
        languagesLabel.setText(LANGUAGES_PREFIX + languages);
        frameworksLabel.setText(FRAMEWORKS_PREFIX + frameworks);
        toolsLabel.setText(TOOLS_PREFIX + tools);
    }

    private JPanel createInfoRow(String icon, JLabel textLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftSide.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftSide.add(iconLabel);
        leftSide.add(textLabel);

        row.add(leftSide, BorderLayout.WEST);
        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserAccountInfo().setVisible(true));
    }
}
