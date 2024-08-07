package interface_adapter.remove_friends;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RemoveFriendsViewModel extends ViewModel {
    private RemoveFriendsState state = new RemoveFriendsState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public RemoveFriendsState getState() {
        return state;
    }

    public void setState(RemoveFriendsState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
