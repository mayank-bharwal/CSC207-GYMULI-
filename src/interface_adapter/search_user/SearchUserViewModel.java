package interface_adapter.search_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUserViewModel extends ViewModel {

    public final String SU_TITLE = "Search User View";
    public final String SU_USERNAME = "Search Username";

    private final PropertyChangeSupport support;
    public SearchUserState state;

    public SearchUserViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new SearchUserState();
    }

    public SearchUserState getState() {
        return state;
    }

    public void setState(SearchUserState state) {
        SearchUserState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
