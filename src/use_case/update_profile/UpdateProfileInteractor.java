package use_case.update_profile;

import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.Collections;
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

    }
}