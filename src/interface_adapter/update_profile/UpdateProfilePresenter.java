package interface_adapter.update_profile;

import interface_adapter.ViewModelManager;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import use_case.update_profile.UpdateProfileOutputBoundary;
import use_case.update_profile.UpdateProfileOutputData;

public class UpdateProfilePresenter implements UpdateProfileOutputBoundary {

    private UpdateProfileViewModel updateProfileViewModel;
    private ViewModelManager viewModelManager;

    public UpdateProfilePresenter(UpdateProfileViewModel updateProfileViewModel,
                                  ViewModelManager viewModelManager) {
        this.updateProfileViewModel = updateProfileViewModel;
        this.viewModelManager = viewModelManager;
    }
    @Override
    public void prepareSuccessView(UpdateProfileOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
