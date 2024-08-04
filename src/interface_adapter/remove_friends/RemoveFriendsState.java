package interface_adapter.remove_friends;

import java.util.ArrayList;

public class RemoveFriendsState {
    private String currentUser;
    private ArrayList<String> friends;
    private String friendRemoved;
    private String error;
    private boolean success;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getFriendRemoved() {
        return friendRemoved;
    }

    public void setFriendRemoved(String friendRemoved) {
        this.friendRemoved = friendRemoved;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
