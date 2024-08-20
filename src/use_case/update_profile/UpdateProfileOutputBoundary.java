package use_case.update_profile;
//output boundary

/**
 * Output boundary for the Update Profile use case.
 * Defines the methods for preparing success and failure views based on the result of the operation.
 */

public interface UpdateProfileOutputBoundary {

    /**
     * Prepares the success view when the update profile operation is successful.
     *
     * @param user The data to be displayed in the success view.
     */

    void prepareSuccessView(UpdateProfileOutputData user);

    /**
     * Prepares the failure view when the update profile operation fails.
     *
     * @param error The error message to be displayed in the failure view.
     */

    void prepareFailView(String error);
}
