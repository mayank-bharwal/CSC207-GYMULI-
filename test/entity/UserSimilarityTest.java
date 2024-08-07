package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UserSimilarity class.
 */
class UserSimilarityTest {

    /**
     * Tests the getUsername method.
     * Verifies that the correct username is returned.
     */
    @Test
    void getUsername() {
        String expectedUsername = "testUser";
        double score = 0.8;
        UserSimilarity userSimilarity = new UserSimilarity(expectedUsername, score);

        String actualUsername = userSimilarity.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    /**
     * Tests the getScore method.
     * Verifies that the correct score is returned.
     */
    @Test
    void getScore() {
        String username = "testUser";
        double expectedScore = 0.8;
        UserSimilarity userSimilarity = new UserSimilarity(username, expectedScore);

        double actualScore = userSimilarity.getScore();

        assertEquals(expectedScore, actualScore, 0.001);
    }
}