package use_case.refresh_user;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Test class for RefreshUserOutputData.
 */
class RefreshUserOutputDataTest {

    private User mockUser;
    private RefreshUserOutputData outputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        outputData = new RefreshUserOutputData(mockUser, false);
    }

    /**
     * Tests the getUser method.
     * Ensures it returns the correct user.
     */
    @Test
    void getUpdatedUser() {
        assertEquals(mockUser, outputData.getUpdatedUser());
    }

    /**
     * Tests the isFailView method.
     * Ensures it returns the correct fail view status.
     */
    @Test
    void isFailView() {
        assertFalse(outputData.isFailView());
    }
}