package use_case.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SearchUserInputData class.
 */
class SearchUserInputDataTest {

    private SearchUserInputData searchUserInputData;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        searchUserInputData = new SearchUserInputData("testUser");
    }

    /**
     * Tests the getUsername method.
     * Verifies that the username is returned correctly.
     */
    @Test
    void getUsername() {
        assertEquals("testUser", searchUserInputData.getUsername());
    }

    /**
     * Tests the setUsername method.
     * Verifies that the username is set correctly.
     */
    @Test
    void setUsername() {
        searchUserInputData.setUsername("newUser");
        assertEquals("newUser", searchUserInputData.getUsername());
    }
}