package interface_adapter.search_user;



public class SearchUserState {

    private String username = "";
    private String usernameError = null;


    public SearchUserState(SearchUserState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }

    public SearchUserState(){};


    public String getUsernameError() {
        return usernameError;
    }

    public String getUsername() {
        return username;
    }


    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
