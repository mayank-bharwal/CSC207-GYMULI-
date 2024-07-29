package interface_adapter.retrieve_chat;

import use_case.retrieve_chat.RetrieveChatOutputBoundary;
import use_case.retrieve_chat.RetrieveChatOutputData;

public class RetrieveChatPresenter implements RetrieveChatOutputBoundary {
    private final RetrieveChatViewModel retrieveChatViewModel;

    public RetrieveChatPresenter(RetrieveChatViewModel retrieveChatViewModel) {
        this.retrieveChatViewModel = retrieveChatViewModel;
    }

    @Override
    public void prepareSuccessView(RetrieveChatOutputData chatInfo) {
        RetrieveChatState state = new RetrieveChatState();
        state.setChatName(chatInfo.getChatName());
        state.setUsers(chatInfo.getUsers());
        state.setNoOfMembers(chatInfo.getNoOfmembers());
        state.setAllMessages(chatInfo.getAllMessages());
        retrieveChatViewModel.setState(state);
    }

    @Override
    public void prepareFailView(String error) {
        RetrieveChatState state = new RetrieveChatState();
        state.setError(error);
        retrieveChatViewModel.setState(state);
    }

    @Override
    public void presentChat(RetrieveChatOutputData outputData) {
        retrieveChatViewModel.presentChat(outputData);
    }
}
