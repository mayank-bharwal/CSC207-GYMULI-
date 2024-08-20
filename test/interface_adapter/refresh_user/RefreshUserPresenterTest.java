package interface_adapter.refresh_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.refresh_user.RefreshUserOutputData;
import entity.User;
import interface_adapter.ViewModelManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RefreshUserPresenter class.
 */
class RefreshUserPresenterTest {

    private RefreshUserPresenter refreshUserPresenter;
    private RefreshUserViewModel mockRefreshUserViewModel;
    private ViewModelManager mockViewModelManager;
    private User mockUser;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockRefreshUserViewModel = mock(RefreshUserViewModel.class);
        mockViewModelManager = mock(ViewModelManager.class);
        refreshUserPresenter = new RefreshUserPresenter(mockRefreshUserViewModel, mockViewModelManager);
        mockUser = mock(User.class);
    }

    /**
     * Tests the setPassView method.
     * Verifies that the view model and view model manager are updated correctly on a successful refresh.
     */
    @Test
    void setPassView() {
        boolean failView = false;
        RefreshUserOutputData outputData = new RefreshUserOutputData(mockUser, failView);
        RefreshUserState mockState = mock(RefreshUserState.class);

        when(mockRefreshUserViewModel.getState()).thenReturn(mockState);
        when(mockUser.getUsername()).thenReturn("testUser");

        refreshUserPresenter.setPassView(outputData);

        verify(mockState).setUsername("testUser");
        verify(mockRefreshUserViewModel).setState(mockState);
        verify(mockRefreshUserViewModel).firePropertyChange("userUpdated", null, mockUser);
        verify(mockViewModelManager).setViewedUser(mockUser);
    }

    /**
     * Tests the setFailView method.
     * Verifies that the view model is updated correctly with the error message.
     */
    @Test
    void setFailView() {
        String error = "User not found";
        RefreshUserState mockState = mock(RefreshUserState.class);

        when(mockRefreshUserViewModel.getState()).thenReturn(mockState);

        refreshUserPresenter.setFailView(error);

        verify(mockState).setUsernameError(error);
        verify(mockRefreshUserViewModel).firePropertyChange("userError", null, error);
    }
}