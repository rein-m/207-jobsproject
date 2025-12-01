package view.company_loggedin;

import javax.swing.*;
import java.awt.*;

public class JobPanelRenderer extends JPanel implements ListCellRenderer<JobData> {
    private JLabel jobTitle = new JLabel("Job Title:");
    private JLabel jobLocation = new JLabel("Job Location:");
    private JLabel jobDescription = new JLabel("Job Description:");
    private JLabel titleLabel = new JLabel();
    private JLabel locationLabel = new JLabel();
    private JLabel descriptionLabel = new JLabel();
    private JButton editButton = new JButton("Edit");

    public JobPanelRenderer() {
        Font font = new Font("Arial", Font.BOLD, 14);
        jobTitle.setFont(font);
        jobDescription.setFont(font);
        jobLocation.setFont(font);
        //Horizontal
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setMaximumSize(new Dimension(80, Integer.MAX_VALUE));
//        left.setPreferredSize(new Dimension(80, Integer.MAX_VALUE));
//        left.setMinimumSize(new Dimension(80, Integer.MAX_VALUE));
        left.add(jobTitle);
        left.add(titleLabel);
        left.add(jobLocation);
        left.add(locationLabel);
        left.add(jobDescription);
        left.add(descriptionLabel);
        this.add(left);

        JPanel right = new JPanel();
        right.add(editButton);
//        right.setMinimumSize(new Dimension(60, 60));
        this.add(right);
        //Vertical
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(jobTitle);
//        this.add(titleLabel);
//        this.add(jobLocation);
//        this.add(locationLabel);
//        this.add(jobDescription);
//        this.add(descriptionLabel);
//        this.add(editButton);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    @Override
    public Component getListCellRendererComponent(JList <? extends JobData> list, JobData value, int index
            , boolean isSelected, boolean cellHasFocus) {

        this.titleLabel.setText(value.jobTitle);
        this.locationLabel.setText(value.jobLocation);
        this.descriptionLabel.setText(value.jobDescription);

//        if (isSelected) {
//            this.setBackground(list.getSelectionBackground());
//            this.setForeground(list.getSelectionForeground());
//        }
//        else {
//            this.setBackground(list.getBackground());
//            this.setForeground(list.getForeground());
//        }
        return this;
    }
}
