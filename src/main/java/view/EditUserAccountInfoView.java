package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EditUserAccountInfoView extends JFrame {

    public EditUserAccountInfoView() {
        super("Contact information");

        // ---------- Main container ----------
        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(24, 24, 24, 24));
        content.setLayout(new BorderLayout(0, 20));
//        setContentPane(content);

        // ---------- Top title + subtitle ----------
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Contact information");
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

        // First name
        formPanel.add(createLabeledTextField("First name", true, "Pavle"), gbc);

        // Last name
        gbc.gridy++;
        formPanel.add(createLabeledTextField("Last name", true, "Adzovic"), gbc);

        // Phone
        gbc.gridy++;
        formPanel.add(createPhoneField(), gbc);

        content.add(formPanel, BorderLayout.CENTER);

        // ---------- Frame settings ----------
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
    }

    /**
     * Creates a label + text field stack:
     *  Label
     *  [ text field ]
     */
    private JPanel createLabeledTextField(String labelText, boolean required, String placeholder) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        // Label row with optional red asterisk
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
     *  "Phone"
     *  [ 🇨🇦 +1 ▼ ][ phone number ]
     */
    private JPanel createPhoneField() {
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
        outer.setOpaque(false);

        JLabel label = new JLabel("Phone");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));
        outer.add(label);
        outer.add(Box.createVerticalStrut(4));

        JPanel phoneRow = new JPanel();
        phoneRow.setLayout(new BoxLayout(phoneRow, BoxLayout.X_AXIS));
        phoneRow.setOpaque(false);

        // Left prefix panel (flag + code)
        JPanel prefixPanel = new JPanel();
        prefixPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xC4C4C4)),
                new EmptyBorder(6, 10, 6, 10)
        ));
        prefixPanel.setBackground(new Color(0xF8F8F8));
        prefixPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 2));

        JLabel flagLabel = new JLabel("🇨🇦"); // simple flag emoji
        JLabel codeLabel = new JLabel("+1");
        JLabel dropLabel = new JLabel("▼");

        prefixPanel.add(flagLabel);
        prefixPanel.add(codeLabel);
        prefixPanel.add(dropLabel);

        // Right phone text field
        JTextField phoneField = new JTextField("647-569-7770");
        styleTextField(phoneField);

        // Adjust borders so they look like a merged control
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

    public static void main(String[] args) {
        // Optional: use system look and feel for a more modern look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new EditUserAccountInfoView().setVisible(true);
        });
    }
}
