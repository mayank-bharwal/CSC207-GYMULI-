package app;

import interface_adapter.ViewModelManager;
import interface_adapter.recommendations.RecommendationsController;
import interface_adapter.recommendations.RecommendationsViewModel;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserViewModel;
import views.RecommendationView;

/**
 * A factory class for creating instances of {@link RecommendationView}.
 */
public class RecommendationViewFactory {

    /**
     * Creates a new {@link RecommendationView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param recommendationsController the controller responsible for handling the recommendations actions.
     * @param recommendationsViewModel the view model for the recommendations view, containing the data and logic specific to recommendations.
     * @param searchUserController the controller responsible for handling the search user actions.
     * @param searchUserViewModel the view model for the search user view, containing the data and logic specific to searching users.
     * @return a new instance of {@link RecommendationView}.
     */
    public static RecommendationView create(ViewModelManager viewModelManager, RecommendationsController recommendationsController, RecommendationsViewModel recommendationsViewModel, SearchUserController searchUserController, SearchUserViewModel searchUserViewModel) {
        return new RecommendationView(recommendationsController, searchUserController, viewModelManager, recommendationsViewModel, searchUserViewModel);
    }
}
