package use_case.recommendations;

import entity.User;

import java.util.List;

public interface RecommendationDataAccessInterface {
    List<User> getNSimilarUsers(User user, int numberOfUsers);
}
