package use_case.login;

import entity.User;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginOutputBoundary outputBoundary;
    private final LoginUserDataAccessInterface userDAO;

    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDAO) {
        this.outputBoundary = outputBoundary;
        this.userDAO = userDAO;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String inputPassword = loginInputData.getPassword();

        if (userDAO.userExists(username)) {
            User user = userDAO.getUser(username);
            if (user.getPassword().equals(inputPassword)) {
                LoginOutputData loginOutputData = new LoginOutputData(user);
                outputBoundary.showSuccessScreen(loginOutputData);
                System.out.println("Successfully logged in");
            } else {
                System.out.println("Incorrect password");
                outputBoundary.showFailureScreen("Incorrect Password");
            }
        } else {
            System.out.println("User not found");
            outputBoundary.showFailureScreen("User not found");
        }
    }
}

