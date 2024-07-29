package interface_adapter.update_profile;

import interface_adapter.ViewModelManager;
import interface_adapter.account_creation.SignupState;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import use_case.update_profile.UpdateProfileOutputBoundary;
import use_case.update_profile.UpdateProfileOutputData;
import views.MainView;

public class UpdateProfilePresenter implements UpdateProfileOutputBoundary {

    private final  UpdateProfileViewModel updateProfileViewModel;
    private final ViewModelManager viewModelManager;

    public UpdateProfilePresenter(UpdateProfileViewModel updateProfileViewModel,
                                  ViewModelManager viewModelManager) {
        this.updateProfileViewModel = updateProfileViewModel;
        this.viewModelManager = viewModelManager;
    }



    @Override
    public void prepareSuccessView(UpdateProfileOutputData user) {
        UpdateProfileState updateProfileState = updateProfileViewModel.getState();
        updateProfileState.setUsername(user.getUsername());
        updateProfileState.setError(null);
        updateProfileViewModel.setState(updateProfileState);
        updateProfileViewModel.firePropertyChanged();

        viewModelManager.setActiveView(MainView.viewName);
        viewModelManager.firePropertyChanged();

        updateProfileViewModel.firePropertyChanged("updateSuccess", null, "Profile successfully updated!");
        viewModelManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
        UpdateProfileState updateProfileState = updateProfileViewModel.getState();
        updateProfileState.setError(error);
        updateProfileViewModel.setState(updateProfileState);
        updateProfileViewModel.firePropertyChanged("generalError", null, error);
    }
}
