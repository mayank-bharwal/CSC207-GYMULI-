package interface_adapter.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the SignupState class.
 * This class contains unit tests to verify the behavior of the SignupState class,
 * ensuring that all getter and setter methods function as expected.
 */
class SignupStateTest {

    private SignupState signupState;

    /**
     * Sets up the test environment before each test method.
     * This method is executed before each test case and initializes the SignupState instance.
     */
    @BeforeEach
    void setUp() {
        signupState = new SignupState();
    }

    /**
     * Tests the getUsername method.
     * Ensures that the username is correctly retrieved after being set.
     */
    @Test
    void getUsername() {
        signupState.setUsername("testUser");
        assertEquals("testUser", signupState.getUsername());
    }

    /**
     * Tests the setUsername method.
     * Verifies that the username is correctly set.
     */
    @Test
    void setUsername() {
        signupState.setUsername("newUser");
        assertEquals("newUser", signupState.getUsername());
    }

    /**
     * Tests the getPassword method.
     * Ensures that the password is correctly retrieved after being set.
     */
    @Test
    void getPassword() {
        signupState.setPassword("password123");
        assertEquals("password123", signupState.getPassword());
    }

    /**
     * Tests the setPassword method.
     * Verifies that the password is correctly set.
     */
    @Test
    void setPassword() {
        signupState.setPassword("newPassword");
        assertEquals("newPassword", signupState.getPassword());
    }

    /**
     * Tests the getRepeatPassword method.
     * Ensures that the repeat password is correctly retrieved after being set.
     */
    @Test
    void getRepeatPassword() {
        signupState.setRepeatPassword("password123");
        assertEquals("password123", signupState.getRepeatPassword());
    }

    /**
     * Tests the setRepeatPassword method.
     * Verifies that the repeat password is correctly set.
     */
    @Test
    void setRepeatPassword() {
        signupState.setRepeatPassword("newPassword123");
        assertEquals("newPassword123", signupState.getRepeatPassword());
    }

    /**
     * Tests the getProgramOfStudy method.
     * Ensures that the program of study is correctly retrieved after being set.
     */
    @Test
    void getProgramOfStudy() {
        signupState.setProgramOfStudy("Computer Science");
        assertEquals("Computer Science", signupState.getProgramOfStudy());
    }

    /**
     * Tests the setProgramOfStudy method.
     * Verifies that the program of study is correctly set.
     */
    @Test
    void setProgramOfStudy() {
        signupState.setProgramOfStudy("Mathematics");
        assertEquals("Mathematics", signupState.getProgramOfStudy());
    }

    /**
     * Tests the getBio method.
     * Ensures that the bio is correctly retrieved after being set.
     */
    @Test
    void getBio() {
        signupState.setBio("This is a test bio.");
        assertEquals("This is a test bio.", signupState.getBio());
    }

    /**
     * Tests the setBio method.
     * Verifies that the bio is correctly set.
     */
    @Test
    void setBio() {
        signupState.setBio("New bio content.");
        assertEquals("New bio content.", signupState.getBio());
    }

    /**
     * Tests the getAge method.
     * Ensures that the age is correctly retrieved after being set.
     */
    @Test
    void getAge() {
        signupState.setAge(25);
        assertEquals(25, signupState.getAge());
    }

    /**
     * Tests the setAge method.
     * Verifies that the age is correctly set.
     */
    @Test
    void setAge() {
        signupState.setAge(30);
        assertEquals(30, signupState.getAge());
    }

    /**
     * Tests the getInterest1 method.
     * Ensures that the first interest is correctly retrieved after being set.
     */
    @Test
    void getInterest1() {
        signupState.setInterest1("Reading");
        assertEquals("Reading", signupState.getInterest1());
    }

    /**
     * Tests the setInterest1 method.
     * Verifies that the first interest is correctly set.
     */
    @Test
    void setInterest1() {
        signupState.setInterest1("Music");
        assertEquals("Music", signupState.getInterest1());
    }

    /**
     * Tests the getInterest2 method.
     * Ensures that the second interest is correctly retrieved after being set.
     */
    @Test
    void getInterest2() {
        signupState.setInterest2("Gym");
        assertEquals("Gym", signupState.getInterest2());
    }

    /**
     * Tests the setInterest2 method.
     * Verifies that the second interest is correctly set.
     */
    @Test
    void setInterest2() {
        signupState.setInterest2("Coding");
        assertEquals("Coding", signupState.getInterest2());
    }

    /**
     * Tests the getInterest3 method.
     * Ensures that the third interest is correctly retrieved after being set.
     */
    @Test
    void getInterest3() {
        signupState.setInterest3("Gaming");
        assertEquals("Gaming", signupState.getInterest3());
    }

    /**
     * Tests the setInterest3 method.
     * Verifies that the third interest is correctly set.
     */
    @Test
    void setInterest3() {
        signupState.setInterest3("Hiking");
        assertEquals("Hiking", signupState.getInterest3());
    }

    /**
     * Tests the getInterests method.
     * Verifies that the list of interests is correctly returned based on individual interests.
     */
    @Test
    void getInterests() {
        signupState.setInterest1("Reading");
        signupState.setInterest2("Music");
        signupState.setInterest3("Gym");
        List<String> interests = signupState.getInterests();
        assertEquals(3, interests.size());
        assertEquals("Reading", interests.get(0));
        assertEquals("Music", interests.get(1));
        assertEquals("Gym", interests.get(2));
    }

    /**
     * Tests the setError method.
     * Verifies that the error message is correctly set.
     */
    @Test
    void setError() {
        signupState.setError("An error occurred");
        assertEquals("An error occurred", signupState.getError());
    }

    /**
     * Tests the getError method.
     * Ensures that the error message is correctly retrieved after being set.
     */
    @Test
    void getError() {
        signupState.setError("Invalid username");
        assertEquals("Invalid username", signupState.getError());
    }
}