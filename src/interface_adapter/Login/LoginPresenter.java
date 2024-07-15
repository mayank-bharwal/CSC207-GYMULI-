package interface_adapter.Login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import views.LoginView;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel viewModel;
    private final LogggedInViewModel logggedInViewModel;


    public LoginPresenter(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void showSuccessScreen(LoginOutputData user) {

    }

    @Override
    public void showFailureScreen(String error) {

    }
}
