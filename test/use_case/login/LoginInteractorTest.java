package use_case.login;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoginInteractor class.
 */
class LoginInteractorTest {

    private LoginInteractor interactor;
    private LoginOutputBoundary outputBoundary;
    private LoginUserDataAccessInterface userDAO;

    /**
     * Sets up the test environment before each test.
     * Initializes the LoginInteractor, LoginOutputBoundary, and LoginUserDataAccessInterface objects.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = mock(LoginOutputBoundary.class);
        userDAO = mock(LoginUserDataAccessInterface.class);
        interactor = new LoginInteractor(outputBoundary, userDAO);
    }

    /**
     * Tests the execute method with valid credentials.
     * Verifies that the success screen is shown.
     */
    @Test
    void execute_withValidCredentials_showsSuccessScreen() {
        LoginInputData inputData = new LoginInputData("validUser", "validPassword");
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("validUser");
        when(mockUser.getPassword()).thenReturn("validPassword");

        when(userDAO.userExists("validUser")).thenReturn(true);
        when(userDAO.getUser("validUser")).thenReturn(mockUser);

        interactor.execute(inputData);

        ArgumentCaptor<LoginOutputData> captor = ArgumentCaptor.forClass(LoginOutputData.class);
        verify(outputBoundary).showSuccessScreen(captor.capture());
        assertEquals("validUser", captor.getValue().getUser().getUsername());
    }

    /**
     * Tests the execute method with an invalid password.
     * Verifies that the failure screen is shown with the message "Incorrect Password".
     */
    @Test
    void execute_withInvalidPassword_showsFailureScreen() {
        LoginInputData inputData = new LoginInputData("validUser", "invalidPassword");
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("validUser");
        when(mockUser.getPassword()).thenReturn("validPassword");

        when(userDAO.userExists("validUser")).thenReturn(true);
        when(userDAO.getUser("validUser")).thenReturn(mockUser);

        interactor.execute(inputData);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(outputBoundary).showFailureScreen(captor.capture());
        assertEquals("Incorrect Password", captor.getValue());
    }

    /**
     * Tests the execute method with a non-existent user.
     * Verifies that the failure screen is shown with the message "User not found".
     */
    @Test
    void execute_withNonExistentUser_showsFailureScreen() {
        LoginInputData inputData = new LoginInputData("nonExistentUser", "anyPassword");
        when(userDAO.userExists("nonExistentUser")).thenReturn(false);

        interactor.execute(inputData);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(outputBoundary).showFailureScreen(captor.capture());
        assertEquals("User not found", captor.getValue());
    }

    /**
     * Tests the execute method with an empty username.
     * Verifies that the failure screen is shown with the message "User not found".
     */
    @Test
    void execute_withEmptyUsername_showsFailureScreen() {
        LoginInputData inputData = new LoginInputData("", "anyPassword");

        interactor.execute(inputData);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(outputBoundary).showFailureScreen(captor.capture());
        assertEquals("User not found", captor.getValue());
    }

    /**
     * Tests the execute method with an empty password.
     * Verifies that the failure screen is shown with the message "Incorrect Password".
     */
    @Test
    void execute_withEmptyPassword_showsFailureScreen() {
        LoginInputData inputData = new LoginInputData("validUser", "");
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("validUser");
        when(mockUser.getPassword()).thenReturn("validPassword");

        when(userDAO.userExists("validUser")).thenReturn(true);
        when(userDAO.getUser("validUser")).thenReturn(mockUser);

        interactor.execute(inputData);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(outputBoundary).showFailureScreen(captor.capture());
        assertEquals("Incorrect Password", captor.getValue());
    }
}