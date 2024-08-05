package views;

import entity.User;
import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "ProfileView";

    private final ViewModelManager viewModelManager;
    private final JLabel usernameLabel;
    private final JLabel bioLabel;
    private final JLabel programOfStudyLabel;
    private final JLabel ageLabel;
    private final JLabel profilePictureLabel;
    private final JButton updateProfileButton;
    private final JButton friendsButton;

    public ProfileView(ViewModelManager viewModelManager) {
        this.viewModelManager = viewModelManager;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.NORTH);

        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        profilePictureLabel = new JLabel(new ImageIcon("images/profilepicdefault.png"));
        profilePictureLabel.setPreferredSize(new Dimension(50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        profilePanel.add(profilePictureLabel, gbc);

        JPanel usernamePanel = new JPanel(new GridBagLayout());
        usernamePanel.setOpaque(false);

        JLabel atSymbolLabel = new JLabel("@");
        atSymbolLabel.setFont(new Font("Arial", Font.BOLD, 24));

        usernameLabel = createLabel(Font.BOLD, 24);

        GridBagConstraints gbcUser = new GridBagConstraints();
        gbcUser.insets = new Insets(0, 0, 0, 5);
        gbcUser.gridx = 0;
        gbcUser.gridy = 0;
        usernamePanel.add(atSymbolLabel, gbcUser);

        gbcUser.gridx = 1;
        usernamePanel.add(usernameLabel, gbcUser);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        profilePanel.add(usernamePanel, gbc);

        JSeparator separator2 = new JSeparator();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        profilePanel.add(separator2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        friendsButton = new JButton("0 Friends");
        profilePanel.add(friendsButton, gbc);

        gbc.gridx = 1;
        updateProfileButton = new JButton("Edit Profile");
        profilePanel.add(updateProfileButton, gbc);

        JSeparator separator1 = new JSeparator();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        profilePanel.add(separator1, gbc);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);

        JPanel ageProgramPanel = new JPanel(new GridBagLayout());
        ageProgramPanel.setOpaque(false);

        ageLabel = createLabel(Font.PLAIN, 14);
        programOfStudyLabel = createLabel(Font.PLAIN, 14);

        JLabel separator = new JLabel("|");
        separator.setFont(new Font("Arial", Font.PLAIN, 14));

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(0, 0, 0, 5);
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        gbcInfo.anchor = GridBagConstraints.WEST;
        ageProgramPanel.add(ageLabel, gbcInfo);

        gbcInfo.gridx = 1;
        ageProgramPanel.add(separator, gbcInfo);

        gbcInfo.gridx = 2;
        ageProgramPanel.add(programOfStudyLabel, gbcInfo);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        profilePanel.add(ageProgramPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        bioLabel = createLabel(Font.PLAIN, 14);
        profilePanel.add(bioLabel, gbc);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        outerPanel.add(profilePanel, BorderLayout.CENTER);

        add(outerPanel, BorderLayout.CENTER);

        viewModelManager.addPropertyChangeListener(this);

        updateUserInfo();

        updateProfileButton.addActionListener(e -> viewModelManager.setActiveView(UpdateProfileView.viewName));
        friendsButton.addActionListener(e -> viewModelManager.setActiveView(EditFriendsView.viewName));
    }

    private JLabel createLabel(int fontStyle, int fontSize) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", fontStyle, fontSize));
        return label;
    }

    private void updateUserInfo() {
        User currentUser = viewModelManager.getCurrentUser();
        User viewedUser = viewModelManager.getViewedUser();
        User userToDisplay = viewedUser != null ? viewedUser : currentUser;
        System.out.println("User to Display: " + userToDisplay);
        if (userToDisplay != null) {
            System.out.println("Username: " + userToDisplay.getUsername());
            usernameLabel.setText(userToDisplay.getUsername());
            bioLabel.setText(userToDisplay.getBio());
            programOfStudyLabel.setText(userToDisplay.getProgramOfStudy());
            ageLabel.setText(userToDisplay.getAge() != null ? userToDisplay.getAge().toString() : "N/A");

            profilePictureLabel.setIcon(new ImageIcon("images/profilepicdefault.png"));
            friendsButton.setText(userToDisplay.getFriends().size() + " Friends");

            boolean isCurrentUser = userToDisplay.equals(currentUser);
            updateProfileButton.setEnabled(isCurrentUser);
            updateProfileButton.setVisible(isCurrentUser);
        } else {
            usernameLabel.setText("N/A");
            bioLabel.setText("N/A");
            programOfStudyLabel.setText("N/A");
            ageLabel.setText("N/A");

            profilePictureLabel.setIcon(new ImageIcon("images/profilepicdefault.png"));
            friendsButton.setText("0 Friends");
            updateProfileButton.setEnabled(false);
            updateProfileButton.setVisible(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeView".equals(evt.getPropertyName()) && viewName.equals(evt.getNewValue())) {
            updateUserInfo();
        }
        if ("viewedUser".equals(evt.getPropertyName())) {
            updateUserInfo();
        }
    }
}



