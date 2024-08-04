package interface_adapter.search_user;

import use_case.search_user.SearchUserOutputBoundary;
import use_case.search_user.SearchUserOutputData;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    private final SearchUserViewModel searchUserViewModel;

    public SearchUserPresenter(SearchUserViewModel searchUserViewModel) {
        this.searchUserViewModel = searchUserViewModel;
    }

    @Override
    public void showSuccessScreen(SearchUserOutputData user) {
        SearchUserState searchUserState = new SearchUserState();
        searchUserState.setUsername(user.getUsername());
        searchUserViewModel.setState(searchUserState);
    }

    @Override
    public void showFailureScreen(String error) {
        SearchUserState searchUserState = new SearchUserState();
        searchUserState.setUsernameError(error);
        searchUserViewModel.setState(searchUserState);
    }
}
