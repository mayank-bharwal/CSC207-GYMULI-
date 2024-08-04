package interface_adapter.remove_friends;

import use_case.remove_friends.RemoveFriendsOutputBoundary;
import use_case.remove_friends.RemoveFriendsOutputData;

/**
 * The {@code RemoveFriendsPresenter} class implements the {@link RemoveFriendsOutputBoundary} interface
 * to handle the output from the use case of removing friends. It updates the {@link RemoveFriendsViewModel}
 * with the result of the removal operation and triggers property change notifications.
 */
public class RemoveFriendsPresenter implements RemoveFriendsOutputBoundary {
    private final RemoveFriendsViewModel removeFriendsViewModel;

    /**
     * Constructs a {@code RemoveFriendsPresenter} with the specified {@link RemoveFriendsViewModel}.
     *
     * @param removeFriendsViewModel the view model to be updated with the result of the removal operation
     */
    public RemoveFriendsPresenter(RemoveFriendsViewModel removeFriendsViewModel) {
        this.removeFriendsViewModel = removeFriendsViewModel;
    }

    /**
     * Updates the view model to indicate a successful friend removal operation.
     * Sets the success flag to true, clears any existing errors, and notifies the view
     * that the friends list has been updated with the new data.
     *
     * @param user an instance of {@link RemoveFriendsOutputData} containing the updated friends list
     */
    @Override
    public void setPassView(RemoveFriendsOutputData user) {
        RemoveFriendsState state = removeFriendsViewModel.getState();
        state.setCurrentUser(user.getCurrentUser());

        state.setSuccess(true);
        state.setError(null);

        removeFriendsViewModel.setState(state);
        removeFriendsViewModel.firePropertyChanged("friendRemoved", null, "Friend successfully removed!");
    }

    /**
     * Updates the view model to indicate a failed friend removal operation.
     * Sets the success flag to false, records the error message, and notifies the view
     * of the failure with the provided error message.
     *
     * @param error a string describing the error that occurred during the removal operation
     */
    @Override
    public void setFailView(String error) {
        RemoveFriendsState state = removeFriendsViewModel.getState();

        state.setError(error);
        state.setSuccess(false);

        removeFriendsViewModel.setState(state);
        removeFriendsViewModel.firePropertyChanged("generalError", null, error);
    }
}