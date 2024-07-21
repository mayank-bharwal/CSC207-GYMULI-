package views;

import entity.Message;
import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatState;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "ChatView";

    private final JTextArea chatArea;
    private final JTextField messageField;
    private final JButton sendButton;
    private final JButton backButton;
    private final ViewModelManager viewModelManager;
    private final RetrieveChatViewModel retrieveChatViewModel;

    public ChatView(ViewModelManager viewModelManager, RetrieveChatViewModel retrieveChatViewModel) {
        this.viewModelManager = viewModelManager;
        this.retrieveChatViewModel = retrieveChatViewModel;
        this.retrieveChatViewModel.addPropertyChangeListener(this);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            RetrieveChatState state = retrieveChatViewModel.getState();
            updateChatView(state);
        }
    }

    private void updateChatView(RetrieveChatState state) {
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            chatArea.setText("");
            chatArea.append("Chat: " + state.getChatName() + "\n");
            for (Message message : state.getAllMessages()) {
                chatArea.append(message.getSender() + ": " + message.getMessage() + "\n");
            }
        }
    }
}


