package use_case.remove_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RemoveFriendsOutputData.
 */
class RemoveFriendsOutputDataTest {

    private RemoveFriendsOutputData outputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputData = new RemoveFriendsOutputData("currentUser", "friendRemoved", false);
    }

    /**
     * Tests the getCurrentUser method.
     */
    @Test
    void getCurrentUser() {
        assertEquals("currentUser", outputData.getCurrentUser());
    }

    /**
     * Tests the getFriendRemoved method.
     */
    @Test
    void getFriendRemoved() {
        assertEquals("friendRemoved", outputData.getFriendRemoved());
    }

    /**
     * Tests the isFailView method.
     */
    @Test
    void isFailView() {
        assertFalse(outputData.isFailView());
    }
}