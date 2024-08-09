package use_case.account_creation;

/**
 * Interface representing the input boundary for creating an account in the application.
 * It defines a method to execute an account creation.
 */
public interface AccountCreationInputBoundary {

    /**
     * Executes the operation to create an account using the provided input data.
     *
     * @param inputData The data required to create an account.
     */
    void execute(AccountCreationInputData inputData);

}
