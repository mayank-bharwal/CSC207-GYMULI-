package use_case.refresh_user;

public class RefreshUserInteractor {
    private final RefreshUserDataAccessInterface refreshDAO;
    private final RefreshUserOutputBoundary refreshUserPresenter;

    public RefreshUserInteractor(RefreshUserDataAccessInterface refreshDAO, RefreshUserOutputBoundary refreshUserPresenter) {
        this.refreshDAO = refreshDAO;
        this.refreshUserPresenter = refreshUserPresenter;
    }
}
