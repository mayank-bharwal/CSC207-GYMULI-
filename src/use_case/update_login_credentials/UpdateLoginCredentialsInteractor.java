package use_case.update_login_credentials;

public class UpdateLoginCredentialsInteractor implements UpdateLoginCredentialsInputBoundary {
    final UpdateLoginCredentialsUserDataAccessInterface userDAO;
    final UpdateLoginCredentialsInputBoundary presenter;

    public UpdateLoginCredentialsInteractor(UpdateLoginCredentialsUserDataAccessInterface userDAO, UpdateLoginCredentialsInputBoundary presenter) {
        this.userDAO = userDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(UpdateLoginCredentialsInputData inputData) {


    }

}
