package entity;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    public static User createUser(String username, String password, String bio, String programOfStudy, String profilePicture,
                                  List<String> interests, List<String> friends) {
        return new User(username, password, bio, programOfStudy, profilePicture, interests, friends);
    }
}
