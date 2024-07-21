package use_case.add_friends;

public class AddFriendsInputData {

    final private String currentUser;
    final private String friend;

    public AddFriendsInputData(String currentUser, String friend) {
        this.currentUser = currentUser;
        this.friend = friend;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getFriend() {
        return friend;
    }
}
