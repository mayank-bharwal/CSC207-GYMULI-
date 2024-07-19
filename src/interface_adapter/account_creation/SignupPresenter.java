package interface_adapter.account_creation;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationOutputBoundary;
import use_case.account_creation.AccountCreationOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements AccountCreationOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final ViewModelManager viewModelManager;

    public SignupPresenter(ViewModelManager viewModelManager, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewModelManager = viewModelManager;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void setPassView(AccountCreationOutputData user) {
        LocalDateTime responseTime = LocalDateTime.parse(user.getCreationTime());
        user.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        SignupState signupState = signupViewModel.getState();
        signupState.setUsername(user.getUsername());
        signupState.setError(null);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        viewModelManager.setActiveView("LoginView");
        viewModelManager.firePropertyChanged();

        signupViewModel.firePropertyChanged("success", null, "Account successfully created!");
    }

    @Override
    public void setFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setError(error);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged("generalError", null, error);
    }
}
