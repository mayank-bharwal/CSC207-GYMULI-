package app;

import interface_adapter.LoginViewModel;
import interface_adapter.SignupViewModel;
import interface_adapter.ViewModelManager;
import views.SignupView;

public class SignupViewFactory {
    public static SignupView create(ViewModelManager viewModelManager, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        // You can add more setup code here if needed
        return new SignupView(viewModelManager, signupViewModel);
    }
}