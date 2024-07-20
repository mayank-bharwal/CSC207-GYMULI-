package use_case.account_creation;

public interface AccountCreationOutputBoundary {
    /**
     *
     * @param user
     */
    void setPassView(AccountCreationOutputData user);

    void setFailView(String error);
}
