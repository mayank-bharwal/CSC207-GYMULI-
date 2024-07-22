package interface_adapter.recommendations;

import entity.User;
import interface_adapter.ViewModelManager;
import use_case.recommendations.RecommendationsInputBoundary;
import use_case.recommendations.RecommendationsInputData;

public class RecommendationsController {

    private final RecommendationsInputBoundary recommendationsInputBoundary;
    private final ViewModelManager viewModelManager;

    public RecommendationsController(RecommendationsInputBoundary recommendationsInputBoundary, ViewModelManager viewModelManager) {
        this.recommendationsInputBoundary = recommendationsInputBoundary;
        this.viewModelManager = viewModelManager;
    }

    public void execute(User user, int numberOfRecommendations) {
        RecommendationsInputData inputData = new RecommendationsInputData(user, numberOfRecommendations);
        recommendationsInputBoundary.execute(inputData);
    }
}
