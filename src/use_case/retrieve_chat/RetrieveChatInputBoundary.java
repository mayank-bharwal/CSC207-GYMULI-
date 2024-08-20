package use_case.retrieve_chat;

/**
 * Interface representing the input boundary for the "Retrieve Chat" use case.
 * It defines the method for initiating the process of retrieving a chat based on input data.
 */
public interface RetrieveChatInputBoundary {

    /**
     * Initiates the process of retrieving a chat using the provided input data.
     *
     * @param inputData the data required to retrieve a chat, including identifiers such as chat ID or user details.
     */
    void retrieveChat(RetrieveChatInputData inputData);
}
