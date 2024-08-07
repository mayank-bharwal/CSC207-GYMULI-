package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatController;
import interface_adapter.delete_chat.DeleteChatController;
import data_access.UserDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * A view that represents the main user interface after a user has logged in.
 * It displays the current user, a list of available chats, and provides options
 * to edit the profile or create a new chat.
 */
public class MainView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "MainView";
    private boolean isDarkMode = false;
    private final DeleteChatController deleteChatController;
    private final ViewModelManager viewModelManager;
    private final UserDataAccessObject userDataAccessObject;
    private final RetrieveChatController retrieveChatController;
    private final JLabel currentUserLabel;
    private final JPanel chatListPanel;
    private Timer timer;
    /**
     * Constructs a MainView with the specified ViewModelManager and RetrieveChatController.
     *
     * @param viewModelManager the manager that handles view models and manages state
     * @param retrieveChatController the controller responsible for retrieving chat data
     * @param userDataAccessObject the data access object for user data
     */
    public MainView(ViewModelManager viewModelManager, RetrieveChatController retrieveChatController,
                    UserDataAccessObject userDataAccessObject, DeleteChatController deleteChatController) {
        this.viewModelManager = viewModelManager;
        this.viewModelManager.addPropertyChangeListener(this);
        this.retrieveChatController = retrieveChatController;
        this.userDataAccessObject = userDataAccessObject;
        this.deleteChatController = deleteChatController;

        JButton toggleDarkModeButton = new JButton("Toggle Dark Mode");
        toggleDarkModeButton.addActionListener(e -> toggleDarkMode());

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        currentUserLabel = new JLabel();
        userInfoPanel.add(new JLabel("Logged in as: "));
        userInfoPanel.add(currentUserLabel);
        userInfoPanel.add(Box.createHorizontalGlue());


        String imagePath = "images/profilepicdefault.png";
        ImageIcon profileIcon = new ImageIcon(imagePath);

        JButton profileIconButton = new JButton(profileIcon);
        profileIconButton.setPreferredSize(new Dimension(50, 50));
        profileIconButton.addActionListener(e ->{
            viewModelManager.setViewedUser(viewModelManager.getCurrentUser());
            viewModelManager.setActiveView(ProfileView.viewName);
        });

        JPanel profileButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        profileButtonPanel.add(profileIconButton);

        userInfoPanel.add(profileButtonPanel);

        headerPanel.add(userInfoPanel, BorderLayout.EAST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            viewModelManager.setActiveView(LoginView.viewName);
            viewModelManager.setCurrentUser(null);
        });

        JButton findFriendsButton = new JButton("Find Friends");
        findFriendsButton.addActionListener(e -> viewModelManager.setActiveView(RecommendationView.viewName));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(logoutButton);
        buttonPanel.add(findFriendsButton);
        buttonPanel.add(toggleDarkModeButton);

        headerPanel.add(buttonPanel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        chatListPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(chatListPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton createChatButton = new JButton("Create Chat");
        createChatButton.addActionListener(e -> viewModelManager.setActiveView(CreateChatView.viewName));
        add(createChatButton, BorderLayout.SOUTH);

        JButton friendsViewButton = new JButton("Friends View");
        friendsViewButton.addActionListener(e -> viewModelManager.setActiveView(EditFriendsView.viewName));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(createChatButton, BorderLayout.EAST);
        bottomPanel.add(friendsViewButton, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCurrentUser();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("activeView")) {
            if (MainView.viewName.equals(evt.getNewValue())) {
                startTimer();

            } else {
                stopTimer();
            }
        }
        if ("currentUser".equals(evt.getPropertyName())) {
            updateCurrentUser();
        } else if ("chatsUpdated".equals(evt.getPropertyName())) {
            updateChats();
        } else if ("profileUpdated".equals(evt.getPropertyName())) {
            updateCurrentUser();
        } else if  ("ChatDeleted".equals(evt.getPropertyName())) {
            updateChats();
            JOptionPane.showMessageDialog(this, "Chat successfully deleted!", "Successful Deletion", JOptionPane.INFORMATION_MESSAGE);}






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
        System.out.println("update chat called");
        User oldUser = viewModelManager.getCurrentUser();
        User currentUser;
        if (oldUser == null) {
            currentUser = null;
        }else {

            currentUser = userDataAccessObject.getUser(oldUser.getUsername());
        }
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
                chatButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)){
                            showChatOptionsPopup(e,chatName);
                        }
                    }
                });
                chatListPanel.add(chatButton, gbc);
                gbc.gridy++;
            }
        }
        chatListPanel.revalidate();
        chatListPanel.repaint();
    }

    private void showChatOptionsPopup(MouseEvent e, String chatname) {
        JPopupMenu chatOptions = new JPopupMenu();
        JMenuItem deleteChatItem = new JMenuItem("Delete Chat");
        deleteChatItem.addActionListener(event -> deleteChatController.deleteChat(chatname));
        chatOptions.add(deleteChatItem);
        chatOptions.show(e.getComponent(), e.getX(), e.getY());
    }

    private void toggleDarkMode() {
        try {
            if (isDarkMode) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                isDarkMode = false;
            } else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                isDarkMode = true;
            }
            // Update the look and feel for all components
            SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(this));
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    private void startTimer() {
        if (timer == null) {
            timer = new java.util.Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> updateChats());
                }
            }, 0, 1000); // Refresh every second
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


}

