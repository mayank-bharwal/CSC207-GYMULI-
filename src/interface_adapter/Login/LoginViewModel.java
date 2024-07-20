package interface_adapter.Login;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    private LoginState state = new LoginState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField usernameInputField = new JTextField(20);
    public final JPasswordField passwordInputField = new JPasswordField(20);

    public LoginState getState() {
        return state;
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
