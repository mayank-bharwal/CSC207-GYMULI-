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

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
