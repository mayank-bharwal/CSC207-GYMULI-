package interface_adapter.refresh_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state of the user refresh operation.
 * This class is responsible for holding the current state and notifying listeners of any property changes.
 */

public class RefreshUserViewModel extends ViewModel {
    private RefreshUserState state = new RefreshUserState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Returns the current state of the user refresh operation.
     *
     * @return The current RefreshUserState.
     */

    public RefreshUserState getState() {
        return state;
    }

    /**
     * Sets the current state of the user refresh operation.
     *
     * @param state The new RefreshUserState to set.
     */

    public void setState(RefreshUserState state) {
        this.state = state;
    }

    /**
     * Fires a property change event to notify listeners of a change in property value.
     *
     * @param propertyName The name of the property that changed.
     * @param oldValue     The old value of the property.
     * @param newValue     The new value of the property.
     */

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     *
     * @param listener The PropertyChangeListener to be removed.
     */

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
