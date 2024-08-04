package interface_adapter.add_friends;

/**
 * The AddFriendsState class represents the state of the adding friends menu.
 * It holds the user inputs and any error messages.
 */
public class AddFriendsState {
    private String currentUser = "";
    private String friend = "";
    private String error = null;
    /**
     * Gets the current user.
     *
     * @return the current user
     */
    public String getCurrentUser() {
        return currentUser;
    }

    public String getFriend() {
        return friend;
    }

    public String getError() {
        return error;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public void setError(String error) {
        this.error = error;
    }
}
