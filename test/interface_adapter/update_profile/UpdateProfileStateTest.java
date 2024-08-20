package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UpdateProfileState class.
 */
class UpdateProfileStateTest {

    private UpdateProfileState state;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        state = new UpdateProfileState();
    }

    /**
     * Tests the getCurrentUsername method.
     * Verifies that it returns the correct current username.
     */
    @Test
    void getCurrentUsername() {
        state.setCurrentUsername("currentUsername");
        assertEquals("currentUsername", state.getCurrentUsername());
    }

    /**
     * Tests the setCurrentUsername method.
     * Verifies that the current username is correctly set.
     */
    @Test
    void setCurrentUsername() {
        state.setCurrentUsername("newUsername");
        assertEquals("newUsername", state.getCurrentUsername());
    }

    /**
     * Tests the getCurrentPassword method.
     * Verifies that it returns the correct current password.
     */
    @Test
    void getCurrentPassword() {
        state.setCurrentPassword("currentPassword");
        assertEquals("currentPassword", state.getCurrentPassword());
    }

    /**
     * Tests the setCurrentPassword method.
     * Verifies that the current password is correctly set.
     */
    @Test
    void setCurrentPassword() {
        state.setCurrentPassword("newPassword");
        assertEquals("newPassword", state.getCurrentPassword());
    }

    /**
     * Tests the getUsername method.
     * Verifies that it returns the correct username.
     */
    @Test
    void getUsername() {
        state.setUsername("newUsername");
        assertEquals("newUsername", state.getUsername());
    }

    /**
     * Tests the setUsername method.
     * Verifies that the username is correctly set.
     */
    @Test
    void setUsername() {
        state.setUsername("anotherUsername");
        assertEquals("anotherUsername", state.getUsername());
    }

    /**
     * Tests the getPassword method.
     * Verifies that it returns the correct password.
     */
    @Test
    void getPassword() {
        state.setPassword("newPassword");
        assertEquals("newPassword", state.getPassword());
    }

    /**
     * Tests the setPassword method.
     * Verifies that the password is correctly set.
     */
    @Test
    void setPassword() {
        state.setPassword("anotherPassword");
        assertEquals("anotherPassword", state.getPassword());
    }

    /**
     * Tests the getBio method.
     * Verifies that it returns the correct bio.
     */
    @Test
    void getBio() {
        state.setBio("This is a bio");
        assertEquals("This is a bio", state.getBio());
    }

    /**
     * Tests the setBio method.
     * Verifies that the bio is correctly set.
     */
    @Test
    void setBio() {
        state.setBio("New bio");
        assertEquals("New bio", state.getBio());
    }

    /**
     * Tests the getAge method.
     * Verifies that it returns the correct age.
     */
    @Test
    void getAge() {
        state.setAge(30);
        assertEquals(30, state.getAge());
    }

    /**
     * Tests the setAge method.
     * Verifies that the age is correctly set.
     */
    @Test
    void setAge() {
        state.setAge(25);
        assertEquals(25, state.getAge());
    }

    /**
     * Tests the getInterest1 method.
     * Verifies that it returns the correct first interest.
     */
    @Test
    void getInterest1() {
        state.setInterest1("Music");
        assertEquals("Music", state.getInterest1());
    }

    /**
     * Tests the setInterest1 method.
     * Verifies that the first interest is correctly set.
     */
    @Test
    void setInterest1() {
        state.setInterest1("Coding");
        assertEquals("Coding", state.getInterest1());
    }

    /**
     * Tests the getInterest2 method.
     * Verifies that it returns the correct second interest.
     */
    @Test
    void getInterest2() {
        state.setInterest2("Reading");
        assertEquals("Reading", state.getInterest2());
    }

    /**
     * Tests the setInterest2 method.
     * Verifies that the second interest is correctly set.
     */
    @Test
    void setInterest2() {
        state.setInterest2("Hiking");
        assertEquals("Hiking", state.getInterest2());
    }

    /**
     * Tests the getInterest3 method.
     * Verifies that it returns the correct third interest.
     */
    @Test
    void getInterest3() {
        state.setInterest3("Traveling");
        assertEquals("Traveling", state.getInterest3());
    }

    /**
     * Tests the setInterest3 method.
     * Verifies that the third interest is correctly set.
     */
    @Test
    void setInterest3() {
        state.setInterest3("Gaming");
        assertEquals("Gaming", state.getInterest3());
    }

    /**
     * Tests the getProgramOfStudy method.
     * Verifies that it returns the correct program of study.
     */
    @Test
    void getProgramOfStudy() {
        state.setProgramOfStudy("Computer Science");
        assertEquals("Computer Science", state.getProgramOfStudy());
    }

    /**
     * Tests the setProgramOfStudy method.
     * Verifies that the program of study is correctly set.
     */
    @Test
    void setProgramOfStudy() {
        state.setProgramOfStudy("Mathematics");
        assertEquals("Mathematics", state.getProgramOfStudy());
    }

    /**
     * Tests the getError method.
     * Verifies that it returns the correct error message.
     */
    @Test
    void getError() {
        state.setError("This is an error");
        assertEquals("This is an error", state.getError());
    }

    /**
     * Tests the setError method.
     * Verifies that the error message is correctly set.
     */
    @Test
    void setError() {
        state.setError("New error message");
        assertEquals("New error message", state.getError());
    }
}