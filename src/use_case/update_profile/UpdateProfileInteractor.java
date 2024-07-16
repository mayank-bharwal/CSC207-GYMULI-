package use_case.update_profile;


import java.util.List;

public class UpdateProfileInteractor implements UpdateProfileInputBoundary {

    private UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface;
    private UpdateProfileOutputBoundary updateProfileOutputBoundary;

    public UpdateProfileInteractor(UpdateProfileUserDataAccessInterface updateProfileUserDataAccessInterface,
                                   UpdateProfileOutputBoundary updateProfileOutputBoundary) {

        this.updateProfileOutputBoundary = updateProfileOutputBoundary;
        this.updateProfileUserDataAccessInterface = updateProfileUserDataAccessInterface;

    }


    @Override
    public void execute(UpdateProfileInputData updateProfileInputData) {

        String username = updateProfileInputData.getUsername();
        String password = updateProfileInputData.getPassword();
        String bio = updateProfileInputData.getBio();
        String programOfStudy = updateProfileInputData.getProgramOfStudy();
        Integer age = updateProfileInputData.getAge();
        List<String> interests = updateProfileInputData.getInterests();

        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                bio == null || bio.trim().isEmpty() || age == null||
                programOfStudy == null || programOfStudy.trim().isEmpty() ||
                interests == null || interests.isEmpty()) {

            // Handle the case where one or more fields are empty or null
            updateProfileOutputBoundary.prepareFailView("All fields must be filled.");
        } else {
            // Proceed with the rest of your logic if all fields are valid
            UpdateProfileOutputData updateProfileOutputData = new UpdateProfileOutputData(
                    username,password,bio,programOfStudy,age,interests,false
            );
            updateProfileOutputBoundary.prepareSuccessView(updateProfileOutputData);
        }







    }
}