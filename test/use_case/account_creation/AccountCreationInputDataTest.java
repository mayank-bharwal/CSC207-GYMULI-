package use_case.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AccountCreationInputData.
 */
class AccountCreationInputDataTest {
    private AccountCreationInputData inputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");
        inputData = new AccountCreationInputData(
                "testUser",
                "testPassword",
                "testPassword",
                "Computer Science",
                interests,
                "This is a test bio.",
                LocalDateTime.now(),
                21
        );
    }

    /**
     * Tests the getUsername method.
     */
    @Test
    void getUsername() {
        assertEquals("testUser", inputData.getUsername());
    }

    /**
     * Tests the getPassword method.
     */
    @Test
    void getPassword() {
        assertEquals("testPassword", inputData.getPassword());
    }

    /**
     * Tests the getRepeatPassword method.
     */
    @Test
    void getRepeatPassword() {
        assertEquals("testPassword", inputData.getRepeatPassword());
    }

    /**
     * Tests the getProgramOfStudy method.
     */
    @Test
    void getProgramOfStudy() {
        assertEquals("Computer Science", inputData.getProgramOfStudy());
    }

    /**
     * Tests the getInterests method.
     */
    @Test
    void getInterests() {
        List<String> expectedInterests = new ArrayList<>();
        expectedInterests.add("Reading");
        expectedInterests.add("Music");
        assertEquals(expectedInterests, inputData.getInterests());
    }

    /**
     * Tests the getBio method.
     */
    @Test
    void getBio() {
        assertEquals("This is a test bio.", inputData.getBio());
    }

    /**
     * Tests the getTime method.
     */
    @Test
    void getTime() {
        assertNotNull(inputData.getTime());
    }

    /**
     * Tests the getAge method.
     */
    @Test
    void getAge() {
        assertEquals(21, inputData.getAge());
    }
}