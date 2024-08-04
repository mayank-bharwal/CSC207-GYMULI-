package use_case.remove_friends;

public interface RemoveFriendsUserDataAccessInterface {
    boolean isFriend(String user1, String user2);

    void remove(String user1, String user2);
}
