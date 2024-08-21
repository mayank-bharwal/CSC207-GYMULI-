package interface_adapter.Login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LoginViewModel} class.
 */
class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private LoginState loginState;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of {@link LoginViewModel}.
     */
    @BeforeEach
    void setUp() {
        loginViewModel = new LoginViewModel();
        loginState = new LoginState();
    }

    /**
     * Tests the {@link LoginViewModel#getState()} method.
     * Ensures it returns the correct login state.
     */
    @Test
    void getState() {
        loginViewModel.setState(loginState);
        assertEquals(loginState, loginViewModel.getState());
    }

    /**
     * Tests the {@link LoginViewModel#setState(LoginState)} method.
     * Ensures it correctly sets the login state.
     */
    @Test
    void setState() {
        loginViewModel.setState(loginState);
        assertEquals(loginState, loginViewModel.getState());
    }

    /**
     * Tests the {@link LoginViewModel#firePropertyChanged()} method.
     * Ensures that the method correctly fires a property change event.
     */
    @Test
    void firePropertyChanged() {
        loginViewModel.setState(loginState);
        loginViewModel.addPropertyChangeListener(evt -> assertEquals("state", evt.getPropertyName()));
        loginViewModel.firePropertyChanged();
    }

    /**
     * Tests the overloaded {@link LoginViewModel#firePropertyChanged(String, Object, Object)} method.
     * Ensures that the method correctly fires a property change event with a specific property name.
     */
    @Test
    void testFirePropertyChanged() {
        loginViewModel.addPropertyChangeListener(evt -> {
            assertEquals("username", evt.getPropertyName());
            assertEquals("oldUser", evt.getOldValue());
            assertEquals("newUser", evt.getNewValue());
        });

        loginViewModel.firePropertyChanged("username", "oldUser", "newUser");
    }

//    /**
//     * Tests the {@link LoginViewModel#addPropertyChangeListener(PropertyChangeListener)} method.
//     * Ensures that a property change listener is correctly added.
//     */
//    @Test
//    void addPropertyChangeListener() {
//        PropertyChangeListener listener = evt -> {
//        };
//        loginViewModel.addPropertyChangeListener(listener);
//        assertTrue(loginViewModel.support.getPropertyChangeListeners().length > 0);
//    }
//
//    /**
//     * Tests the {@link LoginViewModel#removePropertyChangeListener(PropertyChangeListener)} method.
//     * Ensures that a property change listener is correctly removed.
//     */
//    @Test
//    void removePropertyChangeListener() {
//        PropertyChangeListener listener = evt -> {
//        };
//        loginViewModel.addPropertyChangeListener(listener);
//        loginViewModel.removePropertyChangeListener(listener);
//        assertEquals(0, loginViewModel.support.getPropertyChangeListeners().length);
//    }
}