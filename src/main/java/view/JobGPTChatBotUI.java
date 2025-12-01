package view;

import interface_adapter.loggedin.JobGPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class JobGPTChatBotUI extends JFrame implements ActionListener {

    // --- Core ChatBot Logic Members ---
    private final JobGPT chatService; // <-- Import and use the Service
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public JobGPTChatBotUI() {
        // 1. Initialize the Service Layer
        try {
            this.chatService = new JobGPT();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to initialize JobGPT Service. Check API Key/Dependencies.",
                    "Initialization Error", JOptionPane.ERROR_MESSAGE);
            // Print stack trace for debugging purposes
            e.printStackTrace();
            throw new RuntimeException("Service initialization failed.", e);
        }

        // --- UI Setup (same as before) ---
        setTitle("JobGPT AI ChatBot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout(5, 5));

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        messageField = new JTextField();
        messageField.addActionListener(this);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // --- 4. Initial Greeting Call ---
        chatArea.append("🤖 JobGPT: Connecting to AI... Please wait for the initial greeting.\n");
        // Start a worker to get the initial greeting
        new ChatWorker(null).execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == messageField) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String userMessage = messageField.getText().trim();
        if (userMessage.isEmpty()) return;

        chatArea.append("🙋 You: " + userMessage + "\n");
        messageField.setText("");

        // Start the background thread, passing the user's message
        new ChatWorker(userMessage).execute();
    }

    // =================================================================
    // === SwingWorker for Non-Blocking API Calls ======================
    // =================================================================

    private class ChatWorker extends SwingWorker<String, Void> {
        private final String userMessage;

        /** * @param userMessage The message to send to the AI. Null for the initial greeting.
         */
        public ChatWorker(String userMessage) {
            this.userMessage = userMessage;
            messageField.setEnabled(false);
            sendButton.setEnabled(false);
        }

        @Override
        protected String doInBackground() {
            try {
                // Determine which Service method to call
                if (userMessage == null) {
                    return chatService.getInitialGreeting();
                } else {
                    // Call the service method with the user's input
                    return chatService.generateResponse(userMessage);
                }
            } catch (Exception e) {
                return "Error: Could not get a response from the AI. " + e.getMessage();
            }
        }

        @Override
        protected void done() {
            try {
                String botResponse = get();
                chatArea.append("🤖 JobGPT: " + botResponse + "\n");
            } catch (InterruptedException | ExecutionException e) {
                chatArea.append("🤖 JobGPT Error: An unexpected issue occurred during processing.\n");
            } finally {
                messageField.setEnabled(true);
                sendButton.setEnabled(true);
                messageField.requestFocusInWindow();
                chatArea.setCaretPosition(chatArea.getDocument().getLength());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JobGPTChatBotUI().setVisible(true);
        });
    }
}
