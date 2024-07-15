package use_case.account_creation;

public class AccountCreationOutputData {
    private final String username;
    private String creationTime;
    private boolean failView;

    /**
     *
     * @param username
     * @param creationTime
     * @param failView
     */

    public AccountCreationOutputData(String username, String creationTime, boolean failView ) {
        this.username = username;
        this.creationTime = creationTime;
        this.failView = failView;

    }
    public String getUsername() {
        return username;
    }
    public String getCreationTime() {return creationTime; }
    public void setCreationTime(String creationTime) {this.creationTime = creationTime; }
}
