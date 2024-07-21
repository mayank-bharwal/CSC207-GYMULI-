package use_case.send_message;

public interface SendMessageOutputBoundary {
    /**
     *
     * @param message
     */
    void prepareSuccessView(SendMessageOutputData message);

    void prepareFailView(String error);
}
