package use_case.login;

public class LoginInteractor implements LoginInputBoundary{

    private LoginOutputBoundary outputBoundary;
    private LoginUserDataAccessInterface UserDAO;

    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface UserDAO) {
        this.outputBoundary = outputBoundary;
        this.UserDAO = UserDAO;
    }

    @Override

    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String inputPassword = loginInputData.getPassword();

        if (UserDAO.userExists(username)) {
            String password = UserDAO.getUser(username).getPassword();
            if (password.equals(inputPassword)) {
                LoginOutputData loginOutputData = new LoginOutputData(username);
                outputBoundary.showSuccessScreen(loginOutputData);
                System.out.println("Successfully logged in");
            } else {

                System.out.println("incorrect password");
                outputBoundary.showFailureScreen("Incorrect Password");
            }
        } else {
            System.out.println("User not found");
            outputBoundary.showFailureScreen("User not found");
        }



    }
}
