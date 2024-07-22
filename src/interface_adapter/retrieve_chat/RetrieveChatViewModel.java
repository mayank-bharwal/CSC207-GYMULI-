package interface_adapter.retrieve_chat;

import interface_adapter.ViewModel;
import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;
import use_case.retrieve_chat.RetrieveChatOutputData;

public class RetrieveChatViewModel extends ViewModel {
    private RetrieveChatState state = new RetrieveChatState();
    private RetrieveChatInputBoundary retrieveChatInputBoundary;

    public RetrieveChatState getState() {
        return state;
    }

    public void setState(RetrieveChatState state) {
        RetrieveChatState oldState = this.state;
        this.state = state;
        firePropertyChanged("state", oldState, this.state);
    }

    public void triggerUpdate() {
        RetrieveChatInputData inputData = new RetrieveChatInputData(state.getChatName());
        retrieveChatInputBoundary.retrieveChat(inputData);
    }

    public void presentChat(RetrieveChatOutputData outputData) {
        RetrieveChatState newState = new RetrieveChatState();
        newState.setChatName(outputData.getChatName());
        newState.setUsers(outputData.getUsers());
        newState.setAllMessages(outputData.getAllMessages());
        setState(newState);
    }

    public void setRetrieveChatInteractor(RetrieveChatInputBoundary retrieveChatInputBoundary) {
        this.retrieveChatInputBoundary = retrieveChatInputBoundary;
    }
}



