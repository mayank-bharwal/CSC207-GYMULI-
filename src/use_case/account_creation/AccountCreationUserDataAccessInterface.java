package use_case.account_creation;

import entity.User;

public interface AccountCreationUserDataAccessInterface {
    /**
     *
     * @param username
     * @return
     */
    boolean AccountExists(String username);

    void save(User user);
}
