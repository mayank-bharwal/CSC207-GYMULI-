package interface_adapter.delete_chat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class for managing the state of a delete chat operation.
 * It extends {@link ViewModel} and provides methods for interacting with
 * the state of the delete chat operation, including adding and removing
 * property change listeners.
 */
public class DeleteChatViewModel extends ViewModel {
    private DeleteChatState state = new DeleteChatState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves the current state of the delete chat operation.
     *
     * @return The current {@link DeleteChatState} object.
     */
    public DeleteChatState getState() {return state;}

    /**
     * Sets the state of the delete chat operation.
     *
     * @param state The new {@link DeleteChatState} to be set.
     */
    public void setState(DeleteChatState state) {this.state=state;}

    /**
     * Fires a property change event to notify listeners of changes
     * to the state.
     * <p>
     * This method triggers a property change event with the property name "state".
     * </p>
     */
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    /**
     * Adds a property change listener to be notified of changes to the
     * ViewModel's properties.
     *
     * @param listener The {@link PropertyChangeListener} to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener so that it will no longer be notified
     * of changes to the ViewModel's properties.
     *
     * @param listener The {@link PropertyChangeListener} to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
