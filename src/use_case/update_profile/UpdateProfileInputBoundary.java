
package use_case.update_profile;

/**
 * Input boundary for the Update Profile use case.
 * Defines the method to execute the update profile operation.
 */

public interface UpdateProfileInputBoundary {

    /**
     * Executes the update profile use case with the provided input data.
     *
     * @param updateProfileInputData The data required to update the user's profile.
     */

    void execute(UpdateProfileInputData updateProfileInputData);
}
