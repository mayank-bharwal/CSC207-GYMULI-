package use_case.retrieve_chat;

/**
 * Interface for the output boundary of the "Retrieve Chat" use case.
 * It defines methods for preparing and presenting the results of chat retrieval operations,
 * including success and failure scenarios.
 */
public interface RetrieveChatOutputBoundary {

    /**
     * Prepares the view to display the details of a successfully retrieved chat.
     *
     * @param chatInfo the data representing the retrieved chat, including chat details and messages.
     */
    void prepareSuccessView(RetrieveChatOutputData chatInfo);

    /**
     * Prepares the view to display an error message when the chat retrieval fails.
     *
     * @param error the error message explaining why the chat retrieval failed.
     */
    void prepareFailView(String error);

    /**
     * Presents the retrieved chat details using the provided output data.
     *
     * @param outputData the data representing the retrieved chat, including details and messages.
     */
    void presentChat(RetrieveChatOutputData outputData);
}