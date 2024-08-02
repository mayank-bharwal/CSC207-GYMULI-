package interface_adapter.account_creation;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationOutputBoundary;
import use_case.account_creation.AccountCreationOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The SignupPresenter class handles the presentation logic for account creation.
 * It updates the view model based on the result of the account creation process.
 */
public class SignupPresenter implements AccountCreationOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final ViewModelManager viewModelManager;
    private final LoginViewModel loginViewModel;

    /**
     * Constructs a new SignupPresenter with the specified view model manager, signup view model, and login view model.
     *
     * @param viewModelManager the view model manager
     * @param signupViewModel  the signup view model
     * @param loginViewModel   the login view model
     */
    public SignupPresenter(ViewModelManager viewModelManager, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewModelManager = viewModelManager;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Sets the pass view for successful account creation.
     * Updates the signup view model state, fires property change events, and activates the login view.
     *
     * @param user the output data containing user information
     */
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

    /**
     * Sets the fail view for unsuccessful account creation.
     * Updates the signup view model state with the error message and fires property change events.
     *
     * @param error the error message
     */
    @Override
    public void setFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setError(error);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged("generalError", null, error);
    }
}