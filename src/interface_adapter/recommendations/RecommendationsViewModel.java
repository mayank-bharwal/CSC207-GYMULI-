package interface_adapter.recommendations;

import interface_adapter.ViewModel;
import interface_adapter.search_user.SearchUserState;

public class RecommendationsViewModel extends ViewModel {

    public final String RECOMMENDATIONS_TITLE = "recommendations view";
    public final String RECOMMENDATIONS_DETAILS = "Find Friends";

    public SearchUserState state = new SearchUserState();
}
