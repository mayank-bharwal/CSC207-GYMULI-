package interface_adapter.add_friends;

import interface_adapter.Login.LoginState;

public class AddFriendsState {

    private String currentUser = "";
    private String friend = "";
    private String friendError = null;

    public AddFriendsState(AddFriendsState copy) {
        currentUser = copy.currentUser;
        friend = copy.friend;
        friendError = copy.friendError;

    }

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
