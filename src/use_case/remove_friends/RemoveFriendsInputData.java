package use_case.remove_friends;

/**
 * This class represents the input data required for the "Remove Friends" use case.
 * It contains the information about the users involved in the friend removal process.
 */
public class RemoveFriendsInputData {

    private final String user1;
    private final String user2;

    /**
     * Constructs a new {@code RemoveFriendsInputData} instance with the specified users.
     *
     * @param user1 the username or ID of the first user.
     * @param user2 the username or ID of the second user to be removed from the friend list.
     */
    public RemoveFriendsInputData(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Returns the username or ID of the first user.
     *
     * @return the username or ID of the first user.
     */
    public String getUser1() {
        return user1;
    }

    /**
     * Returns the username or ID of the second user to be removed from the friend list.
     *
     * @return the username or ID of the second user.
     */
    public String getUser2() {
        return user2;
    }
}