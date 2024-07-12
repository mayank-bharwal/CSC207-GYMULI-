package use_case.update_profile;

public interface UpdateProfileOutputBoundary {
    void prepareSuccessView(UpdateProfileOutputData user);

    void prepareFailView(String error);
}
