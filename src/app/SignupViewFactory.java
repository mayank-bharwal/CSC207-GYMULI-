package app;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationInputBoundary;
import views.SignupView;

public class SignupViewFactory {
    public static SignupView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, SignupViewModel signupViewModel, AccountCreationInputBoundary accountCreationInputBoundary) {
        SignupController signupController = new SignupController(accountCreationInputBoundary);
        return new SignupView(signupController, signupViewModel, viewModelManager);
    }
}
