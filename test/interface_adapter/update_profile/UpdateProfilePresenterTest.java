package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.update_profile.UpdateProfileOutputData;
import interface_adapter.ViewModelManager;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the functionality of the UpdateProfilePresenter class.
 */
class UpdateProfilePresenterTest {
    private UpdateProfilePresenter updateProfilePresenter;
    private UpdateProfileViewModel mockUpdateProfileViewModel;
    private ViewModelManager mockViewModelManager;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockUpdateProfileViewModel = Mockito.mock(UpdateProfileViewModel.class);
        mockViewModelManager = Mockito.mock(ViewModelManager.class);
        updateProfilePresenter = new UpdateProfilePresenter(mockUpdateProfileViewModel, mockViewModelManager);
    }

    /**
     * Tests the prepareSuccessView method of UpdateProfilePresenter.
     * It verifies that the method correctly updates the view model upon successful profile update.
     */
    @Test
    void testPrepareSuccessView() {
        UpdateProfileOutputData outputData = new UpdateProfileOutputData("Jasmine", "password",
                "(Demo)", "Computer Science", 21, Arrays.asList("Reading", "Running"), false);

        // Create a mock UpdateProfileState
        UpdateProfileState mockState = Mockito.mock(UpdateProfileState.class);
        when(mockUpdateProfileViewModel.getState()).thenReturn(mockState);

        updateProfilePresenter.prepareSuccessView(outputData);

        verify(mockState).setUsername(outputData.getUsername());
        verify(mockState).setError(null);
        verify(mockUpdateProfileViewModel).setState(mockState);
        verify(mockUpdateProfileViewModel).firePropertyChanged();
        verify(mockViewModelManager, Mockito.times(2)).firePropertyChanged();
        verify(mockUpdateProfileViewModel).firePropertyChanged("updateSuccess", null, "Profile successfully updated!");
    }

    /**
     * Tests the prepareFailView method of UpdateProfilePresenter.
     * It verifies that the method correctly updates the view model with the appropriate error message upon profile update failure.
     */
    @Test
    void testPrepareFailView() {
        String error = "An error occurred";

        UpdateProfileState mockState = Mockito.mock(UpdateProfileState.class);
        when(mockUpdateProfileViewModel.getState()).thenReturn(mockState);

        updateProfilePresenter.prepareFailView(error);

        verify(mockState).setError(error);
        verify(mockUpdateProfileViewModel).setState(mockState);
        verify(mockUpdateProfileViewModel).firePropertyChanged("generalError", null, error);
    }
}