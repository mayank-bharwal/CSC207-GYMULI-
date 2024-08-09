package use_case.add_friends;

/**
 * Data class representing the input data required to add a friend.
 * It contains the current user's identifier and the friend's identifier.
 */
public class AddFriendsInputData {

    final private String currentUser;
    final private String friend;

    /**
     * Constructs an {@code AddFriendsInputData} object with the specified current user and friend.
     *
     * @param currentUser The identifier of the current user.
     * @param friend      The identifier of the friend to be added.
     */
    public AddFriendsInputData(String currentUser, String friend) {
        this.currentUser = currentUser;
        this.friend = friend;
    }

    /**
     * Returns the identifier of the current user.
     *
     * @return The current user's identifier.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the identifier of the friend to be added.
     *
     * @return The friend's identifier.
     */
    public String getFriend() {
        return friend;
    }
}
