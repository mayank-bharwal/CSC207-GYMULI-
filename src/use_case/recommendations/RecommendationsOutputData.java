package use_case.recommendations;

import entity.User;

import java.util.List;

public class RecommendationsOutputData {

    private final List<User> users;

    public RecommendationsOutputData(List<User> users) {
        this.users = users;
    }
    public List<User> getSimilarUsers() {return users;}
}
