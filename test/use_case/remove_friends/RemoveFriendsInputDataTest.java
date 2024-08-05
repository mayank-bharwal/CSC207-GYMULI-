package use_case.remove_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RemoveFriendsInputData.
 */
class RemoveFriendsInputDataTest {

    private RemoveFriendsInputData inputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        inputData = new RemoveFriendsInputData("User1", "User2");
    }

    /**
     * Tests the getUser1 method.
     * Ensures it returns the correct user1.
     */
    @Test
    void getUser1() {
        assertEquals("User1", inputData.getUser1());
    }

    /**
     * Tests the getUser2 method.
     * Ensures it returns the correct user2.
     */
    @Test
    void getUser2() {
        assertEquals("User2", inputData.getUser2());
    }
}