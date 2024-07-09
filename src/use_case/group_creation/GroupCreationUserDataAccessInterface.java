package use_case.group_creation;

import entity.User;

public interface GroupCreationUserDataAccessInterface {
    boolean GroupExists(String groupname);
    User GetUser(String groupname);
}
