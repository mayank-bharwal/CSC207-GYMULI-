package interface_adapter.account_creation;

import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInputData;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for handling user signup.
 */
public class SignupController {
    private final AccountCreationInputBoundary accountCreationInputBoundary;
    /**
     * Constructor for SignupController.
     *
     * @param accountCreationInputBoundary the input boundary for the account creation use case
     */
    public SignupController(AccountCreationInputBoundary accountCreationInputBoundary) {
        this.accountCreationInputBoundary = accountCreationInputBoundary;
    }
    /**
     * Executes the account creation process.
     *
     * @param username       the username of the new account
     * @param password       the password for the new account
     * @param repeatPassword the password repeated for confirmation
     * @param programOfStudy the program of study of the user
     * @param interests      the list of interests of the user
     * @param bio            the bio of the user
     * @param age            the age of the user
     * @throws IllegalArgumentException if validation fails
     */

    public void execute(String username, String password, String repeatPassword, String programOfStudy, List<String> interests, String bio, Integer age) {
        AccountCreationInputData inputData = new AccountCreationInputData(username, password, repeatPassword, programOfStudy, interests, bio, LocalDateTime.now(), age);
        accountCreationInputBoundary.execute(inputData);
    }
}