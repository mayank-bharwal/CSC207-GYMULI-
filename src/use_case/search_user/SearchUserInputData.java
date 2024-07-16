package use_case.search_user;

public class SearchUserInputData {
    private String username;

    public SearchUserInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
