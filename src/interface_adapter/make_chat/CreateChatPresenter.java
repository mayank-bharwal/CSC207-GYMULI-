package interface_adapter.make_chat;

import use_case.make_chat.MakeChatOutputBoundary;
import use_case.make_chat.MakeChatOutputData;

public class CreateChatPresenter implements MakeChatOutputBoundary {
    private final CreateChatViewModel createChatViewModel;

    public CreateChatPresenter(CreateChatViewModel createChatViewModel) {
        this.createChatViewModel = createChatViewModel;
    }

    @Override
    public void setPassView(MakeChatOutputData chat) {
        CreateChatState state = createChatViewModel.getState();
        state.setSuccess(true);
        state.setError(null);
        createChatViewModel.setState(state);
        createChatViewModel.firePropertyChanged();
    }

    @Override
    public void setFailView(String error) {
        CreateChatState state = createChatViewModel.getState();
        state.setError(error);
        state.setSuccess(false);
        createChatViewModel.setState(state);
        createChatViewModel.firePropertyChanged();
    }
}
