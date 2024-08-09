package use_case.account_creation;

/**
 * Interface defining the output boundary for the account creation use case.
 * It provides methods for presenting the result of the account creation process
 * to the user interface, depending on whether the operation was successful or not.
 */
public interface AccountCreationOutputBoundary {

    /**
     * Sets the view to display the result of a successful account creation.
     * This method is called when the account creation process completes successfully.
     *
     * @param user An {@link AccountCreationOutputData} object containing the details of the newly created account.
     */
    void setPassView(AccountCreationOutputData user);

    /**
     * Sets the view to display an error message when the account creation process fails.
     * This method is called when there is an issue during account creation.
     *
     * @param error A string containing the error message to be displayed.
     */
    void setFailView(String error);
}
