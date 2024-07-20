package use_case.add_friends;

public interface AddFriendsUserDataAccessObject {

    public boolean userExists(String username);

    public void addFriend(String currentUser, String friend);

}
