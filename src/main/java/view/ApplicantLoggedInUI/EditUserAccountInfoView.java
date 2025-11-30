package view.ApplicantLoggedInUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EditUserAccountInfoView extends JFrame {
    public EditUserAccountInfoView() {
        super("Basic information");

        // ---------- Main container ----------
        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(24, 24, 24, 24));
        content.setLayout(new BorderLayout(0, 20));
        setContentPane(content);

        // ---------- Top title + subtitle ----------
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Basic information");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 26f));
        headerPanel.add(titleLabel);

        JPanel subtitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        subtitlePanel.setOpaque(false);

        JLabel subtitleText = new JLabel("Please fill out the form below.");
        JLabel subtitleReq = new JLabel("*required");
        subtitleReq.setForeground(new Color(0xB00020));

        subtitlePanel.add(subtitleText);
        subtitlePanel.add(subtitleReq);

        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitlePanel);

        content.add(headerPanel, BorderLayout.NORTH);

        // ---------- Form area ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Email
        formPanel.add(createLabeledTextField("Email", true, "user@example.com"), gbc);

        // Phone
        gbc.gridy++;
        formPanel.add(createPhoneField(), gbc);

        // Address
        gbc.gridy++;
        formPanel.add(createLabeledTextField("Address", true, "123 Main St, Toronto, ON"), gbc);

        content.add(formPanel, BorderLayout.CENTER);

        // ---------- Frame settings ----------
        // Do NOT exit the whole app when this closes:
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
    }

    // --- helper methods ---

    /**
     * Creates a label + text field stack:
     *  Label
     *  [ text field ]
     */
    private JPanel createLabeledTextField(String labelText, boolean required, String placeholder) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JPanel labelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelRow.setOpaque(false);

        JLabel label = new JLabel(labelText + " ");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));
        labelRow.add(label);

        if (required) {
            JLabel star = new JLabel("*");
            star.setForeground(new Color(0xB00020));
            star.setFont(star.getFont().deriveFont(Font.BOLD, 16f));
            labelRow.add(star);
        }

        panel.add(labelRow);
        panel.add(Box.createVerticalStrut(4));

        JTextField field = new JTextField(placeholder);
        styleTextField(field);
        panel.add(field);

        return panel;
    }

    /**
     * Phone field row:
     *  "Phone *"
     *  [ 🇨🇦 +1 ][ phone number ]
     */
    private JPanel createPhoneField() {
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
        outer.setOpaque(false);

        // Label row with required star, left-aligned
        JPanel labelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelRow.setOpaque(false);

        JLabel label = new JLabel("Phone ");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));
        labelRow.add(label);

        JLabel star = new JLabel("*");
        star.setForeground(new Color(0xB00020));
        star.setFont(star.getFont().deriveFont(Font.BOLD, 16f));
        labelRow.add(star);

        outer.add(labelRow);
        outer.add(Box.createVerticalStrut(4));

        JPanel phoneRow = new JPanel();
        phoneRow.setLayout(new BoxLayout(phoneRow, BoxLayout.X_AXIS));
        phoneRow.setOpaque(false);

        JPanel prefixPanel = new JPanel();
        prefixPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));
        prefixPanel.setBackground(new Color(0xF8F8F8));
        prefixPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 2));

        JLabel flagLabel = new JLabel("🇨🇦");
        JLabel codeLabel = new JLabel("+1");
        //JLabel dropLabel = new JLabel("▼");

        prefixPanel.add(flagLabel);
        prefixPanel.add(codeLabel);
        //prefixPanel.add(dropLabel);

        JTextField phoneField = new JTextField("647-569-7770");
        styleTextField(phoneField);

        phoneField.setBorder(new CompoundBorder(
                new MatteBorder(1, 0, 1, 1, new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));

        phoneRow.add(prefixPanel);
        phoneRow.add(phoneField);

        outer.add(phoneRow);
        return outer;
    }

    /**
     * Applies rounded-ish look to text fields.
     */
    private void styleTextField(JTextField field) {
        field.setFont(field.getFont().deriveFont(16f));
        field.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }
}
