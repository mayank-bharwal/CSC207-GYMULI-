package interface_adapter.retrieve_chat;

import interface_adapter.ViewModel;

public class RetrieveChatViewModel extends ViewModel {
    private RetrieveChatState state = new RetrieveChatState();

    public RetrieveChatState getState() {
        return state;
    }

    public void setState(RetrieveChatState state) {
        RetrieveChatState oldState = this.state;
        this.state = state;
        firePropertyChanged("state", oldState, this.state);
    }

}

