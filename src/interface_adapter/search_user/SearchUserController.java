package interface_adapter.search_user;


import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInputData;

public class SearchUserController {

    final SearchUserInputBoundary searchUserInputBoundary;

    public SearchUserController(SearchUserInputBoundary searchUserInputBoundary) {
        this.searchUserInputBoundary = searchUserInputBoundary;
    }

    public void search_user(String username) {
        System.out.println("Controller: Searching for user: " + username);
        SearchUserInputData searchUserInputData = new SearchUserInputData(username);
        searchUserInputBoundary.execute(searchUserInputData);

}}
