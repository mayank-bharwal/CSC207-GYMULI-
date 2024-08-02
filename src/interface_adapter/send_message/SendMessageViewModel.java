package interface_adapter.send_message;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The SendMessageModel class manages the state and behavior of the sending message view.
 * It handles user inputs, updates the view model state, and provides support for property change notifications.
 */
public class SendMessageViewModel extends ViewModel {
    private SendMessageState state = new SendMessageState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Gets the current state of the send message view model.
     *
     * @return the current SendMessageState
     */
    public SendMessageState getState(){return state;}

    /**
     * Sets the state of the signup view model.
     *
     * @param state the new SendMessageState
     */
    public void setState(SendMessageState state){this.state=state;}

    /**
     * Fires a property change event for the state.
     */
    public void firePropertyChange(){
        propertyChangeSupport.firePropertyChange("state", null, this.state);}

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from this view model.
     *
     * @param listener the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
