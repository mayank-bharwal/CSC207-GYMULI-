package use_case.refresh_user;

import entity.User;

public class RefreshUserOutputData {
    private final User updatedUser;
    private final boolean failView;

    public RefreshUserOutputData(User updatedUser, boolean failView) {
        this.updatedUser = updatedUser;
        this.failView = failView;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }
    public boolean isFailView() {
        return failView;
    }
}
