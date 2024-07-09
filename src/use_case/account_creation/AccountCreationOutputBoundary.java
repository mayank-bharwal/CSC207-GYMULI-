package use_case.account_creation;

public interface AccountCreationOutputBoundary {
    void setPassView(AccountCreationOutputData user);

    void setFailView(String error);
}
