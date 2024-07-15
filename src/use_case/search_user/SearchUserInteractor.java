package use_case.search_user;

public class SearchUserInteractor implements SearchUserInputBoundary{

    private SearchUserDataAccessInterface searchUserDAO;
    private SearchUserOutputBoundary outputBoundary;

    public SearchUserInteractor(SearchUserOutputBoundary outputBoundary, SearchUserDataAccessInterface searchUserDAO) {
        this.outputBoundary = outputBoundary;
        this.searchUserDAO = searchUserDAO;
    }
    @Override
    public void execute(SearchUserInputData searchUserInputData) {

        String username = searchUserInputData.getUsername();
        if (searchUserDAO.userExists(username)){
            SearchUserOutputData searchUserOutputData = new SearchUserOutputData(username);
            outputBoundary.showSuccessScreen(searchUserOutputData);
        }else{
            outputBoundary.showFailureScreen("User does not exist");
        }

    }
}
