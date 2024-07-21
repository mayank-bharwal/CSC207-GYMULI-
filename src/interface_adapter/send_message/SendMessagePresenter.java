package interface_adapter.send_message;

import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {
    private final SendMessageViewModel sendMessageViewModel;

    public SendMessagePresenter(SendMessageViewModel sendMessageViewModel) {
        this.sendMessageViewModel = sendMessageViewModel;
    }

    @Override
    public void prepareSuccessView(SendMessageOutputData message) {
        SendMessageState state = sendMessageViewModel.getState();
        state.setSuccess(true);
        state.setErrorMessage(null);
        sendMessageViewModel.setState(state);
        sendMessageViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        SendMessageState state = sendMessageViewModel.getState();
        state.setSuccess(false);
        state.setErrorMessage(error);
        sendMessageViewModel.setState(state);
        sendMessageViewModel.firePropertyChange();

    }
}
