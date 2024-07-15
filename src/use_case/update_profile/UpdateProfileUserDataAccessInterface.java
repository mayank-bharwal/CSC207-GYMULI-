package use_case.update_profile;
//user data access interface
import entity.User;

public interface UpdateProfileUserDataAccessInterface {
    boolean existsByName(String username);

    void update(User user);
}
