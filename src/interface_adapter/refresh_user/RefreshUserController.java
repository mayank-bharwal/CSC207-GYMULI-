package interface_adapter.refresh_user;

import use_case.refresh_user.RefreshUserInputBoundary;
import use_case.refresh_user.RefreshUserInputData;

/**
 * Controller for handling user refresh actions in the application.
 * This class acts as an intermediary between the user interface and the use case layer,
 * facilitating the refresh of user data.
 */

public class RefreshUserController {

    /**
     * The input boundary for the refresh user use case.
     */

    final RefreshUserInputBoundary refreshUserInteractor;

    /**
     * Constructs a RefreshUserController with the specified interactor.
     *
     * @param refreshUserInteractor The interactor to be used for refreshing user data.
     */

    public RefreshUserController(RefreshUserInputBoundary refreshUserInteractor) {
        this.refreshUserInteractor = refreshUserInteractor;
    }

    /**
     * Refreshes the user data for the given username.
     * This method creates the input data required for the refresh user use case
     * and passes it to the interactor.
     *
     * @param username The username of the user to be refreshed.
     */

    public void refreshUser(String username) {
        RefreshUserInputData refreshUserInputData = new RefreshUserInputData(username);
        refreshUserInteractor.refreshUser(refreshUserInputData);
    }
}