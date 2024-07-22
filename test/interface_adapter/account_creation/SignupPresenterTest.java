package interface_adapter.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.account_creation.AccountCreationOutputBoundary;
import use_case.account_creation.AccountCreationOutputData;
import interface_adapter.ViewModelManager;
import interface_adapter.Login.LoginViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the SignupPresenter class.
 */
class SignupPresenterTest {
    private SignupPresenter signupPresenter;
    private ViewModelManager mockViewModelManager;
    private SignupViewModel mockSignupViewModel;
    private LoginViewModel mockLoginViewModel;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockViewModelManager = Mockito.mock(ViewModelManager.class);
        mockSignupViewModel = Mockito.mock(SignupViewModel.class);
        signupPresenter = new SignupPresenter(mockViewModelManager, mockSignupViewModel, mockLoginViewModel);
    }

    /**
     * Tests the setPassView method of SignupPresenter.
     * It verifies that the method correctly updates the view model and view model manager upon successful account creation.
     */
    @Test
    void testSetPassView() {
        AccountCreationOutputData outputData = new AccountCreationOutputData("Jasmine",
                "2024-07-20T12:00:00", false);

        SignupState mockSignupState = Mockito.mock(SignupState.class);
        Mockito.when(mockSignupViewModel.getState()).thenReturn(mockSignupState);

        signupPresenter.setPassView(outputData);

        LocalDateTime responseTime = LocalDateTime.parse("2024-07-20T12:00:00");
        String formattedTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));

        verify(mockSignupState).setUsername(outputData.getUsername());
        verify(mockSignupState).setError(null);
        verify(mockSignupViewModel).setState(mockSignupState);
        verify(mockSignupViewModel).firePropertyChanged();
        verify(mockViewModelManager).setActiveView("LoginView");
        verify(mockViewModelManager).firePropertyChanged();
        verify(mockSignupViewModel).firePropertyChanged("success", null,
                "Account successfully created!");
        assertEquals(formattedTime, outputData.getCreationTime());
    }

    /**
     * Tests the setFailView method of SignupPresenter.
     * It verifies that the method correctly updates the view model with the appropriate error message upon account creation failure.
     */
    @Test
    void testSetFailView() {
        String error = "Username already exists";

        SignupState mockSignupState = Mockito.mock(SignupState.class);
        Mockito.when(mockSignupViewModel.getState()).thenReturn(mockSignupState);

        signupPresenter.setFailView(error);

        verify(mockSignupState).setError(error);
        verify(mockSignupViewModel).setState(mockSignupState);
        verify(mockSignupViewModel).firePropertyChanged("generalError", null, error);
    }
}