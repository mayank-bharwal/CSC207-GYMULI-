package use_case.recommendations;


import entity.User;

import java.util.List;

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
        if(numberOfRecommendations == 0 || recommendationsDAO.getNSimilarUsers(user, numberOfRecommendations).isEmpty()
                || !recommendationsDAO.AccountExists(user.getUsername())) {
            outputBoundary.showFailureScreen("User does not exist or there are no similar users");
        } else {
            RecommendationsOutputData recommendationsOutputData = new RecommendationsOutputData
                    (recommendationsDAO.getNSimilarUsers(user, numberOfRecommendations));
            outputBoundary.showSuccessScreen(recommendationsOutputData);
        }
    }
}
