package use_case.login;
import entity.User;

/**
 * Interface for accessing user data related to the login process.
 * This interface provides methods to retrieve user information and check
 * if a user exists in the data store.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Retrieves the user associated with the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The {@code User} object corresponding to the specified username.
     */
    User getUser(String username);

    /**
     * Checks if a user with the specified username exists in the data store.
     *
     * @param username The username to check for existence.
     * @return {@code true} if the user exists; {@code false} otherwise.
     */
    boolean userExists(String username);


}
