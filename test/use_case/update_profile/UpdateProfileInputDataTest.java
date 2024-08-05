package use_case.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UpdateProfileInputData class.
 */
class UpdateProfileInputDataTest {

    private UpdateProfileInputData inputData;
    private final String currentPassword = "oldPassword";
    private final String currentUsername = "oldUsername";
    private final String username = "newUsername";
    private final String password = "newPassword";
    private final String bio = "This is a new bio.";
    private final String programOfStudy = "Computer Science";
    private final int age = 25;
    private final List<String> interests = Arrays.asList("Reading", "Coding");

    /**
     * Sets up the test data before each test.
     */
    @BeforeEach
    void setUp() {
        inputData = new UpdateProfileInputData(currentUsername, currentPassword, username, password, bio, programOfStudy, age, interests);
    }

    /**
     * Tests the getCurrentPassword method.
     */
    @Test
    void getCurrentPassword() {
        assertEquals(currentPassword, inputData.getCurrentPassword());
    }

    /**
     * Tests the getCurrentUsername method.
     */
    @Test
    void getCurrentUsername() {
        assertEquals(currentUsername, inputData.getCurrentUsername());
    }

    /**
     * Tests the getUsername method.
     */
    @Test
    void getUsername() {
        assertEquals(username, inputData.getUsername());
    }

    /**
     * Tests the getPassword method.
     */
    @Test
    void getPassword() {
        assertEquals(password, inputData.getPassword());
    }

    /**
     * Tests the getBio method.
     */
    @Test
    void getBio() {
        assertEquals(bio, inputData.getBio());
    }

    /**
     * Tests the getProgramOfStudy method.
     */
    @Test
    void getProgramOfStudy() {
        assertEquals(programOfStudy, inputData.getProgramOfStudy());
    }

    /**
     * Tests the getAge method.
     */
    @Test
    void getAge() {
        assertEquals(age, inputData.getAge());
    }

    /**
     * Tests the getInterests method.
     */
    @Test
    void getInterests() {
        assertEquals(interests, inputData.getInterests());
    }
}