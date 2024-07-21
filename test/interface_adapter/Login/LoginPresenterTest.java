package interface_adapter.Login;

import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.login.LoginOutputData;
import views.MainView;
import entity.User;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginPresenterTest {
    private LoginPresenter loginPresenter;
    private LoginViewModel mockLoginViewModel;
    private ViewModelManager mockViewModelManager;
    private LoginOutputData mockLoginOutputData;

    @BeforeEach
    void setUp() {
        mockLoginViewModel = Mockito.mock(LoginViewModel.class);
        mockViewModelManager = Mockito.mock(ViewModelManager.class);
        mockLoginOutputData = Mockito.mock(LoginOutputData.class);
        loginPresenter = new LoginPresenter(mockLoginViewModel, mockViewModelManager);
    }

    @Test
    void testShowSuccessScreen() {
        User mockUser = Mockito.mock(User.class);
        when(mockLoginOutputData.getUser()).thenReturn(mockUser);

        loginPresenter.showSuccessScreen(mockLoginOutputData);

        verify(mockViewModelManager).setCurrentUser(mockUser);
        verify(mockViewModelManager).setActiveView(MainView.viewName);
        verify(mockViewModelManager).firePropertyChanged();
    }

    @Test
    void testShowFailureScreen() {
        String error = "Login failed";

        LoginState mockState = Mockito.mock(LoginState.class);
        when(mockLoginViewModel.getState()).thenReturn(mockState);

        loginPresenter.showFailureScreen(error);

        verify(mockState).setPasswordError(error);
        verify(mockLoginViewModel).setState(mockState);
        verify(mockLoginViewModel).firePropertyChanged("generalError", null, error);
    }
}