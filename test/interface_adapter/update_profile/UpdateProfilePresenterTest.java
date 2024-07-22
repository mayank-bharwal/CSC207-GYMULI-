package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.update_profile.UpdateProfileOutputData;
import interface_adapter.ViewModelManager;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

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
                "(Demo)", "Computer Science", 21, Arrays.asList("Reading", "Running"),
                false);

        updateProfilePresenter.prepareSuccessView(outputData);

        verify(mockUpdateProfileViewModel).setUsername(outputData.getUsername());
        verify(mockUpdateProfileViewModel).setBio(outputData.getBio());
        verify(mockUpdateProfileViewModel).setAge(outputData.getAge());
        verify(mockUpdateProfileViewModel).setProgramOfStudy(outputData.getProgramOfStudy());
        verify(mockUpdateProfileViewModel).setInterests(outputData.getInterests());
        verify(mockUpdateProfileViewModel).setError(null);
        verify(mockUpdateProfileViewModel).firePropertyChanged("updateSuccess", null,
                "Profile successfully updated!");
        verify(mockViewModelManager).firePropertyChanged();
    }

    /**
     * Tests the prepareFailView method of UpdateProfilePresenter.
     * It verifies that the method correctly updates the view model with the appropriate error message upon profile update failure.
     */
    @Test
    void testPrepareFailView() {
        String error = "An error occurred";

        updateProfilePresenter.prepareFailView(error);

        verify(mockUpdateProfileViewModel).setError(error);
        verify(mockUpdateProfileViewModel).firePropertyChanged("updateError", null, error);
    }
}