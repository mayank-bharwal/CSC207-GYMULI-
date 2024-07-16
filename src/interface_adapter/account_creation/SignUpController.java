package interface_adapter.account_creation;

import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInputData;

import java.time.LocalDateTime;
import java.util.List;

public class SignUpController {

    private final AccountCreationInputBoundary accountCreationInputBoundary;

    public SignUpController(AccountCreationInputBoundary accountCreationInputBoundary) {
        this.accountCreationInputBoundary = accountCreationInputBoundary;
    }

    public void execute(String username, String password, String repeatPassword, String programOfStudy, List<String> interests, String bio, Integer age) {
        AccountCreationInputData inputData = new AccountCreationInputData(username, password, repeatPassword, programOfStudy, interests, bio, LocalDateTime.now(), age);
        accountCreationInputBoundary.execute(inputData);
    }


}
