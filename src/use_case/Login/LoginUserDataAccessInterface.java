package use_case.Login;
import entity.User;

public interface LoginUserDataAccessInterface {
    User getUser(String username);
    boolean userExists(String username);


}
