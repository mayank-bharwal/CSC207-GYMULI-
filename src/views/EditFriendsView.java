package views;

import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EditFriendsView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "EditFriendsView";
    private final ViewModelManager viewModelManager;
    private final AddFriendsViewModel addFriendsViewModel;
    private final RemoveFriendsViewModel removeFriendsViewModel;
    private final RemoveFriendsController removeFriendsController;
    private final AddFriendsController addFriendsController;
    private final RefreshUserController refreshUserController;
    private final UserDataAccessObject userDAO;
    private final JLabel currentUserLabel;
    private final JLabel profilePictureLabel;
    private final JPanel friendsListPanel;
    private final JButton addFriendButton;
    private Timer timer;

    public EditFriendsView(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel, RemoveFriendsViewModel removeFriendsViewModel, RemoveFriendsController removeFriendsController, AddFriendsController addFriendsController, RefreshUserController refreshUserController, UserDataAccessObject userDAO) {
        this.viewModelManager = viewModelManager;
        this.addFriendsViewModel = addFriendsViewModel;
        this.removeFriendsViewModel = removeFriendsViewModel;
        this.removeFriendsController = removeFriendsController;
        this.addFriendsController = addFriendsController;
        this.refreshUserController = refreshUserController;
        this.userDAO = userDAO;

        this.viewModelManager.addPropertyChangeListener(this);
        this.addFriendsViewModel.addPropertyChangeListener(this);
        this.removeFriendsViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(null);

        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        profilePictureLabel = new JLabel();
        currentUserLabel = new JLabel();
        userInfoPanel.add(profilePictureLabel);
        userInfoPanel.add(currentUserLabel);

        ImageIcon addFriendIcon = new ImageIcon("images/add_friend.png");
        Image originalImage = addFriendIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        addFriendButton = new JButton(resizedIcon);
        addFriendButton.setBorderPainted(false);
        addFriendButton.setContentAreaFilled(false);
        addFriendButton.setFocusPainted(false);
        addFriendButton.setOpaque(false);
        addFriendButton.addActionListener(e -> showAddFriendDialog());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            viewModelManager.clearViewedUser();
            viewModelManager.setActiveView(MainView.viewName);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(addFriendButton);

        headerPanel.add(userInfoPanel, BorderLayout.WEST);
        headerPanel.add(buttonsPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        friendsListPanel = new JPanel();
        friendsListPanel.setLayout(new BoxLayout(friendsListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(friendsListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        updateCurrentUser();
    }

    @Override
public void propertyChange(PropertyChangeEvent evt) {
    String propertyName = evt.getPropertyName();

    switch (propertyName) {
        case "activeView":
            if (EditFriendsView.viewName.equals(evt.getNewValue())) {
                startTimer();
            } else {
                stopTimer();
            }
        case "currentUser":
        case "viewedUser":
            updateCurrentUser();
            break;

        case "friendsEdited":
            updateFriendsList();
            JOptionPane.showMessageDialog(this, "Friend List Edited", "Friend Success", JOptionPane.INFORMATION_MESSAGE);
            break;

        case "generalError":
            String error = evt.getNewValue().toString();
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
            }
            break;

        default:
            break;
    }
}

    private void updateCurrentUser() {
        User currentUser = viewModelManager.getCurrentUser();
        User viewedUser = viewModelManager.getViewedUser();
        User userToDisplay = viewedUser != null ? viewedUser : currentUser;

        currentUserLabel.setText(userToDisplay != null ? userToDisplay.getUsername() + "'s friends" : "Not logged in");
        loadUserProfile(userToDisplay);
        updateFriendsList();

        boolean isViewingOwnFriends = viewedUser == null || viewedUser.equals(currentUser);
        addFriendButton.setVisible(isViewingOwnFriends);
    }

    private void loadUserProfile(User user) {
        String imagePath = "images/profilepicdefault.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        profilePictureLabel.setIcon(imageIcon);
    }

    private void showAddFriendDialog() {
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            String newFriend = JOptionPane.showInputDialog(this, "Enter username:");
            if (newFriend != null && !newFriend.trim().isEmpty()) {
                addFriendsController.add(currentUser.getUsername(), newFriend.trim());
            } else {
                JOptionPane.showMessageDialog(this, "Friend's name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You need to be logged in to add friends.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showFriendOptionsPopup(MouseEvent e, String currentUser, String friend) {
        JPopupMenu friendOptions = new JPopupMenu();
        JMenuItem deleteFriendItem = new JMenuItem("Remove Friend");
        deleteFriendItem.addActionListener(event -> removeFriendsController.removeFriends(currentUser, friend));
        friendOptions.add(deleteFriendItem);
        friendOptions.show(e.getComponent(), e.getX(), e.getY());
    }

    private void updateFriendsList() {
        friendsListPanel.removeAll();
        User oldUser = viewModelManager.getCurrentUser();
        User currentUser;
        if (oldUser == null) {
            currentUser = null;
        }else {
            currentUser = userDAO.getUser(oldUser.getUsername());
        }
        User viewedUser = viewModelManager.getViewedUser();
        User userToDisplay = viewedUser != null ? viewedUser : currentUser;

        if (userToDisplay != null) {
            List<String> friendsList = userToDisplay.getFriends();
            for (String friend : friendsList) {
                JPanel friendPanel = new JPanel(new BorderLayout(5, 5));

                JLabel profilePictureLabel = new JLabel(new ImageIcon("images/profilepicdefault.png"));
                profilePictureLabel.setPreferredSize(new Dimension(50, 50));
                friendPanel.add(profilePictureLabel, BorderLayout.WEST);

                JPanel rightPanel = new JPanel(new BorderLayout());
                JLabel friendLabel = new JLabel(friend);
                friendLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                rightPanel.add(friendLabel, BorderLayout.WEST);


                JButton viewProfileButton = new JButton("View Profile");
                viewProfileButton.setPreferredSize(new Dimension(150, 10));
                viewProfileButton.addActionListener(e -> {
                    refreshUserController.refreshUser(friend);
                    viewModelManager.setActiveView(ProfileView.viewName);
                });
                rightPanel.add(viewProfileButton, BorderLayout.EAST);

                friendPanel.add(rightPanel, BorderLayout.CENTER);

                friendPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            showFriendOptionsPopup(e, currentUser.getUsername(), friend);
                        }
                    }
                });
                friendPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

                // Set the maximum size to prevent stretching
                friendPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                friendsListPanel.add(friendPanel);
            }

            friendsListPanel.revalidate();
            friendsListPanel.repaint();
        }
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> updateFriendsList());
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



