package use_case.make_chat;

/**
 * Interface representing the output boundary for the "Make Chat" use case.
 * It defines the methods for presenting the outcome of the chat creation process,
 * either as a successful operation or as a failure with an error message.
 */
public interface MakeChatOutputBoundary {

    /**
     * Presents the successful creation of a chat session.
     *
     * @param chat the data representing the created chat, which includes details
     *             such as the chat entity and any additional relevant information.
     */
    void setPassView(MakeChatOutputData chat);

    /**
     * Presents the failure of the chat creation process with an appropriate error message.
     *
     * @param error the error message explaining why the chat creation process failed.
     */
    void setFailView(String error);
}