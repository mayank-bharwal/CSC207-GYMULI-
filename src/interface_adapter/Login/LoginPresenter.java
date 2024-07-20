package interface_adapter.Login;

import interface_adapter.ViewModelManager;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import views.MainView;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;
    private final ViewModelManager viewModelManager;

    public LoginPresenter(LoginViewModel viewModel, ViewModelManager viewModelManager) {
        this.viewModel = viewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void showSuccessScreen(LoginOutputData user) {
        viewModelManager.setActiveView(MainView.viewName);
        viewModelManager.setCurrentUser(user.getUsername());
        viewModelManager.firePropertyChanged();
    }

    @Override
    public void showFailureScreen(String error) {
        LoginState loginState = viewModel.getState();
        loginState.setPasswordError(error);
        viewModel.setState(loginState);
        viewModel.firePropertyChanged("generalError", null, error);
    }
}

