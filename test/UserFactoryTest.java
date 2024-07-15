import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserFactoryTest {
    private UserFactory userFactory;
    private String username;
    private String password;
    private String bio;
    private Integer age;
    private String program;
    private List<String> interests;
    private List<String> friends;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        username = "testUser";
        password = "testPassword";
        bio = "This is a test bio.";
        age = 21;
        program = "Computer Science";
        interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");
        interests.add("Gym");
        friends = new ArrayList<>();
        friends.add("Barry");
        date = LocalDateTime.now();
    }

    @Test
    void testCreateUser() {
        User user = userFactory.createUser(username, password, bio, age, program, interests, friends, date);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(bio, user.getBio());
        assertEquals(age, user.getAge());
        assertEquals(program, user.getProgramOfStudy());
        assertEquals(interests, user.getInterests());
        assertEquals(friends, user.getFriends());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithSpecialCharacters() {
        User user = userFactory.createUser("J@sm!ne", "p@$$w0rd", "(D!@#mo)", 21,
                "C0mput3r Sc!3nce", interests, friends, date);

        assertNotNull(user);
        assertEquals("J@sm!ne", user.getUsername());
        assertEquals("p@$$w0rd", user.getPassword());
        assertEquals("(D!@#mo)", user.getBio());
        assertEquals(21, user.getAge());
        assertEquals("C0mput3r Sc!3nce", user.getProgramOfStudy());
        assertEquals(interests, user.getInterests());
        assertEquals(friends, user.getFriends());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithSpecialCharacterInterests() {
        List<String> specialCharInterests = new ArrayList<>();
        specialCharInterests.add("C0d!ng");
        specialCharInterests.add("G@m!ng");

        User user = userFactory.createUser("Jasmine", "password", "Demo Bio", 21,
                "Computer Science", specialCharInterests, friends, date);

        assertNotNull(user);
        assertEquals("Jasmine", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("Demo Bio", user.getBio());
        assertEquals(21, user.getAge());
        assertEquals("Computer Science", user.getProgramOfStudy());
        assertEquals(specialCharInterests, user.getInterests());
        assertEquals(friends, user.getFriends());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithSpecialCharacterFriends() {
        List<String> specialCharFriends = new ArrayList<>();
        specialCharFriends.add("Charl!e");

        User user = userFactory.createUser("Jasmine", "password", "Demo Bio", 21,
                "Computer Science", interests, specialCharFriends, date);

        assertNotNull(user);
        assertEquals("Jasmine", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("Demo Bio", user.getBio());
        assertEquals(21, user.getAge());
        assertEquals("Computer Science", user.getProgramOfStudy());
        assertEquals(interests, user.getInterests());
        assertEquals(specialCharFriends, user.getFriends());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithEmptyStrings() {
        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("", "password", "(Demo)", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "", "(Demo)", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21, "",
                    interests, friends, date);
        });
    }

    @Test
    void testCreateUserWithWhitespaceStrings() {
        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser(" ", "password", "(Demo)", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", " ", "(Demo)", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", " ", 21, "Computer Science",
                    interests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21, " ",
                    interests, friends, date);
        });
    }

    @Test
    void testCreateUserWithEmptyInterestsAndFriends() {
        List<String> emptyInterests = Collections.emptyList();
        List<String> emptyFriends = Collections.emptyList();

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", emptyInterests, friends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", interests, emptyFriends, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", emptyInterests, emptyFriends, date);
        });
    }
}