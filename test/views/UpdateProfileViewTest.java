package views;

import interface_adapter.ViewModelManager;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfileState;
import interface_adapter.update_profile.UpdateProfileViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link UpdateProfileView} class.
 * This class tests the functionality of the UpdateProfileView, ensuring it properly handles
 * user interactions, state updates, and property changes.
 */
class UpdateProfileViewTest {

    private UpdateProfileView updateProfileView;
    private UpdateProfileViewModel updateProfileViewModel;
    private UpdateProfileController updateProfileController;
    private ViewModelManager viewModelManager;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        updateProfileViewModel = mock(UpdateProfileViewModel.class);
        updateProfileController = mock(UpdateProfileController.class);
        viewModelManager = mock(ViewModelManager.class);

        when(updateProfileViewModel.getState()).thenReturn(new UpdateProfileState());

        updateProfileView = new UpdateProfileView(updateProfileViewModel, updateProfileController, viewModelManager);
    }

    /**
     * Tests the actionPerformed method in UpdateProfileView.
     * This test ensures that no exceptions are thrown during action handling.
     */
    @Test
    void actionPerformed() {
        updateProfileView.actionPerformed(null);
    }

    /**
     * Tests the propertyChange method when a general error occurs.
     * This test ensures that the error message is displayed when an error is present in the state.
     */
    @Test
    void propertyChange_generalError() {
        UpdateProfileState state = new UpdateProfileState();
        state.setError("Error updating profile");
        when(updateProfileViewModel.getState()).thenReturn(state);

        PropertyChangeEvent event = new PropertyChangeEvent(this, "generalError", null, "Error updating profile");
        updateProfileView.propertyChange(event);

        verify(updateProfileViewModel, times(1)).getState();
    }

    /**
     * Tests the propertyChange method when the profile is successfully updated.
     * This test ensures that a success message is displayed and the view is switched to LoginView.
     */
    @Test
    void propertyChange_updateSuccess() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "updateSuccess", null, null);
        updateProfileView.propertyChange(event);

        verify(viewModelManager, times(1)).setActiveView(LoginView.viewName);
    }
}