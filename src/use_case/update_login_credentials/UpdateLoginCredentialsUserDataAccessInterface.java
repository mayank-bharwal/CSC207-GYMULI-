package use_case.update_login_credentials;
import entity.User;

public interface UpdateLoginCredentialsUserDataAccessInterface {

    boolean correctUsername(String username);
    boolean correctPassword(String password);
    boolean passwordsMatch(String pwd1, String pwd2);
    User getUser(String username);
}
