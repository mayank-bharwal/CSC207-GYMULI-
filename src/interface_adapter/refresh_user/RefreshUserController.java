package interface_adapter.refresh_user;

import use_case.refresh_user.RefreshUserInputBoundary;
import use_case.refresh_user.RefreshUserInputData;

public class RefreshUserController {

    final RefreshUserInputBoundary refreshUserInteractor;

    public RefreshUserController(RefreshUserInputBoundary refreshUserInteractor) {
        this.refreshUserInteractor = refreshUserInteractor;
    }

    public void refreshUser(String username) {

        RefreshUserInputData refreshUserInputData = new RefreshUserInputData(username);

        refreshUserInteractor.refreshUser(refreshUserInputData);
    }
}


