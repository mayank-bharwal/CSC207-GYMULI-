package interface_adapter.Login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LoginState} class.
 */
class LoginStateTest {

    private LoginState loginState;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of {@link LoginState}.
     */
    @BeforeEach
    void setUp() {
        loginState = new LoginState();
    }

    /**
     * Tests the {@link LoginState#getPassword()} method.
     * Ensures it returns the correct password.
     */
    @Test
    void getPassword() {
        loginState.setPassword("myPassword");
        assertEquals("myPassword", loginState.getPassword());
    }

    /**
     * Tests the {@link LoginState#getUsernameError()} method.
     * Ensures it returns the correct username error message.
     */
    @Test
    void getUsernameError() {
        loginState.setUsernameError("Username cannot be empty");
        assertEquals("Username cannot be empty", loginState.getUsernameError());
    }

    /**
     * Tests the {@link LoginState#getUsername()} method.
     * Ensures it returns the correct username.
     */
    @Test
    void getUsername() {
        loginState.setUsername("myUsername");
        assertEquals("myUsername", loginState.getUsername());
    }

    /**
     * Tests the {@link LoginState#getPasswordError()} method.
     * Ensures it returns the correct password error message.
     */
    @Test
    void getPasswordError() {
        loginState.setPasswordError("Password cannot be empty");
        assertEquals("Password cannot be empty", loginState.getPasswordError());
    }

    /**
     * Tests the {@link LoginState#setPassword(String)} method.
     * Ensures it correctly sets the password.
     */
    @Test
    void setPassword() {
        loginState.setPassword("newPassword");
        assertEquals("newPassword", loginState.getPassword());
    }

    /**
     * Tests the {@link LoginState#setPasswordError(String)} method.
     * Ensures it correctly sets the password error message.
     */
    @Test
    void setPasswordError() {
        loginState.setPasswordError("Invalid password");
        assertEquals("Invalid password", loginState.getPasswordError());
    }

    /**
     * Tests the {@link LoginState#setUsernameError(String)} method.
     * Ensures it correctly sets the username error message.
     */
    @Test
    void setUsernameError() {
        loginState.setUsernameError("Invalid username");
        assertEquals("Invalid username", loginState.getUsernameError());
    }

    /**
     * Tests the {@link LoginState#setUsername(String)} method.
     * Ensures it correctly sets the username.
     */
    @Test
    void setUsername() {
        loginState.setUsername("newUsername");
        assertEquals("newUsername", loginState.getUsername());
    }
}