package interface_adapter.Login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class LoginControllerTest {
    private LoginController loginController;
    private LoginInputBoundary mockLoginInputBoundary;

    @BeforeEach
    void setUp() {
        mockLoginInputBoundary = Mockito.mock(LoginInputBoundary.class);
        loginController = new LoginController(mockLoginInputBoundary);
    }

    @Test
    void testLogin() {
        String username = "testUser";
        String password = "password";
        ArgumentCaptor<LoginInputData> captor = ArgumentCaptor.forClass(LoginInputData.class);

        loginController.login(username, password);

        verify(mockLoginInputBoundary).execute(captor.capture());
        LoginInputData capturedInputData = captor.getValue();

        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password, capturedInputData.getPassword());
    }
}