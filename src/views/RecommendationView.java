package views;

import interface_adapter.Login.LoginState;
import interface_adapter.ViewModelManager;
import interface_adapter.recommendations.RecommendationsController;
import interface_adapter.recommendations.RecommendationsState;
import interface_adapter.recommendations.RecommendationsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecommendationView extends JPanel implements PropertyChangeListener {

    public static final String viewname = "RecommendationView";

    private final RecommendationsController recommendationsController;
    private final ViewModelManager viewModelManager;
    private final RecommendationsViewModel recommendationsViewModel;

    private final JButton find_friends_button;
    private final JButton go_back_button;

    public RecommendationView(RecommendationsController recommendationsController, ViewModelManager viewModelManager, RecommendationsViewModel recommendationsViewModel, JButton findFriendsButton, JButton goBackButton, JTextField numberOfFriendsField) {
        this.recommendationsController = recommendationsController;
        this.recommendationsViewModel = recommendationsViewModel;
        this.viewModelManager = viewModelManager;
        this.recommendationsViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new BorderLayout());
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
        //number_of_friends_field = new JTextField(20);
        JTextField number_of_friends_field =  recommendationsViewModel.noOfRecommendationsField;
        formPanel.add(number_of_friends_field, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        find_friends_button = new JButton(recommendationsViewModel.FIND_FRNDS_BUTTON_LABEL);
        buttonPanel.add(find_friends_button);

        go_back_button = new JButton("Go Back");
        buttonPanel.add(go_back_button);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        find_friends_button.addActionListener(e -> recommendationsController.execute(
                viewModelManager.getCurrentUser(), Integer.valueOf(numberOfFriendsField.getText())));

        go_back_button.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("generalError".equals(evt.getPropertyName())) {
            String errorMessage = recommendationsViewModel.getState().getRecommendationError();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        RecommendationsState state = recommendationsViewModel.getState();
        setFields(state);
    }
    //public void actionPerformed(ActionEvent e) {}
    private void setFields(RecommendationsState state) {
        recommendationsViewModel.noOfRecommendationsField.setText(state.getRecommendationError()); // maybe state.getSimilarUsers()
    }
}
