package interface_adapter.account_creation;

import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationOutputBoundary;
import use_case.account_creation.AccountCreationOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SignUpPresenter implements AccountCreationOutputBoundary {


        private final SignUpViewModel signupViewModel;
        private final ViewModelManager viewModelManager;

        public SignUpPresenter(ViewModelManager viewModelManager, SignUpViewModel signupViewModel) {
            this.viewModelManager = viewModelManager;
            this.signupViewModel = signupViewModel;
        }

        @Override
        public void setPassView(AccountCreationOutputData user) {
            LocalDateTime responseTime = LocalDateTime.parse(user.getCreationTime());
            user.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

            SignUpState signupState = signupViewModel.getState();
            signupState.setUsername(user.getUsername());
            signupViewModel.setState(signupState);
            signupViewModel.firePropertyChanged();

            viewModelManager.setActiveView("LoginView");
            viewModelManager.firePropertyChanged();
        }

        @Override
        public void setFailView(String error) {
            SignUpState signupState = signupViewModel.getState();
            signupState.setUsernameError(error);
            signupViewModel.firePropertyChanged();
        }


    }


