package interface_adapter.make_chat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateChatViewModel extends ViewModel {
    private CreateChatState state = new CreateChatState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateChatState getState() {
        return state;
    }

    public void setState(CreateChatState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
