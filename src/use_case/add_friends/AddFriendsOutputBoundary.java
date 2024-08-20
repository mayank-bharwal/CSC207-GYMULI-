package use_case.add_friends;

/**
 * Interface representing the output boundary for adding friends in the application.
 * It defines methods to handle the success and failure cases of the add friends operation.
 */
public interface AddFriendsOutputBoundary {

    /**
     * Sets the view to display the success of adding a friend.
     *
     * @param outputData The output data containing information about the successful friend addition.
     */
    void setPassView(AddFriendsOutputData outputData);

    /**
     * Sets the view to display a failure message when adding a friend fails.
     *
     * @param msg The failure message explaining why the friend could not be added.
     */
    void setFailView(String msg);


}
