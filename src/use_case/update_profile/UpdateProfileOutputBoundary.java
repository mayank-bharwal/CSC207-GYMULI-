package use_case.update_profile;
//output boundary
public interface UpdateProfileOutputBoundary {
    void prepareSuccessView(UpdateProfileOutputData user);

    void prepareFailView(String error);
}
