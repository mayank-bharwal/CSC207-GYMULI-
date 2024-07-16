package interface_adapter.account_creation;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String INTERESTS_LABEL = "Enter interests";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignUpState state = new SignUpState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField usernameInputField = new JTextField(15);
    public final JPasswordField passwordInputField = new JPasswordField(15);
    public final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    public final JTextField interest1InputField = new JTextField(10);
    public final JTextField interest2InputField = new JTextField(10);
    public final JTextField interest3InputField = new JTextField(10);

    public final JLabel titleLabel = new JLabel(TITLE_LABEL);
    public final JLabel usernameLabel = new JLabel(USERNAME_LABEL);
    public final JLabel passwordLabel = new JLabel(PASSWORD_LABEL);
    public final JLabel repeatPasswordLabel = new JLabel(REPEAT_PASSWORD_LABEL);
    public final JLabel interestsLabel = new JLabel(INTERESTS_LABEL);


    public SignUpState getState() {
        return state;
    }

    public void setState(SignUpState state) {
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
