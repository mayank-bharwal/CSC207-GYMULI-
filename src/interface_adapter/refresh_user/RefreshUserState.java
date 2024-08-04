package interface_adapter.refresh_user;


public class RefreshUserState {
    private String username = "";
    private String usernameError = "";

    public RefreshUserState(RefreshUserState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }

    public RefreshUserState() {}

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
