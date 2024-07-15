package use_case.search_user;

import use_case.login.LoginOutputData;

public interface SearchUserOutputBoundary {
    void showSuccessScreen(SearchUserOutputData user);

    void showFailureScreen(String error);

}
