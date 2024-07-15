package interface_adapter.search_user;

import use_case.search_user.SearchUserOutputBoundary;
import use_case.search_user.SearchUserOutputData;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    private SearchUserViewModel searchUserViewModel;

    public SearchUserPresenter(SearchUserViewModel searchUserViewModel) {
        this.searchUserViewModel = searchUserViewModel;
    }


    @Override
    public void showSuccessScreen(SearchUserOutputData user) {

    }

    @Override
    public void showFailureScreen(String error) {

    }
}
