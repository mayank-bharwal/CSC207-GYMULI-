package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.recommendations.RecommendationsController;
import interface_adapter.recommendations.RecommendationsViewModel;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserState;
import interface_adapter.search_user.SearchUserViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class RecommendationView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "RecommendationView";

    private final RecommendationsController recommendationsController;
    private final SearchUserController searchUserController;
    private final ViewModelManager viewModelManager;
    private final RecommendationsViewModel recommendationsViewModel;
    private final SearchUserViewModel searchUserViewModel;

    private final JButton findFriendsButton;
    private final JButton goBackButton;
    private final JButton searchButton;
    private final JTextField searchField;
    private final JPanel contentPanel;
    private final JLabel searchUserLabel;

    public RecommendationView(RecommendationsController recommendationsController, SearchUserController searchUserController, ViewModelManager viewModelManager, RecommendationsViewModel recommendationsViewModel, SearchUserViewModel searchUserViewModel) {
        this.recommendationsController = recommendationsController;
        this.searchUserController = searchUserController;
        this.recommendationsViewModel = recommendationsViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.viewModelManager = viewModelManager;
        this.recommendationsViewModel.addPropertyChangeListener(this);
        this.searchUserViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = recommendationsViewModel.titleLabel;
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel numberOfRecommLabel = recommendationsViewModel.noOfRecommendationsLabel;
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(numberOfRecommLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField number_of_friends_field = recommendationsViewModel.noOfRecommendationsField;
        formPanel.add(number_of_friends_field, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        findFriendsButton = new JButton(recommendationsViewModel.FIND_FRNDS_BUTTON_LABEL);
        buttonPanel.add(findFriendsButton);

        goBackButton = new JButton(recommendationsViewModel.GO_BACK_BUTTON_LABEL);
        buttonPanel.add(goBackButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        searchUserLabel = new JLabel("Search User:");
        searchUserLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        searchPanel.add(searchUserLabel, gbc);

        searchField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        searchPanel.add(searchField, gbc);

        searchButton = new JButton("Search");
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPanel.add(searchButton, gbc);

        add(searchPanel, BorderLayout.SOUTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        findFriendsButton.addActionListener(e -> {
            searchField.setText("");
            contentPanel.removeAll();
            recommendationsController.execute(viewModelManager.getCurrentUser(), Integer.valueOf(number_of_friends_field.getText()));
        });

        goBackButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));

        searchButton.addActionListener(e -> {
            contentPanel.removeAll();
            System.out.println("Search button clicked");
            searchUserController.search_user(searchField.getText());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("generalError".equals(evt.getPropertyName())) {
            String errorMessage = recommendationsViewModel.getState().getRecommendationError();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if ("userSimilarities".equals(evt.getPropertyName())) {
            updateRecommendations(recommendationsViewModel.getUserSimilarities());
        } else if ("state".equals(evt.getPropertyName()) && evt.getSource() == searchUserViewModel) {
            handleSearchResult();
        }
    }

    private void updateRecommendations(Map<User, Double> userSimilarities) {
        contentPanel.removeAll();

        userSimilarities.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> {
                    User user = entry.getKey();
                    double score = entry.getValue();

                    JPanel userPanel = new JPanel();
                    userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                    JLabel profilePictureLabel = new JLabel(new ImageIcon("images/profilepicdefault.png"));
                    profilePictureLabel.setPreferredSize(new Dimension(50, 50));
                    userPanel.add(profilePictureLabel);

                    JLabel userLabel = new JLabel(user.getUsername() + " (Similarity: " + String.format("%.2f%%", score * 100) + ")");
                    userPanel.add(userLabel);

                    JButton addButton = new JButton("Add");
                    addButton.addActionListener(e -> {
                        System.out.println("Add button clicked for user: " + user.getUsername());
                    });
                    userPanel.add(addButton);

                    contentPanel.add(userPanel);
                });

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void handleSearchResult() {
        contentPanel.removeAll();

        SearchUserState searchUserState = searchUserViewModel.getState();
        if (searchUserState.getUsernameError() != null) {
            JOptionPane.showMessageDialog(null, searchUserState.getUsernameError(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel profilePictureLabel = new JLabel(new ImageIcon("images/profilepicdefault.png"));
            profilePictureLabel.setPreferredSize(new Dimension(50, 50));
            userPanel.add(profilePictureLabel);

            JLabel userLabel = new JLabel(searchUserState.getUsername());
            userPanel.add(userLabel);

            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                System.out.println("Add button clicked for user: " + searchUserState.getUsername());
            });
            userPanel.add(addButton);

            contentPanel.add(userPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}





