package use_case.refresh_user;

public class RefreshUserOutputData {
    private final String updatedUser;
    private final boolean failView;

    public RefreshUserOutputData(String updatedUser, boolean failView) {
        this.updatedUser = updatedUser;
        this.failView = failView;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }
    public boolean isFailView() {
        return failView;
    }
}
