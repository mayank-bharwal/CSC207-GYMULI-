package interface_adapter.recommendations;

import interface_adapter.ViewModelManager;
import use_case.recommendations.RecommendationsOutputBoundary;
import use_case.recommendations.RecommendationsOutputData;

public class RecommendationsPresenter implements RecommendationsOutputBoundary {

    private final RecommendationsViewModel recommendationsViewModel;
    private final ViewModelManager viewModelManager;

    public RecommendationsPresenter(RecommendationsViewModel recommendationsViewModel, ViewModelManager viewModelManager) {
        this.recommendationsViewModel = recommendationsViewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void showSuccessScreen(RecommendationsOutputData recommendationsOutputData){
        RecommendationsState recommendationsState = new RecommendationsState();
        recommendationsState.setSimilarusers(recommendationsOutputData.getSimilarUsers()); // list
        recommendationsViewModel.setState(recommendationsState);
    }

    @Override
    public void showFailureScreen(String errorMessage){

        RecommendationsState recommendationsState = new RecommendationsState();
        recommendationsState.setrecommendationError(errorMessage);
        recommendationsViewModel.setState(recommendationsState);
    }

}
