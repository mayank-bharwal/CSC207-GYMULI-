package interface_adapter.Login;

import interface_adapter.ViewModelManager;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewModelManager viewModelManager;

    public LoginPresenter(LoginViewModel loginViewModel, ViewModelManager viewModelManager) {
        this.loginViewModel = loginViewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void showSuccessScreen(LoginOutputData user) {
        viewModelManager.setActiveView("ChatView");
        viewModelManager.firePropertyChanged();
    }

    @Override
    public void showFailureScreen(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setPasswordError(error);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged("generalError", null, error);
    }
}
