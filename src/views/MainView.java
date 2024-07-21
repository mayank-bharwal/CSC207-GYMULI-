package views;

import entity.User;
import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MainView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "MainView";

    private final ViewModelManager viewModelManager;
    private final JLabel currentUserLabel;
    private final JPanel chatsPanel;

    public MainView(ViewModelManager viewModelManager) {
        this.viewModelManager = viewModelManager;
        this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        currentUserLabel = new JLabel();
        headerPanel.add(new JLabel("Logged in as: "));
        headerPanel.add(currentUserLabel);

        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        chatsPanel = new JPanel();
        chatsPanel.setLayout(new BoxLayout(chatsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatsPanel);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        mainPanel.add(scrollPane, gbc);

        JButton createChatButton = new JButton("Create Chat");
        createChatButton.addActionListener(e -> viewModelManager.setActiveView(CreateChatView.viewName));
        gbc.gridy = 1;
        mainPanel.add(createChatButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        updateCurrentUser();
        updateChats();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentUser".equals(evt.getPropertyName())) {
            updateCurrentUser();
            updateChats();
        } else if ("chatsUpdated".equals(evt.getPropertyName())) {
            updateChats();
        }
    }

    private void updateCurrentUser() {
        User currentUser = viewModelManager.getCurrentUser();
        currentUserLabel.setText(currentUser != null ? currentUser.getUsername() : "Not logged in");
    }

    private void updateChats() {
        User currentUser = viewModelManager.getCurrentUser();
        chatsPanel.removeAll();

        if (currentUser != null) {
            List<String> userChats = currentUser.getChats();
            for (String chatName : userChats) {
                JButton chatButton = new JButton(chatName);
                chatButton.addActionListener(e -> viewModelManager.setActiveView(ChatView.viewName));
                chatsPanel.add(chatButton);
            }
        }

        chatsPanel.revalidate();
        chatsPanel.repaint();
    }
}


