package use_case.account_creation;

public class AccountCreationOutputData {
    private final String username;
    private String creationTime;
    private boolean failView;

    /**
     *
     * @param username - user identifier
     * @param creationTime - time of creation
     * @param failView -
     */

    public AccountCreationOutputData(String username, String creationTime, boolean failView ) {
        this.username = username;
        this.creationTime = creationTime;
        this.failView = failView;

    }

    /**
     *
     * @return the username and time of creation of account
     */
    public String getUsername() {
        return username;
    }
    public String getCreationTime() {return creationTime; }
    public void setCreationTime(String creationTime) {this.creationTime = creationTime; }
}
