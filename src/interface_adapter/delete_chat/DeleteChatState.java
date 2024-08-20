package interface_adapter.delete_chat;

/**
 * Represents the state of a delete chat operation.
 * It holds information about the success of the operation and any error message that may have occurred.
 */
public class DeleteChatState {
    private boolean success;
    private String error;

    /**
     * Checks if the delete chat operation was successful.
     *
     * @return {@code true} if the operation was successful, {@code false} otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success status of the delete chat operation.
     *
     * @param success {@code true} if the operation was successful, {@code false} otherwise.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Retrieves the error message associated with the delete chat operation.
     *
     * @return The error message if the operation failed, {@code null} otherwise.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message for the delete chat operation.
     *
     * @param error The error message describing the reason for the failure.
     */
    public void setError(String error) {
        this.error = error;
    }
}
