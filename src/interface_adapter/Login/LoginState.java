package interface_adapter.Login;

public class LoginState {

    private String username = "";
    private String password = "";
    private String usernameError = null;
    private String passwordError = null;

    public LoginState(LoginState copy) {
        username = copy.username;
        password = copy.password;
        usernameError = copy.usernameError;
        passwordError = copy.passwordError;
    }

    public LoginState() {}

    public String getPassword() {
        return password;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
