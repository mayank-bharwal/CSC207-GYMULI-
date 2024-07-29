package app;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewModelManager;
import use_case.login.LoginInputBoundary;
import views.LoginView;


/**
 * A factory class for creating instances of {@link LoginView}.
 */
public class LoginViewFactory {
    /**
     * Creates a new {@link LoginView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param loginViewModel the view model for the login view, containing the data and logic specific to the login view.
     * @param loginInputBoundary the input boundary for the login use case, defining the operations related to user login.
     * @return a new instance of {@link LoginView}.
     */
    public static LoginView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, LoginInputBoundary loginInputBoundary) {
        LoginController loginController = new LoginController(loginInputBoundary);
        return new LoginView(loginViewModel, loginController, viewModelManager);
    }
}
