package use_case.refresh_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RefreshUserInputData.
 */
class RefreshUserInputDataTest {

    private RefreshUserInputData refreshUserInputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        refreshUserInputData = new RefreshUserInputData("testUser");
    }

    /**
     * Tests the getUser method.
     * Ensures it returns the correct username.
     */
    @Test
    void getUser() {
        assertEquals("testUser", refreshUserInputData.getUser());
    }
}