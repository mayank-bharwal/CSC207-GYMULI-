import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.List;

// Implement JUnit testing

public class UserFactoryTest {
    public void testCreateUser() {
        String username = "testUser";
        String password = "testPassword";
        String bio = "This is a test user.";
        String programOfStudy = "Test Program";
        String profilePicture = "testPic.jpg";
        List<String> interests = new ArrayList<>();
        interests.add("Testing");
        List<String> friends = new ArrayList<>();
        friends.add("Friend1");

        User user = UserFactory.createUser(username, password, bio, programOfStudy, profilePicture, interests, friends);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(bio, user.getBio());
        assertEquals(programOfStudy, user.getProgramOfStudy());
        assertEquals(profilePicture, user.getProfilePicture());
        assertEquals(interests, user.getInterests());
        assertEquals(friends, user.getFriends());
    }
}