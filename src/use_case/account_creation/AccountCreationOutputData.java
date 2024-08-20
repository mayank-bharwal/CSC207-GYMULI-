package use_case.account_creation;


/**
 * Data class representing the output data for the account creation process.
 * It contains information about the created account, including the username,
 * the time of creation, and a flag indicating whether the operation failed.
 */
public class AccountCreationOutputData {
    private final String username;
    private String creationTime;
    private boolean failView;

    /**
     * Constructs an {@code AccountCreationOutputData} object with the specified details.
     *
     * @param username     The username of the newly created account.
     * @param creationTime The time when the account was created.
     * @param failView     A flag indicating whether the account creation process failed.
     */
    public AccountCreationOutputData(String username, String creationTime, boolean failView ) {
        this.username = username;
        this.creationTime = creationTime;
        this.failView = failView;

    }

    /**
     * Returns the username of the created account.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the time when the account was created.
     *
     * @return The creation time.
     */
    public String getCreationTime() {return creationTime; }

    /**
     * Sets the time when the account was created.
     *
     * @param creationTime The creation time to set.
     */
    public void setCreationTime(String creationTime) {this.creationTime = creationTime; }
}
