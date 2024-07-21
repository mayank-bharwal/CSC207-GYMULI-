package interface_adapter.recommendations;

import entity.User;
import use_case.recommendations.RecommendationsInputBoundary;
import use_case.recommendations.RecommendationsInputData;

public class RecommendationsController {

    private final RecommendationsInputBoundary recommendationsInputBoundary;

    public RecommendationsController(RecommendationsInputBoundary recommendationsInputBoundary) {
        this.recommendationsInputBoundary = recommendationsInputBoundary;
    }

    public void execute(User user, int numberOfRecommendations) {
        RecommendationsInputData inputData = new RecommendationsInputData(user, numberOfRecommendations);
        recommendationsInputBoundary.execute(inputData);
    }
}
