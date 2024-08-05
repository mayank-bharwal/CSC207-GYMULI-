package use_case.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AddFriendsOutputData.
 */
class AddFriendsOutputDataTest {

    private AddFriendsOutputData outputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputData = new AddFriendsOutputData("currentUser", "friend", false);
    }

    /**
     * Tests the getCurrentUser method.
     * Ensures that it returns the correct current user.
     */
    @Test
    void getCurrentUser() {
        assertEquals("currentUser", outputData.getCurrentUser());
    }

    /**
     * Tests the getFriend method.
     * Ensures that it returns the correct friend user.
     */
    @Test
    void getFriend() {
        assertEquals("friend", outputData.getFriend());
    }
}