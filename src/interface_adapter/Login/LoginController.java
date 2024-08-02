package interface_adapter.Login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * Controller for handling User Logins
 */
public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    /**
     * Constructor for LoginController
     * @param loginInputBoundary
     */
    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    /**
     * executes the login process
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInputBoundary.execute(loginInputData);

    }
}


