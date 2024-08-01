package interface_adapter.delete_chat;

import use_case.delete_chat.DeleteChatOutputBoundary;
import use_case.delete_chat.DeleteChatOutputData;

public class DeleteChatPresenter implements DeleteChatOutputBoundary {
    private final DeleteChatViewModel deleteChatViewModel;


    public DeleteChatPresenter(DeleteChatViewModel deleteChatViewModel) {
        this.deleteChatViewModel = deleteChatViewModel;
    }


    @Override
    public void setPassView(DeleteChatOutputData chat) {
        DeleteChatState state = deleteChatViewModel.getState();
        state.setSuccess(true);
        state.setError(null);
        deleteChatViewModel.setState(state);
        deleteChatViewModel.firePropertyChanged();
    }

    @Override
    public void setFailView(String error) {
        DeleteChatState state = deleteChatViewModel.getState();
        state.setError(error);
        state.setSuccess(false);
        deleteChatViewModel.setState(state);
        deleteChatViewModel.firePropertyChanged();

    }
}
