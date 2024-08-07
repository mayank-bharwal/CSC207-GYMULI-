package interface_adapter.add_friends;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The AddFriendsViewModel class manages the state and behavior of the add friends view.
 * It handles user inputs related to adding friends, updates the view model state, and provides
 * support for property change notifications.
 */
public class AddFriendsViewModel extends ViewModel {
    private AddFriendsState state = new AddFriendsState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Gets the current state of the add friends view model.
     *
     * @return the current {@link AddFriendsState}
     */
    public AddFriendsState getState() {
        return state;
    }

    /**
     * Sets the state of the add friends view model.
     *
     * @param state the new {@link AddFriendsState}
     */
    public void setState(AddFriendsState state) {
        this.state = state;
    }

    /**
     * Fires a property change event for the state.
     * This notifies listeners that the state has changed.
     */
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("state", null, this.state);
    }

    /**
     * Overloading property change method with different method signatures.
     * This notefies listener that the state has changed with appropriate message.
     */
    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from this view model.
     *
     * @param listener the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
