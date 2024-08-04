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
        recommendationsState.setUserSimilarities(recommendationsOutputData.getUserSimilarities());
        recommendationsViewModel.setState(recommendationsState);
        recommendationsViewModel.firePropertyChanged("userSimilarities", null, recommendationsState.getUserSimilarities());
    }

    @Override
    public void showFailureScreen(String errorMessage){
        RecommendationsState recommendationsState = new RecommendationsState();
        recommendationsState.setRecommendationError(errorMessage);
        recommendationsViewModel.setState(recommendationsState);
        recommendationsViewModel.firePropertyChanged("generalError", null, errorMessage);
    }

}

