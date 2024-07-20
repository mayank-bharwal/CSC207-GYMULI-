package interface_adapter.update_profile;

import interface_adapter.ViewModelManager;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import use_case.update_profile.UpdateProfileOutputBoundary;
import use_case.update_profile.UpdateProfileOutputData;

public class UpdateProfilePresenter implements UpdateProfileOutputBoundary {

    private final UpdateProfileViewModel updateProfileViewModel;
    private final ViewModelManager viewModelManager;

    public UpdateProfilePresenter(UpdateProfileViewModel updateProfileViewModel,
                                  ViewModelManager viewModelManager) {
        this.updateProfileViewModel = updateProfileViewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void prepareSuccessView(UpdateProfileOutputData user) {
        updateProfileViewModel.setUsername(user.getUsername());
        updateProfileViewModel.setBio(user.getBio());
        updateProfileViewModel.setAge(user.getAge());
        updateProfileViewModel.setProgramOfStudy(user.getProgramOfStudy());
        updateProfileViewModel.setInterests(user.getInterests());

        updateProfileViewModel.setError(null);

        viewModelManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        updateProfileViewModel.setError(error);
    }
}
