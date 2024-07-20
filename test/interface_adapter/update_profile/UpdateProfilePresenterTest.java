package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.update_profile.UpdateProfileOutputData;
import interface_adapter.ViewModelManager;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

class UpdateProfilePresenterTest {
    private UpdateProfilePresenter updateProfilePresenter;
    private UpdateProfileViewModel mockUpdateProfileViewModel;
    private ViewModelManager mockViewModelManager;

    @BeforeEach
    void setUp() {
        mockUpdateProfileViewModel = Mockito.mock(UpdateProfileViewModel.class);
        mockViewModelManager = Mockito.mock(ViewModelManager.class);
        updateProfilePresenter = new UpdateProfilePresenter(mockUpdateProfileViewModel, mockViewModelManager);
    }

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
        verify(mockViewModelManager).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        String error = "An error occurred";

        updateProfilePresenter.prepareFailView(error);

        verify(mockUpdateProfileViewModel).setError(error);
    }
}