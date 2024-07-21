package use_case.recommendations;

import entity.User;

public class RecommendationsInputData {

    private int numberOfRecommendations;
    private User user;

    public RecommendationsInputData(User user, int numberOfRecommendations) {
        this.user = user;
        this.numberOfRecommendations = numberOfRecommendations;
    }

    public int getNumberOfRecommendations() {return numberOfRecommendations;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public void setNumberOfRecommendations(int numberOfRecommendations) {this.numberOfRecommendations = numberOfRecommendations;}
}
