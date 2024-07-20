package use_case.add_friends;

public class AddFriendsOutputData {

    private final String currentUser;
    private final String friend;

    public AddFriendsOutputData(String currentUser, String friend){
        this.currentUser = currentUser;
        this.friend = friend;
    }

}
