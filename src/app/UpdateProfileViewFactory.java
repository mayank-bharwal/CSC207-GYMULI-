package app;

import interface_adapter.ViewModelManager;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfileViewModel;
import org.springframework.data.mongodb.core.query.Update;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.update_profile.UpdateProfileInputBoundary;
import views.SignupView;
import views.UpdateProfileView;

public class UpdateProfileViewFactory {

    public static UpdateProfileView create(ViewModelManager viewModelManager,
                                           UpdateProfileViewModel updateProfileViewModel, UpdateProfileInputBoundary updateProfileInputBoundary){
        UpdateProfileController updateProfileController = new UpdateProfileController(updateProfileInputBoundary);
        return new UpdateProfileView(updateProfileViewModel, updateProfileController, viewModelManager);

    }

}
