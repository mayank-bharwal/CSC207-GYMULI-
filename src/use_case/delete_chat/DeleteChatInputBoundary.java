package use_case.delete_chat;

/**
 * Interface for the input boundary of the delete chat use case.
 * It defines the method for deleting a chat based on the provided input data.
 */
public interface DeleteChatInputBoundary {

    /**
     * Deletes a chat using the provided input data.
     *
     * @param inputData The data required to delete a chat, encapsulated in a {@link DeleteChatInputData} object.
     */
    void deleteChat(DeleteChatInputData inputData);
}
