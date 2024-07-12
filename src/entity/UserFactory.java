package entity;
update_profile_use_case
import java.time.LocalDateTime;
main
import java.util.ArrayList;
import java.util.List;

public class UserFactory {
update_profile_use_case
    public static User createUser(String username, String password, String bio, String programOfStudy, String profilePicture,
                                  List<String> interests, List<String> friends) {
        return new User(username, password, bio, programOfStudy, profilePicture, interests, friends);

    public User createUser(String username, String password, String bio, String programOfStudy, String profilePicture,
                                  List<String> interests, List<String> friends, LocalDateTime dateCreated) {
        return new User(username, password, bio, programOfStudy, profilePicture, interests, friends, dateCreated);
 main
    }
}
