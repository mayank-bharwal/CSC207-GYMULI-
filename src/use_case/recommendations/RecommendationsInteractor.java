package use_case.recommendations;

import entity.User;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// will use a DAO here to interact with the global dictionary in the Database to recommend friends
public class RecommendationsInteractor implements RecommendationsInputBoundary {
    private RecommendationsDataAccessInterface recommendationsDAO;
    private RecommendationsOutputBoundary outputBoundary;

    public RecommendationsInteractor(RecommendationsDataAccessInterface recommendationsDAO, RecommendationsOutputBoundary outputBoundary) {
        this.recommendationsDAO = recommendationsDAO;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RecommendationsInputData recommendationsInputData) {
        User user = recommendationsInputData.getUser();
        int numberOfRecommendations = recommendationsInputData.getNumberOfRecommendations();
        System.out.println("Executing recommendations for user: " + user.getUsername() + " with " + numberOfRecommendations + " recommendations.");

        if (numberOfRecommendations == 0 || recommendationsDAO.getNSimilarUsers(user, numberOfRecommendations).isEmpty()
                || !recommendationsDAO.AccountExists(user.getUsername())) {
            System.out.println("User does not exist or there are no similar users");
            outputBoundary.showFailureScreen("User does not exist or there are no similar users");
        } else {
            Map<User, Double> similarUsersMap = recommendationsDAO.getNSimilarUsers(user, numberOfRecommendations);
            RecommendationsOutputData recommendationsOutputData = new RecommendationsOutputData(similarUsersMap);
            System.out.println("Found " + similarUsersMap.size() + " similar users for user: " + user.getUsername());
            outputBoundary.showSuccessScreen(recommendationsOutputData);
        }
    }
}

