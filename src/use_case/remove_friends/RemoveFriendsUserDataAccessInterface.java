package use_case.remove_friends;

/**
 * Interface for accessing and manipulating user data related to friendships in the "Remove Friends" use case.
 * It provides methods to check if users are friends and to remove a friendship.
 */
public interface RemoveFriendsUserDataAccessInterface {

    /**
     * Checks if there is a friendship between the specified users.
     *
     * @param user1 the username or ID of the first user.
     * @param user2 the username or ID of the second user.
     * @return {@code true} if the specified users are friends, {@code false} otherwise.
     */
    boolean isFriend(String user1, String user2);

    /**
     * Removes the friendship between the specified users.
     *
     * @param user1 the username or ID of the first user.
     * @param user2 the username or ID of the second user.
     */
    void remove(String user1, String user2);
}