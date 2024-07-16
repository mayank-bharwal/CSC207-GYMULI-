package interface_adapter.Login;

import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.ViewModelManager;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import views.LoginView;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel viewModel;
    private final LoggedInViewModel logggedInViewModel;
    private ViewModelManager viewModelManager;


    public LoginPresenter(LoginViewModel viewModel, LoggedInViewModel logggedInViewModel,ViewModelManager viewModelManager) {
        this.viewModel = viewModel;
        this.logggedInViewModel = logggedInViewModel;
        this.viewModelManager = viewModelManager;
    }


    @Override
    public void showSuccessScreen(LoginOutputData user) {

    }

    @Override
    public void showFailureScreen(String error) {

    }
}
