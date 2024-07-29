package app;

import interface_adapter.ViewModelManager;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfileViewModel;
import org.springframework.data.mongodb.core.query.Update;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.update_profile.UpdateProfileInputBoundary;
import views.SignupView;
import views.UpdateProfileView;

/**
 * A factory class for creating instances of {@link UpdateProfileView}.
 */
public class UpdateProfileViewFactory {

    /**
     * Creates a new {@link UpdateProfileView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @param updateProfileViewModel the view model for the update profile view, containing the data and logic specific to updating the user profile.
     * @param updateProfileInputBoundary the input boundary for the update profile use case, defining the operations related to updating a user's profile.
     * @return a new instance of {@link UpdateProfileView}.
     */
    public static UpdateProfileView create(ViewModelManager viewModelManager,
                                           UpdateProfileViewModel updateProfileViewModel, UpdateProfileInputBoundary updateProfileInputBoundary){
        UpdateProfileController updateProfileController = new UpdateProfileController(updateProfileInputBoundary);
        return new UpdateProfileView(updateProfileViewModel, updateProfileController, viewModelManager);

    }

}
