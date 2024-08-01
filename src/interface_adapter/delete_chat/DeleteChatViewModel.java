package interface_adapter.delete_chat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteChatViewModel extends ViewModel {
    private DeleteChatState state = new DeleteChatState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DeleteChatState getState() {return state;}

    public void setState(DeleteChatState state) {this.state=state;}

    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
