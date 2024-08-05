package use_case.recommendations;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RecommendationsInputData class.
 */
class RecommendationsInputDataTest {

    private RecommendationsInputData recommendationsInputData;
    private User testUser;

    /**
     * Sets up the test environment before each test.
     * Initializes the necessary mock objects and the RecommendationsInputData instance.
     */
    @BeforeEach
    void setUp() {
        testUser = mock(User.class);
        recommendationsInputData = new RecommendationsInputData(testUser, 5);
    }

    /**
     * Tests the getNumberOfRecommendations method.
     * Verifies that the method returns the correct number of recommendations.
     */
    @Test
    void getNumberOfRecommendations() {
        assertEquals(5, recommendationsInputData.getNumberOfRecommendations());
    }

    /**
     * Tests the getUser method.
     * Verifies that the method returns the correct user instance.
     */
    @Test
    void getUser() {
        assertEquals(testUser, recommendationsInputData.getUser());
    }

    /**
     * Tests the setUser method.
     * Verifies that the method correctly sets and returns the new user instance.
     */
    @Test
    void setUser() {
        User newUser = mock(User.class);
        recommendationsInputData.setUser(newUser);
        assertEquals(newUser, recommendationsInputData.getUser());
    }

    /**
     * Tests the setNumberOfRecommendations method.
     * Verifies that the method correctly sets and returns the new number of recommendations.
     */
    @Test
    void setNumberOfRecommendations() {
        recommendationsInputData.setNumberOfRecommendations(10);
        assertEquals(10, recommendationsInputData.getNumberOfRecommendations());
    }
}