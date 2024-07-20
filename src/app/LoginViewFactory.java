package app;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewModelManager;
import use_case.login.LoginInputBoundary;
import views.LoginView;

public class LoginViewFactory {
    public static LoginView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, LoginInputBoundary loginInputBoundary) {
        LoginController loginController = new LoginController(loginInputBoundary);
        return new LoginView(loginViewModel, loginController, viewModelManager);
    }
}
