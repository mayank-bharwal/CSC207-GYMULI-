package app;

import interface_adapter.ViewModelManager;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfileViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.update_profile.UpdateProfileInputBoundary;
import views.UpdateProfileView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit test for the {@link UpdateProfileViewFactory} class.
 */
class UpdateProfileViewFactoryTest {

    private ViewModelManager viewModelManager;
    private UpdateProfileViewModel updateProfileViewModel;
    private UpdateProfileInputBoundary updateProfileInputBoundary;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        updateProfileViewModel = mock(UpdateProfileViewModel.class);
        updateProfileInputBoundary = mock(UpdateProfileInputBoundary.class);
    }

    /**
     * Tests the UpdateProfileViewFactory#create(ViewModelManager, UpdateProfileViewModel, UpdateProfileInputBoundary)
     * method to ensure that it returns a non-null UpdateProfileView.
     */
    @Test
    void create() {
        UpdateProfileView updateProfileView = UpdateProfileViewFactory.create(viewModelManager, updateProfileViewModel, updateProfileInputBoundary);

        assertNotNull(updateProfileView, "UpdateProfileView should not be null");
    }
}