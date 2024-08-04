package interface_adapter.refresh_user;

/**
 * Represents the state of a user refresh operation.
 * This class holds the data related to the user's refresh state, including the username and any error messages.
 */

public class RefreshUserState {
    private String username = "";
    private String usernameError = "";

    /**
     * Constructs a new RefreshUserState by copying the values from another RefreshUserState instance.
     *
     * @param copy The RefreshUserState instance to copy values from.
     */

    public RefreshUserState(RefreshUserState copy) {
        this.username = copy.username;
        this.usernameError = copy.usernameError;
    }

    /**
     * Constructs a new, empty RefreshUserState.
     */

    public RefreshUserState() {}

    /**
     * Returns the error message related to the username.
     *
     * @return The username error message.
     */

    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Sets the error message related to the username.
     *
     * @param usernameError The username error message to set.
     */

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set.
     */

    public void setUsername(String username) {
        this.username = username;
    }
}