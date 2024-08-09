package use_case.add_friends;

/**
 * Interface representing the data access object (DAO) for user-related operations
 * in the add friends use case. It defines methods to check user existence, add a friend,
 * and check if two users are already friends.
 */
public interface AddFriendsUserDataAccessObject {

    /**
     * Checks if a user with the specified username exists.
     *
     * @param username The username of the user to check.
     * @return {@code true} if the user exists; {@code false} otherwise.
     */
    public boolean userExists(String username);

    /**
     * Adds a friend to the current user's friend list.
     *
     * @param currentUser The username of the current user.
     * @param friend      The username of the friend to be added.
     */
    public void addFriend(String currentUser, String friend);

    /**
     * Checks if two users are already friends.
     *
     * @param user1 The username of the first user.
     * @param user2 The username of the second user.
     * @return {@code true} if the two users are friends; {@code false} otherwise.
     */
    public boolean isFriend(String user1, String user2);

}
