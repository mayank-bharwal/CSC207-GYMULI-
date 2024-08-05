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
        System.out.println("Interactor: Searching for user: " + username);
        if (searchUserDAO.userExists(username)){
            System.out.println("Interactor: User exists");
            SearchUserOutputData searchUserOutputData = new SearchUserOutputData(username);
            outputBoundary.showSuccessScreen(searchUserOutputData);
        }else{
            System.out.println("Interactor: User does not exist");
            outputBoundary.showFailureScreen("User does not exist");
        }
    }
}
