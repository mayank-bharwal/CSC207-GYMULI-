package use_case.recommendations;

import entity.User;

import java.util.Map;

public class RecommendationsOutputData {
    private final Map<User, Double> userSimilarities;

    public RecommendationsOutputData(Map<User, Double> userSimilarities) {
        this.userSimilarities = userSimilarities;
    }

    public Map<User, Double> getUserSimilarities() {
        return userSimilarities;
    }
}