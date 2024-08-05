package use_case.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginInputData class.
 */
class LoginInputDataTest {

    private LoginInputData loginInputData;

    /**
     * Sets up the test environment before each test.
     * Initializes the LoginInputData object with test data.
     */
    @BeforeEach
    void setUp() {
        loginInputData = new LoginInputData("testUser", "testPassword");
    }

    /**
     * Tests the getUsername method.
     * Verifies that the username is correctly retrieved.
     */
    @Test
    void getUsername() {
        assertEquals("testUser", loginInputData.getUsername());
    }

    /**
     * Tests the getPassword method.
     * Verifies that the password is correctly retrieved.
     */
    @Test
    void getPassword() {
        assertEquals("testPassword", loginInputData.getPassword());
    }
}