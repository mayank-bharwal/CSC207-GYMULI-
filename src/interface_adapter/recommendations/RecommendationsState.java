package interface_adapter.recommendations;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsState {

    private List<User> Similarusers = new ArrayList<>();
    private String recommendationError = null;

    public RecommendationsState(RecommendationsState copy){
        Similarusers = copy.Similarusers;
        recommendationError = copy.recommendationError;
    }
    public RecommendationsState() {}

    public String getRecommendationError() {return recommendationError;}
    public List<User> getSimilarUsers() {return Similarusers;}

    public void setrecommendationError(String recommendationError) {this.recommendationError = recommendationError;}
    public void setSimilarusers(List<User> similarusers) {this.Similarusers = similarusers;}
}
