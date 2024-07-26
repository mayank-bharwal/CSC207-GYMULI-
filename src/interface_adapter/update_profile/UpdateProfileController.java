package interface_adapter.update_profile;

import use_case.login.LoginInputBoundary;
import use_case.update_profile.UpdateProfileInputBoundary;
import use_case.update_profile.UpdateProfileInputData;
import use_case.update_profile.UpdateProfileOutputBoundary;
import use_case.update_profile.UpdateProfileOutputData;

import java.util.List;

public class UpdateProfileController {
    private final UpdateProfileInputBoundary updateProfileInputBoundary;
    ;

    public UpdateProfileController(UpdateProfileInputBoundary updateProfileInputBoundary) {
        this.updateProfileInputBoundary = updateProfileInputBoundary;
    }

    public void update(String username, String password, String currentUsername,
                       String currentPassword, String bio, Integer age,
                       String programOfStudy, List<String> interests) {

        UpdateProfileInputData updateProfileInputData = new UpdateProfileInputData(currentUsername, currentPassword, username,
                password, bio, programOfStudy, age, interests);
        updateProfileInputBoundary.execute(updateProfileInputData);
    }
}

