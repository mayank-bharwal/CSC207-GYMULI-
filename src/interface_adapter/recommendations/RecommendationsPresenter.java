package interface_adapter.recommendations;

import interface_adapter.ViewModelManager;
import use_case.recommendations.RecommendationsOutputBoundary;
import use_case.recommendations.RecommendationsOutputData;

public class RecommendationsPresenter implements RecommendationsOutputBoundary {

    private final RecommendationsViewModel recommendationsViewModel;
    private final ViewModelManager viewModelManager;

    public RecommendationsPresenter(ViewModelManager viewModelManager, RecommendationsViewModel recommendationsViewModel) {
        this.recommendationsViewModel = recommendationsViewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void showSuccessScreen(RecommendationsOutputData recommendationsOutputData){


    }

    @Override
    public void showFailureScreen(String errorMessage){

    }

}
