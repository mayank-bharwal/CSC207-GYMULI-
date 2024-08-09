package use_case.add_friends;

/**
 * Data class representing the output data for the add friends use case.
 * It contains the current user's identifier, the friend's identifier,
 * and a flag indicating whether the use case failed.
 */
public class AddFriendsOutputData {

    private final String currentUser;
    private final String friend;
    private boolean UseCaseFailed;

    /**
     * Constructs an {@code AddFriendsOutputData} object with the specified current user,
     * friend, and failure status.
     *
     * @param currentUser   The identifier of the current user.
     * @param friend        The identifier of the friend.
     * @param UseCaseFailed A boolean indicating whether the use case failed.
     */
    public AddFriendsOutputData(String currentUser, String friend,
                                boolean UseCaseFailed){
        this.currentUser = currentUser;
        this.friend = friend;
        this.UseCaseFailed = UseCaseFailed;
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
     * Returns whether the use case failed.
     *
     * @return {@code true} if the use case failed; {@code false} otherwise.
     */
    public String getFriend() {
        return friend;
    }
}
