package use_case.update_profile;

import entity.User;

public interface UpdateProfileUserDataAccessInterface {
    boolean existsByName(String username);

    void save(User user);
}
