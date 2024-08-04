package interface_adapter.recommendations;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class RecommendationsState {
    private Map<User, Double> userSimilarities = new HashMap<>();
    private String recommendationError = null;

    public RecommendationsState(RecommendationsState copy){
        userSimilarities = copy.userSimilarities;
        recommendationError = copy.recommendationError;
    }

    public RecommendationsState() {}

    public String getRecommendationError() {
        return recommendationError;
    }

    public Map<User, Double> getUserSimilarities() {
        return userSimilarities;
    }

    public void setRecommendationError(String recommendationError) {
        this.recommendationError = recommendationError;
    }

    public void setUserSimilarities(Map<User, Double> userSimilarities) {
        this.userSimilarities = userSimilarities;
    }
}