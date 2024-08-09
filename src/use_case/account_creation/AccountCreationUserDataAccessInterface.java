package use_case.account_creation;

import entity.User;

/**
 * Interface for data access related to account creation.
 * Defines methods for checking if an account exists and for saving a new user account.
 */
public interface AccountCreationUserDataAccessInterface {

    /**
     * Checks if an account with the specified username already exists.
     *
     * @param username The username of the account to check.
     * @return {@code true} if the account exists, {@code false} otherwise.
     */
    boolean AccountExists(String username);

    /**
     * Saves the data of a new user account.
     *
     * @param user The {@link User} object containing the details of the new account.
     */
    void save(User user);
}
