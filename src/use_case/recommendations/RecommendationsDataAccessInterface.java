package use_case.recommendations;

import entity.User;

import java.util.List;
import java.util.Map;

public interface RecommendationsDataAccessInterface {
    Map<User, Double> getNSimilarUsers(User user, int numberOfUsers);
    boolean AccountExists(String username);
}
