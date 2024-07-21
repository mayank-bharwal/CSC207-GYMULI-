package interface_adapter.send_message;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SendMessageViewModel extends ViewModel {
    private SendMessageState state = new SendMessageState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SendMessageState getState(){return state;}

    public void setState(SendMessageState state){this.state=state;}

    public void firePropertyChange(){
        propertyChangeSupport.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
