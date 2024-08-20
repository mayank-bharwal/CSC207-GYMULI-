package use_case.update_profile;


import entity.User;
import entity.UserFactory;

import java.util.List;
import java.util.Objects;


/**
 * Interactor for the Update Profile use case, responsible for processing and validating
 * the input data and coordinating with the output boundary and data access interfaces.
 * Implements the UpdateProfileInputBoundary.
 */

public class UpdateProfileInteractor implements UpdateProfileInputBoundary {

    private UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface;
    private UpdateProfileOutputBoundary updateProfileOutputBoundary;

    /**
     * Constructs an UpdateProfileInteractor with the provided data access and output boundary interfaces.
     *
     * @param updateProfileUserDataAccessInterface  The interface to access user data for updating the profile.
     * @param updateProfileOutputBoundary          The interface to handle the output of the update profile operation.
     */


    public UpdateProfileInteractor(UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface,
                                   UpdateProfileOutputBoundary updateProfileOutputBoundary ) {

        this.updateProfileOutputBoundary = updateProfileOutputBoundary;
        this.updateProfileUserDataAccessInterface = updateProfileUserDataAccessInterface;

    }

    /**
     * Executes the update profile operation. Validates the input data and interacts with the
     * data access layer to update the user's profile. Depending on the outcome, the success
     * or failure view is prepared via the output boundary.
     *
     * @param updateProfileInputData The input data required to update the user's profile.
     */


    @Override
    public void execute(UpdateProfileInputData updateProfileInputData) {

        System.out.println("interactor called");

        String currentUsername = updateProfileInputData.getCurrentUsername();
        String currentPassword = updateProfileInputData.getCurrentPassword();
        String username = updateProfileInputData.getUsername();
        String password = updateProfileInputData.getPassword();
        String bio = updateProfileInputData.getBio();
        String programOfStudy = updateProfileInputData.getProgramOfStudy();
        Integer age = updateProfileInputData.getAge();
        List<String> interests = updateProfileInputData.getInterests();

        if (!updateProfileUserDataAccessInterface.userExists(currentUsername) ) {
            updateProfileOutputBoundary.prepareFailView("Wrong Username");

        } else if (!Objects.equals(updateProfileUserDataAccessInterface.getUser(currentUsername).getPassword(), currentPassword)) {
            updateProfileOutputBoundary.prepareFailView("Wrong Password");

        }

        else if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                bio == null || bio.trim().isEmpty() || age == null||

                programOfStudy == null || programOfStudy.trim().isEmpty() ||
                interests == null || interests.isEmpty()) {

            // Handle the case where one or more fields are empty or null
            updateProfileOutputBoundary.prepareFailView("All fields must be filled.");


        } else if(updateProfileUserDataAccessInterface.userExists(username) && !username.equals(currentUsername)) {
            updateProfileOutputBoundary.prepareFailView("Username taken.");

        }
        else {
            // Proceed with the rest of your logic if all fields are valid
            UpdateProfileOutputData updateProfileOutputData = new UpdateProfileOutputData(
                    username,password,bio,programOfStudy,age,interests,false
            );
            updateProfileUserDataAccessInterface.updateUser(currentUsername ,username, password, bio, programOfStudy, age, interests);
            System.out.println(" success");
            updateProfileOutputBoundary.prepareSuccessView(updateProfileOutputData);

        }







    }
}