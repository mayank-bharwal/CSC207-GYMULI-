package app;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationInputBoundary;
import views.SignupView;

/**
 * A factory class for creating instances of {@link SignupView}.
 */
public class SignupViewFactory {

    /**
     * Creates a new {@link SignupView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param loginViewModel the view model for the login view, containing the data and logic specific to the login view.
     * @param signupViewModel the view model for the signup view, containing the data and logic specific to the signup view.
     * @param accountCreationInputBoundary the input boundary for the account creation use case, defining the operations related to creating a new account.
     * @return a new instance of {@link SignupView}.
     */
    public static SignupView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, SignupViewModel signupViewModel, AccountCreationInputBoundary accountCreationInputBoundary) {
        SignupController signupController = new SignupController(accountCreationInputBoundary);
        return new SignupView(signupController, signupViewModel, viewModelManager);
    }
}