package use_case.edit_friends;

import entity.User;

public interface EditFriendsUserDataAccessInterface {
    boolean userExists(String username);
    void save(User user);
    User getUser(String username);
}
