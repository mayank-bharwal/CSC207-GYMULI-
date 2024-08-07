package use_case.refresh_user;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for RefreshUserInteractor.
 */
class RefreshUserInteractorTest {

    private RefreshUserDataAccessInterface refreshDAO;
    private RefreshUserOutputBoundary refreshUserPresenter;
    private RefreshUserInteractor interactor;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        refreshDAO = mock(RefreshUserDataAccessInterface.class);
        refreshUserPresenter = mock(RefreshUserOutputBoundary.class);
        interactor = new RefreshUserInteractor(refreshDAO, refreshUserPresenter);
    }

    /**
     * Tests the refreshUser method with a non-existent user.
     * Ensures it sets the fail view with the correct message.
     */
    @Test
    void refreshUser_nonExistentUser_setsFailView() {
        RefreshUserInputData inputData = new RefreshUserInputData("nonExistentUser");

        when(refreshDAO.userExists("nonExistentUser")).thenReturn(false);

        interactor.refreshUser(inputData);

        verify(refreshUserPresenter).setFailView("User does not exist");
    }

    /**
     * Tests the refreshUser method with an existing user.
     * Ensures it retrieves the user data and sets the pass view.
     */
    @Test
    void refreshUser_existingUser_setsPassView() {
        RefreshUserInputData inputData = new RefreshUserInputData("existingUser");
        User mockUser = mock(User.class);

        when(refreshDAO.userExists("existingUser")).thenReturn(true);
        when(refreshDAO.userUpdate("existingUser")).thenReturn(mockUser);

        interactor.refreshUser(inputData);

        ArgumentCaptor<RefreshUserOutputData> captor = ArgumentCaptor.forClass(RefreshUserOutputData.class);
        verify(refreshUserPresenter).setPassView(captor.capture());

        RefreshUserOutputData outputData = captor.getValue();
        assertEquals(mockUser, outputData.getUpdatedUser());
        assertFalse(outputData.isFailView());
    }
}
