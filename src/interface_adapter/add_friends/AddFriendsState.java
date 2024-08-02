package interface_adapter.add_friends;

import interface_adapter.Login.LoginState;

/**
 * The AddFriendsState class represents the state of the adding friends menu.
 * It holds the user inputs and any error messages.
 */
public class AddFriendsState {
    private String currentUser = "";
    private String friend = "";
    private String friendError = null;

    /**
     * Constructs a new AddFriendsState by copying the state from another AddFriendsState.
     *
     * @param copy the AddFriendsState to copy from
     */
    public AddFriendsState(AddFriendsState copy) {
        currentUser = copy.currentUser;
        friend = copy.friend;
        friendError = copy.friendError;

    }

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

    public String getFriendError() {
        return friendError;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public void setFriendError(String friendError) {
        this.friendError = friendError;
    }
}
