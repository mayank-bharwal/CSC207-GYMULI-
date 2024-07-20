package use_case.add_friends;

public class AddFriendsOutputData {

    private final String currentUser;
    private final String friend;
    private boolean UseCaseFailed;

    public AddFriendsOutputData(String currentUser, String friend,
                                boolean UseCaseFailed){
        this.currentUser = currentUser;
        this.friend = friend;
        this.UseCaseFailed = UseCaseFailed;
    }

}
