package app;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewModelManager;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.account_creation.AccountCreationInputBoundary;
import views.SignupView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit test for the {@link SignupViewFactory} class.
 */
class SignupViewFactoryTest {

    private ViewModelManager viewModelManager;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private AccountCreationInputBoundary accountCreationInputBoundary;
    private SignupController signupController;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        loginViewModel = mock(LoginViewModel.class);
        signupViewModel = new SignupViewModel(); // Directly instantiate, no need to mock
        accountCreationInputBoundary = mock(AccountCreationInputBoundary.class);
        signupController = mock(SignupController.class);
    }

    @Test
    void create() {
        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel,
                accountCreationInputBoundary);

        assertNotNull(signupView, "SignupView should not be null");
    }
}