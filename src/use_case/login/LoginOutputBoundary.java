package use_case.login;

public interface LoginOutputBoundary {
    void showSuccessScreen(LoginOutputData user);

    void showFailureScreen(String error);

}
