package views;

import interface_adapter.ViewModel;
import interface_adapter.ViewModelManager;
import interface_adapter.recommendations.RecommendationsController;
import interface_adapter.recommendations.RecommendationsState;
import interface_adapter.recommendations.RecommendationsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecommendationView extends JPanel implements PropertyChangeListener {

    public static final String viewname = "RecommendationView";

    private final RecommendationsController recommendationsController;
    private final ViewModelManager viewModelManager;
    private final RecommendationsViewModel recommendationsViewModel;

    private final JButton find_friends_button;
    private final JButton go_back_button;
    private final JTextField number_of_friends_field;

    public RecommendationView(RecommendationsController recommendationsController, ViewModelManager viewModelManager, RecommendationsViewModel recommendationsViewModel, JButton findFriendsButton, JButton goBackButton, JTextField numberOfFriendsField) {
        this.recommendationsController = recommendationsController;
        this.recommendationsViewModel = recommendationsViewModel;
        this.viewModelManager = viewModelManager;

        //this.find_friends_button = findFriendsButton;
        //this.go_back_button = goBackButton;
        //this.number_of_friends_field = numberOfFriendsField;
        this.recommendationsViewModel.addPropertyChangeListener(this);
        //this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Recommendations", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel numberOfRecommLabel = new JLabel("Number of Friends");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(numberOfRecommLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        number_of_friends_field = new JTextField(20);
        formPanel.add(number_of_friends_field, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        find_friends_button = new JButton("Find Friends");
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
        RecommendationsState state = recommendationsViewModel.getRecommendationsState();
        if(state.isSuccess()){
            System.out.println("Recommendations successfully loaded");
            JOptionPane.showMessageDialog(this, "Recommendations successfully loaded");
            number_of_friends_field.setText("");
            viewModelManager.setActiveView(MainView.viewName);
        }else if (state.getError() != null){
            JOptionPane.showMessageDialog(this, state.getRecommendationError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
