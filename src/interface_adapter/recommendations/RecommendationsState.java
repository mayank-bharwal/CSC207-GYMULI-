package interface_adapter.recommendations;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsState {

    private List<User> users = new ArrayList<>();
    private String recommendationError = null;

    public String getRecommendationError() {return recommendationError;}

    public List<User> getSimilarUsers() {return users;}

    public void setrecommendationError(String recommendationError) {this.recommendationError = recommendationError;}
    public void setUsers(List<User> users) {this.users = users;}
}
