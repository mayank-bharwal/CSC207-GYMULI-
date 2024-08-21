package interface_adapter.refresh_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RefreshUserState class.
 */
class RefreshUserStateTest {

    private RefreshUserState state;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of RefreshUserState.
     */
    @BeforeEach
    void setUp() {
        state = new RefreshUserState();
    }

    /**
     * Tests the getUsernameError method.
     * Verifies that the method returns the correct username error message.
     */
    @Test
    void getUsernameError() {
        state.setUsernameError("Error message");
        assertEquals("Error message", state.getUsernameError());
    }

    /**
     * Tests the setUsernameError method.
     * Verifies that the username error message is set correctly.
     */
    @Test
    void setUsernameError() {
        state.setUsernameError("New error message");
        assertEquals("New error message", state.getUsernameError());
    }

    /**
     * Tests the getUsername method.
     * Verifies that the method returns the correct username.
     */
    @Test
    void getUsername() {
        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername());
    }

    /**
     * Tests the setUsername method.
     * Verifies that the username is set correctly.
     */
    @Test
    void setUsername() {
        state.setUsername("newUser");
        assertEquals("newUser", state.getUsername());
    }
}