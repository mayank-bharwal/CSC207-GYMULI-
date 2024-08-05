package use_case.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AccountCreationOutputData.
 */
class AccountCreationOutputDataTest {

    private AccountCreationOutputData outputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputData = new AccountCreationOutputData("testUser", "2023-07-07T12:34:56", false);
    }

    /**
     * Tests the getUsername method.
     * Ensures that the correct username is returned.
     */
    @Test
    void getUsername() {
        assertEquals("testUser", outputData.getUsername());
    }

    /**
     * Tests the getCreationTime method.
     * Ensures that the correct creation time is returned.
     */
    @Test
    void getCreationTime() {
        assertEquals("2023-07-07T12:34:56", outputData.getCreationTime());
    }

    /**
     * Tests the setCreationTime method.
     * Ensures that the creation time is correctly set and returned.
     */
    @Test
    void setCreationTime() {
        outputData.setCreationTime("2023-07-08T12:34:56");
        assertEquals("2023-07-08T12:34:56", outputData.getCreationTime());
    }
}