package interface_adapter.delete_chat;

import use_case.delete_chat.DeleteChatOutputBoundary;
import use_case.delete_chat.DeleteChatOutputData;
import interface_adapter.ViewModelManager;

import javax.swing.text.View;

/**
 * Presenter class responsible for handling the output of the delete chat use case.
 * It updates the {@link DeleteChatViewModel} with the result of the delete chat operation
 * and interacts with the {@link ViewModelManager} to notify the view of changes.
 */
public class DeleteChatPresenter implements DeleteChatOutputBoundary {
    private final DeleteChatViewModel deleteChatViewModel;
    private final ViewModelManager   viewModelManager;

    /**
     * Constructs a {@code DeleteChatPresenter} with the specified view model and view model manager.
     *
     * @param deleteChatViewModel The view model to be updated with the result of the delete chat operation.
     * @param viewModelManager    The manager responsible for handling view model changes.
     */
    public DeleteChatPresenter(DeleteChatViewModel deleteChatViewModel, ViewModelManager viewModelManager) {
        this.deleteChatViewModel = deleteChatViewModel;
        this.viewModelManager = viewModelManager;
    }

    /**
     * Updates the view model to reflect the success of the delete chat operation.
     * <p>
     * This method sets the success state in the view model, clears any previous error message,
     * and notifies the view model manager of the successful operation.
     * </p>
     *
     * @param chat The output data containing information about the delete chat operation.
     */
    @Override
    public void setPassView(DeleteChatOutputData chat) {
        DeleteChatState state = deleteChatViewModel.getState();
        state.setSuccess(true);
        state.setError(null);
        deleteChatViewModel.setState(state);
        deleteChatViewModel.firePropertyChanged();

        viewModelManager.firePropertyChanged("ChatDeleted", null, null);

    }

    /**
     * Updates the view model to reflect the failure of the delete chat operation.
     * <p>
     * This method sets the error message in the view model and marks the operation as unsuccessful.
     * The view model is then notified of the change.
     * </p>
     *
     * @param error A string containing the error message indicating the reason for the failure.
     */
    @Override
    public void setFailView(String error) {
        DeleteChatState state = deleteChatViewModel.getState();
        state.setError(error);
        state.setSuccess(false);
        deleteChatViewModel.setState(state);
        deleteChatViewModel.firePropertyChanged();

    }
}
