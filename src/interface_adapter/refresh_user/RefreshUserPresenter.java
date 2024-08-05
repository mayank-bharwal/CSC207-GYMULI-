package interface_adapter.refresh_user;

import interface_adapter.ViewModelManager;
import use_case.refresh_user.RefreshUserOutputBoundary;
import use_case.refresh_user.RefreshUserOutputData;

/**
 * Presenter for handling the output of the refresh user use case.
 * This class updates the view model with the results of the refresh operation
 * and manages the state changes in the view model.
 */

public class RefreshUserPresenter implements RefreshUserOutputBoundary {

    private final RefreshUserViewModel refreshUserViewModel;
    private final ViewModelManager viewModelManager;

    /**
     * Constructs a RefreshUserPresenter with the specified view model and view model manager.
     *
     * @param refreshUserViewModel The view model to be updated with refresh results.
     * @param viewModelManager     The manager for handling view model operations.
     */

    public RefreshUserPresenter(RefreshUserViewModel refreshUserViewModel, ViewModelManager viewModelManager) {
        this.refreshUserViewModel = refreshUserViewModel;
        this.viewModelManager = viewModelManager;
    }

    /**
     * Updates the view model with the successful refresh result.
     * Sets the username in the view model's state and fires a property change event.
     *
     * @param user The output data containing the updated user information.
     */

    @Override
    public void setPassView(RefreshUserOutputData user) {
        RefreshUserState refreshUserState = refreshUserViewModel.getState();
        refreshUserState.setUsername(user.getUpdatedUser().getUsername());
        this.refreshUserViewModel.setState(refreshUserState);
        refreshUserViewModel.firePropertyChange("userUpdated", null, user.getUpdatedUser());

        viewModelManager.setViewedUser(user.getUpdatedUser());
    }

    /**
     * Updates the view model with the error result of the refresh operation.
     * Sets the username error in the view model's state and fires a property change event.
     *
     * @param error The error message to be set in the view model's state.
     */

    @Override
    public void setFailView(String error) {
        RefreshUserState refreshUserState = refreshUserViewModel.getState();
        refreshUserState.setUsernameError(error);
        refreshUserViewModel.firePropertyChange("userError", null, error);
    }
}