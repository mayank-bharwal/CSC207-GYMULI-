package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;

import javax.swing.*;
import javax.swing.border.Border;
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
    private final RemoveFriendsViewModel removeFriendsViewModel;
    private final RemoveFriendsController removeFriendsController;
    private final AddFriendsController addFriendsController;
    private final JLabel currentUserLabel;
    private final JLabel profilePictureLabel;
    private final JPanel friendsListPanel;

    /**
     * Constructs an EditFriendsView with the specified ViewModelManager and Controllers.
     *
     * @param viewModelManager       the manager that handles view models and manages state
     * @param addFriendsViewModel    the view model for adding friends
     * @param removeFriendsViewModel the view model for removing friends
     * @param removeFriendsController the controller for removing friends
     * @param addFriendsController   the controller for adding friends
     */
    public EditFriendsView(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel, RemoveFriendsViewModel removeFriendsViewModel, RemoveFriendsController removeFriendsController, AddFriendsController addFriendsController) {
        this.viewModelManager = viewModelManager;
        this.addFriendsViewModel = addFriendsViewModel;
        this.removeFriendsViewModel = removeFriendsViewModel;
        this.removeFriendsController = removeFriendsController;
        this.addFriendsController = addFriendsController;

        // Add PropertyChangeListeners
        this.viewModelManager.addPropertyChangeListener(this);
        this.addFriendsViewModel.addPropertyChangeListener(this);
        this.removeFriendsViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        Color hex = new Color(224,224,224);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(null);
        headerPanel.setBackground(hex);

        // User Info Panel
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInfoPanel.setBackground(hex);
        profilePictureLabel = new JLabel();
        currentUserLabel = new JLabel();
        userInfoPanel.add(profilePictureLabel);
        userInfoPanel.add(currentUserLabel);

        // Add Friend Button (Image of the button)
        ImageIcon addFriendIcon = new ImageIcon("images/add_friend.png");
        Image originalImage = addFriendIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);


        JButton addFriendButton = new JButton(resizedIcon);
        addFriendButton.setBorderPainted(false);
        addFriendButton.setContentAreaFilled(false);
        addFriendButton.setFocusPainted(false);
        addFriendButton.setOpaque(false);
        addFriendButton.addActionListener(e -> showAddFriendDialog());


        // Back button (with text back)
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

        friendsListPanel = new JPanel();
        friendsListPanel.setLayout(new BoxLayout(friendsListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(friendsListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        updateCurrentUser();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "currentUser":
                updateCurrentUser();
                break;
            case "friendsList":
                updateFriendsList();
                JOptionPane.showMessageDialog(this, "Friend successfully added!", "Friend Success", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "friendRemoved":
                updateFriendsList();
                JOptionPane.showMessageDialog(this, "Friend successfully removed!", "Friend Success", JOptionPane.INFORMATION_MESSAGE);
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
            } else {
                JOptionPane.showMessageDialog(this, "Friend's name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You need to be logged in to add friends.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows a popup menu with options for a specific friend.
     *
     * @param e         the mouse event
     * @param currentUser the current user's username
     * @param friend    the name of the friend
     */
    private void showFriendOptionsPopup(MouseEvent e, String currentUser, String friend) {
        JPopupMenu friendOptions = new JPopupMenu();
        JMenuItem deleteFriendItem = new JMenuItem("Remove Friend");
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
            System.out.println(friendsList);
            for (String friend : friendsList) {
                JPanel friendPanel = new JPanel();
                friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));

                JLabel friendLabel = new JLabel(friend);
                friendLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
                Border paddingBorder = BorderFactory.createEmptyBorder(10 ,10,10, screenSize.width);
                Border compoundBorder = BorderFactory.createCompoundBorder(blackBorder, paddingBorder);

                friendLabel.setBorder(compoundBorder);

                friendPanel.add(friendLabel);
                friendPanel.add(Box.createHorizontalGlue());

                friendPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            showFriendOptionsPopup(e, currentUser.getUsername(), friend);
                        }
                    }
                });

                friendsListPanel.add(friendPanel);
            }

            friendsListPanel.revalidate(); // Refresh the panel to reflect updates
            friendsListPanel.repaint(); // Repaint the panel to apply changes
        }
    }
}