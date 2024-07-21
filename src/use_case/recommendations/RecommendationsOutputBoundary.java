package use_case.recommendations;

import use_case.search_user.SearchUserOutputData;

public interface RecommendationsOutputBoundary {
    void showSuccessScreen(RecommendationsOutputData recommendationsOutputData);

    void showFailureScreen(String error);
}
