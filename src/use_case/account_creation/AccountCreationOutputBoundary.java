package use_case.account_creation;

public interface AccountCreationOutputBoundary {
    /**
     *
     * @param user - User of program
     */
    void setPassView(AccountCreationOutputData user);

    /**
     *
     * @param error - error message
     */

    void setFailView(String error);
}
