package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    public User createUser(String username, String password, String bio, String programOfStudy, String profilePicture,
                                  List<String> interests, List<String> friends, LocalDateTime dateCreated) {
        return new User(username, password, bio, programOfStudy, profilePicture, interests, friends, dateCreated);
    }
}
