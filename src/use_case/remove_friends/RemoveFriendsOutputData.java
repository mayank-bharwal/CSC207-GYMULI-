package use_case.remove_friends;

/**
 * This class represents the output data for the "Remove Friends" use case.
 * It contains information about the users involved in the friend removal process
 * and the status of the operation.
 */
public class RemoveFriendsOutputData {

    private final String currentUser;
    private final String friendRemoved;
    private final boolean failView;

    /**
     * Constructs a new {@code RemoveFriendsOutputData} instance with the specified details.
     *
     * @param currentUser    the username or ID of the user who is removing the friend.
     * @param friendRemoved  the username or ID of the friend who is being removed.
     * @param failView       {@code true} if the operation failed, {@code false} otherwise.
     */
    public RemoveFriendsOutputData(String currentUser, String friendRemoved, boolean failView) {
        this.currentUser = currentUser;
        this.friendRemoved = friendRemoved;
        this.failView = failView;
    }

    /**
     * Returns the username or ID of the user who is removing the friend.
     *
     * @return the username or ID of the current user.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the username or ID of the friend who is being removed.
     *
     * @return the username or ID of the friend being removed.
     */
    public String getFriendRemoved() {
        return friendRemoved;
    }

    /**
     * Returns whether the operation failed.
     *
     * @return {@code true} if the friend removal operation failed, {@code false} otherwise.
     */
    public boolean isFailView() {
        return failView;
    }
}