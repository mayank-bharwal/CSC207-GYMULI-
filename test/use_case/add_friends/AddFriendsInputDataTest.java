package use_case.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AddFriendsInputData.
 */
class AddFriendsInputDataTest {

    private AddFriendsInputData inputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        inputData = new AddFriendsInputData("currentUser", "friendUser");
    }

    /**
     * Tests the getCurrentUser method.
     * Ensures that the correct current user is returned.
     */
    @Test
    void getCurrentUser() {
        assertEquals("currentUser", inputData.getCurrentUser());
    }

    /**
     * Tests the getFriend method.
     * Ensures that the correct friend is returned.
     */
    @Test
    void getFriend() {
        assertEquals("friendUser", inputData.getFriend());
    }
}