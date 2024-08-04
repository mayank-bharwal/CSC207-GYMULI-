package use_case.refresh_user;

import entity.User;

public interface RefreshUserDataAccessInterface {
    boolean userExists(String user);

    User getUser(String user);
}
