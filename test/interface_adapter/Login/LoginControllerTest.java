package interface_adapter.Login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the LoginController class.
 */
class LoginControllerTest {
    private LoginController loginController;
    private LoginInputBoundary mockLoginInputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockLoginInputBoundary = Mockito.mock(LoginInputBoundary.class);
        loginController = new LoginController(mockLoginInputBoundary);
    }

    /**
     * Tests the login method of LoginController.
     * It verifies that the login method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void testLogin() {
        String username = "Jasmine";
        String password = "password";
        ArgumentCaptor<LoginInputData> captor = ArgumentCaptor.forClass(LoginInputData.class);

        loginController.login(username, password);

        verify(mockLoginInputBoundary).execute(captor.capture());
        LoginInputData capturedInputData = captor.getValue();

        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password, capturedInputData.getPassword());
    }
}