package use_case.account_creation;

public class AccountCreationOutputData {
    private final String username;

    public AccountCreationOutputData(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
