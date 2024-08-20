package use_case.remove_friends;

/**
 * Interface representing the input boundary for the "Remove Friends" use case.
 * It defines the method for initiating the process of removing a friend.
 */
public interface RemoveFriendsInputBoundary {

    /**
     * Initiates the process of removing a friend based on the provided input data.
     *
     * @param inputData the data required to remove a friend, such as the usernames involved.
     */
    void removeFriend(RemoveFriendsInputData inputData);
}