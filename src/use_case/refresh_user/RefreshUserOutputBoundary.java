package use_case.refresh_user;

public interface RefreshUserOutputBoundary {

    void setPassView(RefreshUserOutputData user);

    void setFailView(String error);
}
