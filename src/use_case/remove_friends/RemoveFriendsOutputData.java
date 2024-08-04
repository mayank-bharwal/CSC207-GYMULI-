package use_case.remove_friends;

public class RemoveFriendsOutputData {
    private final String friendRemoved;
    private boolean failView;


    public RemoveFriendsOutputData(String friendRemoved, boolean failView) {
        this.friendRemoved = friendRemoved;
        this.failView = failView;
    }

    public String getFriendRemoved() {
        return friendRemoved;
    }
    public boolean isFailView() {
        return failView;
    }
}
