package interface_adapter.send_message;

/**
 * The SendMessageState class represents the state of the message.
 * It holds the user inputs and any error messages.
 */
public class SendMessageState {
    private boolean success;
    private String errorMessage;

    /**
     * gets whether the message was sent successfully
     * @return whether sent successfully
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
