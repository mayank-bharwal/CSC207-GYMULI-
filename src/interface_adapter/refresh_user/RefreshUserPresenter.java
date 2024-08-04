package interface_adapter.refresh_user;

import interface_adapter.ViewModelManager;
import use_case.refresh_user.RefreshUserOutputBoundary;
import use_case.refresh_user.RefreshUserOutputData;

public class RefreshUserPresenter implements RefreshUserOutputBoundary {

    private final RefreshUserViewModel refreshUserViewModel;
    private final ViewModelManager viewModelManager;


    public RefreshUserPresenter(RefreshUserViewModel refreshUserViewModel, ViewModelManager viewModelManager) {
        this.refreshUserViewModel = refreshUserViewModel;
        this.viewModelManager = viewModelManager;
    }


    @Override
    public void setPassView(RefreshUserOutputData user) {
        RefreshUserState refreshUserState = refreshUserViewModel.getState();
        refreshUserState.setUsername(user.getUpdatedUser().getUsername());
        this.refreshUserViewModel.setState(refreshUserState);
        refreshUserViewModel.firePropertyChange("userUpdated", null, user.getUpdatedUser());

    }

    @Override
    public void setFailView(String error) {
        RefreshUserState refreshUserState = refreshUserViewModel.getState();
        refreshUserState.setUsernameError(error);
        refreshUserViewModel.firePropertyChange("userError", null, error);

    }
}
