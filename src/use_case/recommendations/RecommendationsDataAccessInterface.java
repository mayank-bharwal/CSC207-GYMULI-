package use_case.recommendations;

import entity.User;

import java.util.List;

public interface RecommendationsDataAccessInterface {
    List<User> getNSimilarUsers(User user, int numberOfUsers);
    public boolean AccountExists(String username);
}
