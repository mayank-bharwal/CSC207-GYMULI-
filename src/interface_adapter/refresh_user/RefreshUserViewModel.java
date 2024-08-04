package interface_adapter.refresh_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RefreshUserViewModel extends ViewModel {
    private RefreshUserState state = new RefreshUserState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public RefreshUserState getState() {
        return state;
    }

    public void setState(RefreshUserState state) {
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
