package use_case.recommendations;

import entity.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the RecommendationsOutputData class.
 */
class RecommendationsOutputDataTest {

    /**
     * Tests the getUserSimilarities method.
     * Verifies that the user similarities map is returned correctly.
     */
    @Test
    void getUserSimilarities() {
        User user1 = createMockUser("User1");
        User user2 = createMockUser("User2");

        Map<User, Double> userSimilarities = new HashMap<>();
        userSimilarities.put(user1, 0.9);
        userSimilarities.put(user2, 0.8);

        RecommendationsOutputData outputData = new RecommendationsOutputData(userSimilarities);

        Map<User, Double> result = outputData.getUserSimilarities();

        assertEquals(2, result.size());
        assertEquals(0.9, result.get(user1));
        assertEquals(0.8, result.get(user2));
    }

    /**
     * Creates a mock User object with the specified username.
     *
     * @param username The username to set for the mock User.
     * @return The mock User object.
     */
    private User createMockUser(String username) {
        User user = mock(User.class);
        user.setUsername(username);
        return user;
    }
}