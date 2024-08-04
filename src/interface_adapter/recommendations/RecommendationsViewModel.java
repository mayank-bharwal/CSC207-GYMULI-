package interface_adapter.recommendations;

import entity.User;
import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

public class RecommendationsViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Recommendations";
    public static final String NO_OF_FRNDS_LABEL = "No. of recommendations";
    public static final String FIND_FRNDS_BUTTON_LABEL = "Find Friends";
    public static final String GO_BACK_BUTTON_LABEL = "Go Back";

    private RecommendationsState state = new RecommendationsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField noOfRecommendationsField = new JTextField(15);
    public final JLabel titleLabel = new JLabel(TITLE_LABEL);
    public final JLabel noOfRecommendationsLabel = new JLabel(NO_OF_FRNDS_LABEL);

    public RecommendationsState getState() {
        return state;
    }

    public void setState(RecommendationsState state) {
        this.state = state;
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    public Map<User, Double> getUserSimilarities() {
        return state.getUserSimilarities();
    }
}
