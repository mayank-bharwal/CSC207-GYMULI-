package use_case.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SearchUserOutputData class.
 */
class SearchUserOutputDataTest {

    private SearchUserOutputData outputData;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        outputData = new SearchUserOutputData("testUser");
    }

    /**
     * Tests the getUsername method.
     * Verifies that it returns the correct username.
     */
    @Test
    void getUsername() {
        assertEquals("testUser", outputData.getUsername());
    }
}