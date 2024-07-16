package use_case.update_profile;
//user data access interface
import entity.User;

import java.util.List;

public interface UpdateProfileUserDataAccessInterface {
    boolean userExists(String username);

    void updateUser(String oldUsername,String username, String password, String bio, String programOfStudy, Integer age,
                List<String> interests);

    User getUser (String username);


}
