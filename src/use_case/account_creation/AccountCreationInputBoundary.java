package use_case.account_creation;

public interface AccountCreationInputBoundary {
    void execute(AccountCreationInputData inputData);
    void change(AccountCreationInputData inputData);
}
