package use_case.update_profile;


import entity.User;
import entity.UserFactory;

import java.util.List;
import java.util.Objects;

public class UpdateProfileInteractor implements UpdateProfileInputBoundary {

    private UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface;
    private UpdateProfileOutputBoundary updateProfileOutputBoundary;


    public UpdateProfileInteractor(UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface,
                                   UpdateProfileOutputBoundary updateProfileOutputBoundary ) {

        this.updateProfileOutputBoundary = updateProfileOutputBoundary;
        this.updateProfileUserDataAccessInterface = updateProfileUserDataAccessInterface;

    }


    @Override
    public void execute(UpdateProfileInputData updateProfileInputData) {

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
            updateProfileOutputBoundary.prepareSuccessView(updateProfileOutputData);

        }







    }
}