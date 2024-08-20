package interface_adapter.refresh_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.refresh_user.RefreshUserInputBoundary;
import use_case.refresh_user.RefreshUserInputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RefreshUserController class.
 */
class RefreshUserControllerTest {

    private RefreshUserController refreshUserController;
    private RefreshUserInputBoundary mockRefreshUserInteractor;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockRefreshUserInteractor = mock(RefreshUserInputBoundary.class);
        refreshUserController = new RefreshUserController(mockRefreshUserInteractor);
    }

    /**
     * Tests the refreshUser method.
     * Verifies that the controller correctly passes the username to the interactor and creates the appropriate input data.
     */
    @Test
    void refreshUser() {
        String username = "testUser";

        refreshUserController.refreshUser(username);

        ArgumentCaptor<RefreshUserInputData> captor = ArgumentCaptor.forClass(RefreshUserInputData.class);
        verify(mockRefreshUserInteractor).refreshUser(captor.capture());

        RefreshUserInputData capturedInputData = captor.getValue();
        assertEquals(username, capturedInputData.getUser());
    }
}