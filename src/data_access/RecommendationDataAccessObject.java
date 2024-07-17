package data_access;

import entity.User;
import use_case.recommendations.RecommendationDataAccessInterface;

import java.util.List;

public class RecommendationDataAccessObject implements RecommendationDataAccessInterface {
    @Override
    public List<User> getNSimilarUsers(User user, int limit) {
        // get top N users from the database by converting it into MAP and according to similarity score and make a list
        // of users and return
    }
}
