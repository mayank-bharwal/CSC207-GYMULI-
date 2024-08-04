package interface_adapter.remove_friends;

import use_case.remove_friends.RemoveFriendsInputBoundary;
import use_case.remove_friends.RemoveFriendsOutputBoundary;
import use_case.remove_friends.RemoveFriendsOutputData;

public class RemoveFriendsPresenter implements RemoveFriendsOutputBoundary {
    private final RemoveFriendsViewModel removeFriendsViewModel;

    public RemoveFriendsPresenter(RemoveFriendsViewModel removeFriendsViewModel) {
        this.removeFriendsViewModel = removeFriendsViewModel;
    }

    @Override
    public void setPassView(RemoveFriendsOutputData user) {
        RemoveFriendsState state = removeFriendsViewModel.getState();
        state.setSuccess(true);
        state.setError(null);
        removeFriendsViewModel.setState(state);
        removeFriendsViewModel.firePropertyChanged();
    }

    @Override
    public void setFailView(String error) {
        RemoveFriendsState state = new RemoveFriendsState();
        state.setError(error);
        state.setSuccess(false);
        removeFriendsViewModel.setState(state);
        removeFriendsViewModel.firePropertyChanged();

    }
}
