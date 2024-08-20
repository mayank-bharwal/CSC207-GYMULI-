package use_case.make_chat;

/**
 * Interface representing the input boundary for the "Make Chat" use case.
 * It provides an abstraction for the operation of creating a new chat session.
 */
public interface MakeChatInputBoundary {

    /**
     * Initiates the creation of a new chat session using the specified input data.
     *
     * @param inputData the data required to create a new chat session,
     *                  which typically includes the participants and any initial settings or messages.
     */
    void makeChat(MakeChatInputData inputData);
}