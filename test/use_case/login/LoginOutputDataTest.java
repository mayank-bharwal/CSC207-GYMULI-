package use_case.login;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginOutputData class.
 */
class LoginOutputDataTest {

    private User user;
    private LoginOutputData loginOutputData;

    /**
     * Sets up the test environment before each test.
     * Initializes the User mock object and the LoginOutputData object.
     */
    @BeforeEach
    void setUp() {
        user = Mockito.mock(User.class);
        loginOutputData = new LoginOutputData(user);
    }

    /**
     * Tests the getUser method.
     * Verifies that the user object returned by getUser is the same as the one passed to the constructor.
     */
    @Test
    void getUser() {
        assertEquals(user, loginOutputData.getUser());
    }
}