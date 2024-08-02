package views;

import interface_adapter.ViewModel;
import interface_adapter.ViewModelManager;
import interface_adapter.recommendations.RecommendationsController;
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

        this.find_friends_button = findFriendsButton;
        this.go_back_button = goBackButton;
        this.number_of_friends_field = numberOfFriendsField;
        this.recommendationsViewModel.addPropertyChangeListener(this);
        this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));


    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
