package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.edit_friends.EditFriendsViewModel;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * A view that represents the user interface for managing friends.
 * It displays the current user, a list of friends, and provides options
 * to add or delete friends.
 */
public class EditFriendsView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "EditFriendsView";
    private final ViewModelManager viewModelManager;
    private final AddFriendsViewModel addFriendsViewModel;
    private final RemoveFriendsController removeFriendsController;
    private final AddFriendsController addFriendsController;
    private final JLabel currentUserLabel;
    private final JLabel profilePictureLabel;
    private final JPanel friendsListPanel;

    /**
     * Constructs an EditFriendsView with the specified ViewModelManager and Controllers.
     *
     * @param viewModelManager the manager that handles view models and manages state
     * @param removeFriendsController the controller for removing friends
     * @param addFriendsController the controller for adding friends
     */
    public EditFriendsView(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel, RemoveFriendsController removeFriendsController, AddFriendsController addFriendsController) {
        this.viewModelManager = viewModelManager;
        this.addFriendsViewModel = addFriendsViewModel;
        this.removeFriendsController = removeFriendsController;
        this.addFriendsController = addFriendsController;
        this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new BorderLayout());

        // User Info Panel
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePictureLabel = new JLabel();
        currentUserLabel = new JLabel();
        userInfoPanel.add(profilePictureLabel);
        userInfoPanel.add(currentUserLabel);

        // Add Friend Button (Plus Sign)
        JButton addFriendButton = new JButton("+");
        addFriendButton.addActionListener(e -> showAddFriendDialog());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(addFriendButton);

        headerPanel.add(userInfoPanel, BorderLayout.WEST);
        headerPanel.add(buttonsPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        friendsListPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(friendsListPanel);
        add(scrollPane, BorderLayout.CENTER);

        updateCurrentUser();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentUser".equals(evt.getPropertyName())) {
            updateCurrentUser();
        } else if ("friendsList".equals(evt.getPropertyName())) {
            updateFriendsList();
            JOptionPane.showMessageDialog(this, "Friend Successfully added!", "Friend Success", JOptionPane.INFORMATION_MESSAGE);
        } else if ("generalError".equals(evt.getPropertyName())) {
            String error = addFriendsViewModel.getState().getError();
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Updates the display of the current user.
     * Also triggers an update of the friends list.
     */
    private void updateCurrentUser() {
        User currentUser = viewModelManager.getCurrentUser();
        currentUserLabel.setText(currentUser != null ? currentUser.getUsername() : "Not logged in");
        loadUserProfile();
        updateFriendsList();
    }

    /**
     * Loads the user's profile picture.
     */
    private void loadUserProfile() {
        String imagePath = "images/profilepicdefault.png"; // Replace with actual path
        ImageIcon imageIcon = new ImageIcon(imagePath);
        profilePictureLabel.setIcon(imageIcon);
    }

    /**
     * Shows a dialog to add a new friend.
     */
    private void showAddFriendDialog() {
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            String newFriend = JOptionPane.showInputDialog(this, "Enter friend's name:");
            if (newFriend != null && !newFriend.trim().isEmpty()) {
                addFriendsController.add(currentUser.getUsername(), newFriend.trim());
            }
        } else {
            JOptionPane.showMessageDialog(this, "You need to be logged in to add friends.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows a popup menu with options for a specific friend.
     *
     * @param e the mouse event
     * @param currentUser the current user's username
     * @param friend the name of the friend
     */
    private void showFriendOptionsPopup(MouseEvent e, String currentUser, String friend) {
        JPopupMenu friendOptions = new JPopupMenu();
        JMenuItem deleteFriendItem = new JMenuItem("Delete Friend");
        deleteFriendItem.addActionListener(event -> removeFriendsController.removeFriends(currentUser, friend));
        friendOptions.add(deleteFriendItem);
        friendOptions.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
    * Updates the list of friends displayed in the view.
    */
    private void updateFriendsList() {
        friendsListPanel.removeAll(); // Clear existing friends list
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            List<String> friendsList = currentUser.getFriends();
            for (String friend : friendsList) {
                JButton friendButton = new JButton(friend);
                friendButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            showFriendOptionsPopup(e, currentUser.getUsername(), friend);
                        }
                    }
                });
                friendsListPanel.add(friendButton); // Add friend button to panel
            }

            friendsListPanel.revalidate(); // Refresh the panel to reflect updates
            friendsListPanel.repaint(); // Repaint the panel to apply changes
        }
    }
}