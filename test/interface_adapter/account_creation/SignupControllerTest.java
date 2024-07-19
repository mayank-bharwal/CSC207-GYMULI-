package interface_adapter.account_creation;

import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInputData;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class SignupControllerTest {

    private SignupController signupController;
    private AccountCreationInputBoundary mockAccountCreationInputBoundary;

    @BeforeEach
    void setUp() {
        mockAccountCreationInputBoundary = Mockito.mock(AccountCreationInputBoundary.class);
        signupController = new SignupController(mockAccountCreationInputBoundary);
    }

    @Test
    void testExecute() {
        String username = "Jasmine";
        String password = "password";
        String repeatPassword = "password";
        String programOfStudy = "Computer Science";
        String bio = "(Demo)";
        Integer age = 21;
        LocalDateTime currentTime = LocalDateTime.now();
        ArgumentCaptor<AccountCreationInputData> captor = ArgumentCaptor.forClass(AccountCreationInputData.class);

        signupController.execute(username, password, repeatPassword, programOfStudy, Arrays.asList("Reading", "Running"), bio, age);

        verify(mockAccountCreationInputBoundary).execute(captor.capture());
        AccountCreationInputData capturedInputData = captor.getValue();

        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password, capturedInputData.getPassword());
        assertEquals(repeatPassword, capturedInputData.getRepeatPassword());
        assertEquals(programOfStudy, capturedInputData.getProgramOfStudy());
        assertEquals(bio, capturedInputData.getBio());
        assertEquals(age, capturedInputData.getAge());
        assertNotNull(capturedInputData.getTime());
        assertEquals(2, capturedInputData.getInterests().size());
        assertEquals("Reading", capturedInputData.getInterests().get(0));
        assertEquals("Running", capturedInputData.getInterests().get(1));
    }
}