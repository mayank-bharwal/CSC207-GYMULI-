package use_case.remove_friends;

public class RemoveFriendsOutputData {
    private final String currentUser;
    private final String friendRemoved;
    private boolean failView;


    public RemoveFriendsOutputData(String cuurentUser, String friendRemoved, boolean failView) {
        this.currentUser = cuurentUser;
        this.friendRemoved = friendRemoved;
        this.failView = failView;
    }

    public String getCurrentUser() {
        return currentUser;
    }
    public String getFriendRemoved() {
        return friendRemoved;
    }
    public boolean isFailView() {
        return failView;
    }
}
