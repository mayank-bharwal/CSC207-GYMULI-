package use_case.login;

import entity.User;


/**
 * Interactor class that handles the process of logging in a user.
 * It implements the {@link LoginInputBoundary} interface and coordinates
 * the interaction between the input data, user data access, and output boundaries.
 */
public class LoginInteractor implements LoginInputBoundary {

    private final LoginOutputBoundary outputBoundary;
    private final LoginUserDataAccessInterface userDAO;

    /**
     * Constructs a new {@code LoginInteractor} with the specified output boundary
     * and user data access object.
     *
     * @param outputBoundary The output boundary to handle the success or failure view.
     * @param userDAO The data access object to interact with user data.
     */
    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDAO) {
        this.outputBoundary = outputBoundary;
        this.userDAO = userDAO;
    }

    /**
     * Executes the login process based on the provided input data.
     * <p>
     * This method checks if the user exists and if the provided password matches
     * the stored password for that user. If successful, it shows the success view;
     * otherwise, it shows the failure view with an appropriate error message.
     *
     * @param loginInputData The data required for logging in, including username and password.
     */
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

