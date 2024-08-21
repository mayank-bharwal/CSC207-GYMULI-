package use_case.update_profile;
//user data access interface
import entity.User;

import java.util.List;


/**
 * Interface for accessing and updating user data in the Update Profile use case.
 * Defines methods for checking user existence, retrieving user data, and updating user profiles.
 */

public interface UpdateProfileUserDataAccessInterface {

    /**
     * Checks if a user with the given username exists in the system.
     *
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */

    boolean userExists(String username);

    /**
     * Updates the profile of the user in the system with the new details provided.
     *
     * @param oldUsername    The current username of the user.
     * @param username       The updated username.
     * @param password       The updated password.
     * @param bio            The updated bio.
     * @param programOfStudy The updated program of study.
     * @param age            The updated age.
     * @param interests      The updated list of interests.
     */

    void updateUser(String oldUsername,String username, String password, String bio, String programOfStudy, Integer age,
                List<String> interests);

    /**
     * Retrieves the user data associated with the given username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object representing the user, or null if no user exists with the given username.
     */

    User getUser (String username);


}
