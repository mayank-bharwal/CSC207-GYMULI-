package interface_adapter.remove_friends;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state of the Remove Friends view.
 * <p>
 * This class is responsible for maintaining the state of the Remove Friends view and providing
 * methods to interact with property change listeners.
 * </p>
 */
public class RemoveFriendsViewModel extends ViewModel {
    private RemoveFriendsState state = new RemoveFriendsState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Gets the current state of the Remove Friends view.
     *
     * @return the current {@link RemoveFriendsState} object.
     */
    public RemoveFriendsState getState() {
        return state;
    }

    /**
     * Sets a new state for the Remove Friends view.
     *
     * @param state the new {@link RemoveFriendsState} object to set.
     */
    public void setState(RemoveFriendsState state) {
        this.state = state;
    }

    /**
     * Fires a property change event to registered listeners.
     *
     * @param propertyName the name of the property that has changed.
     * @param oldValue the old value of the property.
     * @param newValue the new value of the property.
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Adds a property change listener to be notified of property changes.
     *
     * @param listener the {@link PropertyChangeListener} to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from being notified of property changes.
     *
     * @param listener the {@link PropertyChangeListener} to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
