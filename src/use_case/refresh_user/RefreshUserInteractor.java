package use_case.refresh_user;

import entity.User;

/**
 * Interactor for the refresh user use case.
 * This class handles the business logic for refreshing user data, interacting with the data access layer and the presenter.
 */

public class RefreshUserInteractor implements RefreshUserInputBoundary {
    private final RefreshUserDataAccessInterface refreshDAO;
    private final RefreshUserOutputBoundary refreshUserPresenter;

    /**
     * Constructs a RefreshUserInteractor with the specified data access interface and presenter.
     *
     * @param refreshDAO            The data access interface for accessing user data.
     * @param refreshUserPresenter  The presenter for handling the output of the refresh operation.
     */

    public RefreshUserInteractor(RefreshUserDataAccessInterface refreshDAO, RefreshUserOutputBoundary refreshUserPresenter) {
        this.refreshDAO = refreshDAO;
        this.refreshUserPresenter = refreshUserPresenter;
    }

    /**
     * Refreshes the user data for the given input data.
     * Checks if the user exists and either retrieves the user information or sets an error message.
     *
     * @param inputData The input data containing the username to refresh.
     */

    @Override
    public void refreshUser(RefreshUserInputData inputData) {
        if (!refreshDAO.userExists(inputData.getUser())) {
            refreshUserPresenter.setFailView("User does not exist");
        } else {
            User updatedUser = refreshDAO.getUser(inputData.getUser());
            RefreshUserOutputData refreshUserOutputData = new RefreshUserOutputData(updatedUser, false);
            refreshUserPresenter.setPassView(refreshUserOutputData);
        }
    }
}
