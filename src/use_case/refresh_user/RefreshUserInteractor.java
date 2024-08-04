package use_case.refresh_user;

import entity.User;

public class RefreshUserInteractor implements RefreshUserInputBoundary{
    private final RefreshUserDataAccessInterface refreshDAO;
    private final RefreshUserOutputBoundary refreshUserPresenter;

    public RefreshUserInteractor(RefreshUserDataAccessInterface refreshDAO, RefreshUserOutputBoundary refreshUserPresenter) {
        this.refreshDAO = refreshDAO;
        this.refreshUserPresenter = refreshUserPresenter;
    }


    @Override
    public void refreshUser(RefreshUserInputData inputData) {
        if (!refreshDAO.userExists(inputData.getUser())) {
            refreshUserPresenter.setFailView("User does not exist");
        } else {

            User updatedUser = refreshDAO.getUser(inputData.getUser());
            RefreshUserOutputData refreshUserOutputData = new RefreshUserOutputData(updatedUser, false);
        }
    }
}
