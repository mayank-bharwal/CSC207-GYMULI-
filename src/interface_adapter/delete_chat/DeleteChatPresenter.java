package interface_adapter.delete_chat;

import use_case.delete_chat.DeleteChatOutputBoundary;
import use_case.delete_chat.DeleteChatOutputData;
import interface_adapter.ViewModelManager;

import javax.swing.text.View;

public class DeleteChatPresenter implements DeleteChatOutputBoundary {
    private final DeleteChatViewModel deleteChatViewModel;
    private final ViewModelManager   viewModelManager;

    public DeleteChatPresenter(DeleteChatViewModel deleteChatViewModel, ViewModelManager viewModelManager) {
        this.deleteChatViewModel = deleteChatViewModel;
        this.viewModelManager = viewModelManager;
    }


    @Override
    public void setPassView(DeleteChatOutputData chat) {
        DeleteChatState state = deleteChatViewModel.getState();
        state.setSuccess(true);
        state.setError(null);
        deleteChatViewModel.setState(state);
        deleteChatViewModel.firePropertyChanged();

        deleteChatViewModel.firePropertyChanged("Chat Deleted", null, "Chat Successfully Deleted");
        viewModelManager.firePropertyChanged();
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
