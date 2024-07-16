
package app;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignUpViewModel;
import interface_adapter.account_creation.SignUpController;
import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationInputBoundary;
import views.SignupView;

public class SignupViewFactory {
    public static SignupView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, SignUpViewModel signupViewModel, AccountCreationInputBoundary accountCreationInputBoundary) {
        SignUpController signupController = new SignUpController(accountCreationInputBoundary);
        return new SignupView(signupController, signupViewModel);
    }
}
