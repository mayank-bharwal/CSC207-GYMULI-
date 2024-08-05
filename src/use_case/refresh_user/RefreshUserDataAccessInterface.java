package use_case.refresh_user;

import entity.User;

/**
 * Interface for accessing user data related to the refresh user use case.
 * This interface defines the methods required to check for user existence and to retrieve user information.
 */
public interface RefreshUserDataAccessInterface {

    /**
     * Checks if a user exists in the data source.
     *
     * @param user The username to check for existence.
     * @return {@code true} if the user exists, {@code false} otherwise.
     */
    boolean userExists(String user);

    /**
     * Retrieves the user information for the specified username.
     *
     * @param user The username of the user to retrieve.
     * @return The User object containing the user's information.
     */
    User userUpdate(String user);
}
