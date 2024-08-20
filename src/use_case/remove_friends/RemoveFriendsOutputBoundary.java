package use_case.remove_friends;

/**
 * Interface representing the output boundary for the "Remove Friends" use case.
 * It defines methods for presenting the result of the friend removal process,
 * whether it is successful or results in an error.
 */
public interface RemoveFriendsOutputBoundary {

    /**
     * Presents the successful removal of a friend.
     *
     * @param user the data representing the outcome of the friend removal process,
     *             including the users involved and the status of the operation.
     */
    void setPassView(RemoveFriendsOutputData user);

    /**
     * Presents the failure of the friend removal process with an appropriate error message.
     *
     * @param error the error message explaining why the friend removal process failed.
     */
    void setFailView(String error);
}