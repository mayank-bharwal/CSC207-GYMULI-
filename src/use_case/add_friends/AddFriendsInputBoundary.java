package use_case.add_friends;

/**
 * Interface representing the input boundary for adding friends in the application.
 * It defines a method to execute the addition of a friend.
 */
public interface AddFriendsInputBoundary {

     /**
      * Executes the operation to add a friend using the provided input data.
      *
      * @param addFriendsInputData The data required to add a friend.
      */
     void execute(AddFriendsInputData addFriendsInputData);

}
