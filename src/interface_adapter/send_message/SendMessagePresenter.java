package interface_adapter.send_message;

import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

/**
 * The SendMessagePresenter class handles the presentation logic for sending a message.
 * It updates the view model based on the result of the sending message process.
 */
public class SendMessagePresenter implements SendMessageOutputBoundary {
    private final SendMessageViewModel sendMessageViewModel;

    /**
     * Constructs a new SignupPresenter with the specified view model manager, signup view model, and login view model.
     * @param sendMessageViewModel - view model for sending a message
     */
    public SendMessagePresenter(SendMessageViewModel sendMessageViewModel) {
        this.sendMessageViewModel = sendMessageViewModel;
    }

    /**
     *  Sets the pass view for successful sending message.
     *  Updates the sendmessage view model state, fires property change events, and activates the sendmessage view.
     *
     * @param message the outputdata containing message information
     */
    @Override
    public void prepareSuccessView(SendMessageOutputData message) {
        SendMessageState state = sendMessageViewModel.getState();
        state.setSuccess(true);
        state.setErrorMessage(null);
        sendMessageViewModel.setState(state);
        sendMessageViewModel.firePropertyChange();
    }

    /**
     * Sets the fail view for unsuccessful sending message.
     * Updates the sendmessage view model state with the error message and fires property change events.
     *
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        SendMessageState state = sendMessageViewModel.getState();
        state.setSuccess(false);
        state.setErrorMessage(error);
        sendMessageViewModel.setState(state);
        sendMessageViewModel.firePropertyChange();

    }
}
