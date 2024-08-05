package use_case.account_creation;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for AccountCreationInteractor.
 */
class AccountCreationInteractorTest {
    private AccountCreationUserDataAccessInterface accountDataAccessObject;
    private AccountCreationOutputBoundary accountPresenter;
    private UserFactory userFactory;
    private AccountCreationInteractor interactor;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        accountDataAccessObject = mock(AccountCreationUserDataAccessInterface.class);
        accountPresenter = mock(AccountCreationOutputBoundary.class);
        userFactory = mock(UserFactory.class);
        interactor = new AccountCreationInteractor(accountDataAccessObject, accountPresenter, userFactory);
    }

    /**
     * Tests the execution of the interactor with valid input data.
     * Ensures that the user is saved and the success view is shown.
     */
    @Test
    void execute_withValidInput_savesUserAndShowsPassView() {
        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        LocalDateTime now = LocalDateTime.now();
        AccountCreationInputData inputData = new AccountCreationInputData(
                "testUser", "password", "password", "Computer Science", interests, "This is a bio", now, 21
        );

        when(accountDataAccessObject.AccountExists("testUser")).thenReturn(false);

        User mockUser = mock(User.class);
        when(userFactory.createUser(anyString(), anyString(), anyString(), anyInt(), anyString(), anyList(), anyList(), anyList(), any(LocalDateTime.class)))
                .thenReturn(mockUser);
        when(mockUser.getUsername()).thenReturn("testUser");
        when(mockUser.getDateCreated()).thenReturn(now);

        interactor.execute(inputData);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(accountDataAccessObject).save(userCaptor.capture());
        assertEquals("testUser", userCaptor.getValue().getUsername());

        ArgumentCaptor<AccountCreationOutputData> outputDataCaptor = ArgumentCaptor.forClass(AccountCreationOutputData.class);
        verify(accountPresenter).setPassView(outputDataCaptor.capture());
        assertEquals("testUser", outputDataCaptor.getValue().getUsername());
    }

    /**
     * Tests the execution of the interactor when the username already exists.
     * Ensures that the failure view is shown with the appropriate message.
     */
    @Test
    void execute_withExistingUsername_showsFailView() {
        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        LocalDateTime now = LocalDateTime.now();
        AccountCreationInputData inputData = new AccountCreationInputData(
                "testUser", "password", "password", "Computer Science", interests, "This is a bio", now, 21
        );
        when(accountDataAccessObject.AccountExists("testUser")).thenReturn(true);

        interactor.execute(inputData);

        verify(accountPresenter).setFailView("Account already exists");
    }

    /**
     * Tests the execution of the interactor with mismatched passwords.
     * Ensures that the failure view is shown with the appropriate message.
     */
    @Test
    void execute_withMismatchedPasswords_showsFailView() {
        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        LocalDateTime now = LocalDateTime.now();
        AccountCreationInputData inputData = new AccountCreationInputData(
                "testUser", "password", "differentPassword", "Computer Science", interests, "This is a bio", now, 21
        );

        interactor.execute(inputData);

        verify(accountPresenter).setFailView("Passwords are not equal");
    }

    /**
     * Tests the execution of the interactor with empty fields.
     * Ensures that the failure view is shown with the appropriate message.
     */
    @Test
    void execute_withEmptyFields_showsFailView() {
        List<String> interests = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        AccountCreationInputData inputData = new AccountCreationInputData(
                "", "password", "password", "Computer Science", interests, "This is a bio", now, 21
        );

        interactor.execute(inputData);

        verify(accountPresenter).setFailView("Please fill in all fields");
    }
}

