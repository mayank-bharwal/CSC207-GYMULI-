package interface_adapter.recommendations;

import entity.User;

import java.util.List;

public class RecommendationsState {

    private List<User> users;
    private String recommendationError = null;

    public RecommendationsState(RecommendationsState copy) {
        users = copy.users;
        recommendationError = copy.recommendationError;
    }

    public RecommendationsState() {};

    public String getRecommendationError() {return recommendationError;}

    public List<User> getSimilarUsers() {return users;}

    public void setrecommendationError(String recommendationError) {this.recommendationError = recommendationError;}
    public void setUsers(List<User> users) {this.users = users;}
}
