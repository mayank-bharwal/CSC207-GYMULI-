package use_case.remove_friends;

public class RemoveFriendsInputData {
    final private String user1;
    final private String user2;

    public RemoveFriendsInputData(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }
    public String getUser2() {
        return user2;
    }
}
