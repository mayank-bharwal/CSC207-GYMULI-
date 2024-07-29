package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A view that represents the main user interface after a user has logged in.
 * It displays the current user, a list of available chats, and provides options
 * to edit the profile or create a new chat.
 */


public class MainView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "MainView";

    private final ViewModelManager viewModelManager;
    private final RetrieveChatController retrieveChatController;
    private final JLabel currentUserLabel;
    private final JPanel chatListPanel;


    /**
     * Constructs a MainView with the specified ViewModelManager and RetrieveChatController.
     *
     * @param viewModelManager the manager that handles view models and manages state
     * @param retrieveChatController the controller responsible for retrieving chat data
     */
    public MainView(ViewModelManager viewModelManager, RetrieveChatController retrieveChatController) {
        this.viewModelManager = viewModelManager;
        this.viewModelManager.addPropertyChangeListener(this);
        this.retrieveChatController = retrieveChatController;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        currentUserLabel = new JLabel();
        headerPanel.add(new JLabel("Logged in as: "));
        headerPanel.add(currentUserLabel);
        headerPanel.add(Box.createHorizontalGlue());

        JButton editProfileButton =  new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> viewModelManager.setActiveView(UpdateProfileView.viewName));
        headerPanel.add(editProfileButton);

        add(headerPanel, BorderLayout.NORTH);

        chatListPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(chatListPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton createChatButton = new JButton("Create Chat");
        createChatButton.addActionListener(e -> viewModelManager.setActiveView(CreateChatView.viewName));
        add(createChatButton, BorderLayout.SOUTH);

        updateCurrentUser();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentUser".equals(evt.getPropertyName())) {
            updateCurrentUser();
        } else if ("chatsUpdated".equals(evt.getPropertyName())) {
            updateChats();
        }
    }

    /**
     * Updates the display of the current user.
     * Also triggers an update of the chat list.
     */

    private void updateCurrentUser() {
        User currentUser = viewModelManager.getCurrentUser();
        currentUserLabel.setText(currentUser != null ? currentUser.getUsername() : "Not logged in");
        updateChats();
    }

    /**
     * Updates the list of chats displayed in the main view.
     * Clears the current list and repopulates it based on the current user's chats.
     */

    private void updateChats() {
        chatListPanel.removeAll();
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            List<String> chats = currentUser.getChats();
            Set<String> uniqueChats = new HashSet<>(chats);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            for (String chatName : uniqueChats) {
                JButton chatButton = new JButton(chatName);
                chatButton.addActionListener(e -> {
                    retrieveChatController.retrieveChat(chatName);
                    viewModelManager.setActiveView(ChatView.viewName);
                });
                chatListPanel.add(chatButton, gbc);
                gbc.gridy++;
            }
        }
        chatListPanel.revalidate();
        chatListPanel.repaint();
    }
}


