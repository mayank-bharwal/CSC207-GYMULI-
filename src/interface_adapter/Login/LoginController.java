package interface_adapter.Login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInputBoundary.execute(loginInputData);

    }
}


