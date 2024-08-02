package interface_adapter.recommendations;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecommendationsViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Recommendations";
    private RecommendationsState recommendationsState = new RecommendationsState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public RecommendationsState getRecommendationsState() {return recommendationsState;}

    public final JLabel titleLabel = new JLabel(TITLE_LABEL);

    public void setRecommendationsState(RecommendationsState recommendationsState) {
        this.recommendationsState = recommendationsState;
    }

    public void firePropertyChanged(){
        propertyChangeSupport.firePropertyChange("recommendationState", null, this.recommendationsState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
