package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;

public class ChatView extends JPanel {
    public static final String viewName = "ChatView";

    private final JTextArea chatArea;
    private final JTextField messageField;
    private final JButton sendButton;
    private final JButton backButton;
    private final ViewModelManager viewModelManager;

    public ChatView(ViewModelManager viewModelManager) {
        this.viewModelManager = viewModelManager;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);
    }
}

