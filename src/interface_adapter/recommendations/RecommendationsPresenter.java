package interface_adapter.recommendations;

import interface_adapter.ViewModelManager;
import use_case.recommendations.RecommendationsOutputBoundary;
import use_case.recommendations.RecommendationsOutputData;

import java.util.ArrayList;

public class RecommendationsPresenter implements RecommendationsOutputBoundary {

    private final RecommendationsViewModel recommendationsViewModel;

    public RecommendationsPresenter(RecommendationsViewModel recommendationsViewModel) {
        this.recommendationsViewModel = recommendationsViewModel;
    }

    @Override
    public void showSuccessScreen(RecommendationsOutputData recommendationsOutputData){
        RecommendationsState recommendationsState = new RecommendationsState();
        recommendationsState.setUsers(recommendationsOutputData.getSimilarUsers()); // list
        recommendationsViewModel.setState(recommendationsState);
    }

    @Override
    public void showFailureScreen(String errorMessage){

        RecommendationsState recommendationsState = new RecommendationsState();
        recommendationsState.setrecommendationError(errorMessage);
        recommendationsViewModel.setState(recommendationsState);
    }

}
