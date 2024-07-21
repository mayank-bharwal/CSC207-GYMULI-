package use_case.account_creation;

import entity.User;

public interface AccountCreationUserDataAccessInterface {
    /**
     * saves the data of the new account
     * @param username
     *
     */
    boolean AccountExists(String username);

    void save(User user);
}
