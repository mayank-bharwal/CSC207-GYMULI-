package views;

import interface_adapter.ViewModelManager;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ChatView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "ChatView";

    private final ViewModelManager viewModelManager;
    private final SendMessageController sendMessageController;
    private final SendMessageViewModel sendMessageViewModel;
    private final RetrieveChatViewModel retrieveChatViewModel;

    private final JTextArea chatArea;
    private final JTextField messageField;
    private final JButton sendButton;
    private final JButton backButton;
    private Timer timer;

    public ChatView(ViewModelManager viewModelManager, SendMessageController sendMessageController, SendMessageViewModel sendMessageViewModel, RetrieveChatViewModel retrieveChatViewModel) {
        this.viewModelManager = viewModelManager;
        this.sendMessageController = sendMessageController;
        this.sendMessageViewModel = sendMessageViewModel;
        this.retrieveChatViewModel = retrieveChatViewModel;
        this.sendMessageViewModel.addPropertyChangeListener(this);
        this.retrieveChatViewModel.addPropertyChangeListener(this);
        this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());
        messagePanel.add(sendButton, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        loadChatMessages();
    }

    private void sendMessage() {
        String message = messageField.getText();
        String sender = viewModelManager.getCurrentUser().getUsername();
        String receiver = retrieveChatViewModel.getState().getUsers().stream()
                .filter(user -> !user.equals(sender))
                .findFirst()
                .orElse(null);

        if (receiver != null && !message.trim().isEmpty()) {
            System.out.println("Sending message: " + message + " from: " + sender + " to: " + receiver);
            sendMessageController.execute(retrieveChatViewModel.getState().getChatName(), message, LocalDateTime.now(), sender, receiver);
            messageField.setText("");
        }
    }

    private void loadChatMessages() {
        System.out.println(retrieveChatViewModel.getState().getAllMessages());
        chatArea.setText("");
        retrieveChatViewModel.getState().getAllMessages().forEach(message -> {
            System.out.println("Loading message: " + message.getMessage() + " from: " + message.getSender());
            chatArea.append(message.getSender() + ": " + message.getMessage() + "\n");
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            loadChatMessages();
        } else if ("activeView".equals(evt.getPropertyName())) {
            if (ChatView.viewName.equals(evt.getNewValue())) {
                startTimer();
            } else {
                stopTimer();
            }
        }
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Timer triggered");
                    retrieveChatViewModel.triggerUpdate();
                }
            }, 0, 1000);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}


