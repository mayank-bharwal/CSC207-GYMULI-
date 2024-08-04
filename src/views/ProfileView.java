package views;

import entity.User;
import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * A view that displays the user's profile information.
 */
public class ProfileView extends JPanel {
    public static final String viewName = "ProfileView";

    private final ViewModelManager viewModelManager;
    private final JLabel usernameLabel;
    private final JLabel bioLabel;
    private final JLabel programOfStudyLabel;
    private final JLabel ageLabel;
    private final JLabel interestsLabel;
    private final JLabel friendsLabel;
    private final JLabel dateCreatedLabel;
    private final JLabel profilePictureLabel;

    /**
     * Constructs a ProfileView with the specified ViewModelManager.
     *
     * @param viewModelManager the manager that handles view models and manages state
     */
    public ProfileView(ViewModelManager viewModelManager) {
        this.viewModelManager = viewModelManager;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        profilePictureLabel = new JLabel();
        profilePictureLabel.setHorizontalAlignment(JLabel.CENTER);
        profilePictureLabel.setPreferredSize(new Dimension(150, 150));
        profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        profilePanel.add(profilePictureLabel);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameLabel = createLabel(Font.BOLD, 24);
        headerPanel.add(usernameLabel);

        bioLabel = createLabel(Font.ITALIC, 16);
        headerPanel.add(bioLabel);

        profilePanel.add(headerPanel);

        programOfStudyLabel = createLabel(Font.PLAIN, 14);
        ageLabel = createLabel(Font.PLAIN, 14);
        interestsLabel = createLabel(Font.PLAIN, 14);
        friendsLabel = createLabel(Font.PLAIN, 14);
        dateCreatedLabel = createLabel(Font.PLAIN, 14);

        profilePanel.add(createLabeledPanel("Program of Study:", programOfStudyLabel));
        profilePanel.add(createLabeledPanel("Age:", ageLabel));
        profilePanel.add(createLabeledPanel("Interests:", interestsLabel));
        profilePanel.add(createLabeledPanel("Friends:", friendsLabel));
        profilePanel.add(createLabeledPanel("Date Created:", dateCreatedLabel));

        add(profilePanel, BorderLayout.CENTER);

        updateUserInfo();
    }

    /**
     * Creates a JLabel with specified font settings.
     *
     * @param fontStyle the style of the font
     * @param fontSize  the size of the font
     * @return a new JLabel
     */
    private JLabel createLabel(int fontStyle, int fontSize) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", fontStyle, fontSize));
        return label;
    }

    /**
     * Creates a JPanel with a label and a value.
     *
     * @param labelText  the text for the label
     * @param valueLabel the JLabel to display the value
     * @return a new JPanel
     */
    private JPanel createLabeledPanel(String labelText, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return panel;
    }

    /**
     * Updates the display with the current user's information.
     */
    private void updateUserInfo() {
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            usernameLabel.setText(currentUser.getUsername());
            bioLabel.setText(currentUser.getBio());
            programOfStudyLabel.setText(currentUser.getProgramOfStudy());
            ageLabel.setText(currentUser.getAge() != null ? currentUser.getAge().toString() : "N/A");
            interestsLabel.setText(String.join(", ", currentUser.getInterests()));
            friendsLabel.setText(String.join(", ", currentUser.getFriends()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateCreatedLabel.setText(currentUser.getDateCreated().format(formatter));

            profilePictureLabel.setIcon(new ImageIcon("path/to/profile_picture.png")); // replace with actual path
        } else {
            usernameLabel.setText("N/A");
            bioLabel.setText("N/A");
            programOfStudyLabel.setText("N/A");
            ageLabel.setText("N/A");
            interestsLabel.setText("N/A");
            friendsLabel.setText("N/A");
            dateCreatedLabel.setText("N/A");

            profilePictureLabel.setIcon(new ImageIcon("path/to/default_picture.png")); // replace with actual path
        }
    }
}
