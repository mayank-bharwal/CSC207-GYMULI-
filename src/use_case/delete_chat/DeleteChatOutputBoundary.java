package use_case.delete_chat;


/**
 * Interface for the output boundary of the delete chat use case.
 * It defines methods for communicating the results of the delete chat operation
 * to the presentation layer.
 */
public interface DeleteChatOutputBoundary {

    /**
     * Sets the view to reflect a successful chat deletion.
     *
     * @param chat The output data encapsulating the details of the deleted chat,
     *             encapsulated in a {@link DeleteChatOutputData} object.
     */
    void setPassView(DeleteChatOutputData chat);

    /**
     * Sets the view to reflect a failure in chat deletion.
     *
     * @param error The error message describing why the chat deletion failed.
     */
    void setFailView(String error);
}
