package interface_adapter.account_creation;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String PROGRAM_OF_STUDY_LABEL = "Program of Study";
    public static final String INTEREST1_LABEL = "Interest 1";
    public static final String INTEREST2_LABEL = "Interest 2";
    public static final String INTEREST3_LABEL = "Interest 3";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    private SignupState state = new SignupState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField usernameInputField = new JTextField(20);
    public final JPasswordField passwordInputField = new JPasswordField(20);
    public final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    public final JTextField programOfStudyInputField = new JTextField(20);
    public final JTextField interest1InputField = new JTextField(20);
    public final JTextField interest2InputField = new JTextField(20);
    public final JTextField interest3InputField = new JTextField(20);

    public final JLabel titleLabel = new JLabel(TITLE_LABEL);
    public final JLabel usernameLabel = new JLabel(USERNAME_LABEL);
    public final JLabel passwordLabel = new JLabel(PASSWORD_LABEL);
    public final JLabel repeatPasswordLabel = new JLabel(REPEAT_PASSWORD_LABEL);
    public final JLabel programOfStudyLabel = new JLabel(PROGRAM_OF_STUDY_LABEL);
    public final JLabel interest1Label = new JLabel(INTEREST1_LABEL);
    public final JLabel interest2Label = new JLabel(INTEREST2_LABEL);
    public final JLabel interest3Label = new JLabel(INTEREST3_LABEL);

    public SignupState getState() {
        return state;
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    //for testing
    private String username;
    private String error;

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        firePropertyChanged("username", oldUsername, username);
    }

    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        firePropertyChanged("error", oldError, error);
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