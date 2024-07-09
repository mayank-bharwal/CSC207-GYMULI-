package use_case.Login;

public interface LoginOutputBoundary {
    void showSuccessScreen(LoginOutputData user);

    void showFailureScreen(String error);

}
