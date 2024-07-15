import entity.Requests;
import entity.User;
import entity.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestsTest {
    private User fromUser;
    private User toUser;
    private UserFactory userFactory;
    private Requests request;

    @BeforeEach
    void setUp() {
        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");

        List<String> friends = new ArrayList<>();
        friends.add("Barry");

        LocalDateTime dateCreated = LocalDateTime.now();

        fromUser = userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                "Computer Science", interests, friends, dateCreated);
        toUser = userFactory.createUser("Charlie", "password123", "Always Sunny", 22,
                "Law", interests, friends, dateCreated);

        request = new Requests(fromUser, toUser);
    }

    @Test
    void testGetFrom() {
        assertEquals(fromUser, request.getFrom());
    }

    @Test
    void testGetTo() {
        assertEquals(toUser, request.getTo());
    }

    @Test
    void testSetFrom() {
        List<String> newInterests = new ArrayList<>();
        newInterests.add("Coding");
        newInterests.add("Gaming");

        User newFromUser = userFactory.createUser("Barry", "newPassword", "Seeking Direction",
                23, "Arts", newInterests, new ArrayList<>(), LocalDateTime.now());
        request.setFrom(newFromUser);
        assertEquals(newFromUser, request.getFrom());
    }

    @Test
    void testSetTo() {
        List<String> newInterests = new ArrayList<>();
        newInterests.add("Hiking");
        newInterests.add("Traveling");

        User newToUser = userFactory.createUser("Alice", "anotherPassword", "In Wonderland",
                24, "Chemistry", newInterests, new ArrayList<>(), LocalDateTime.now());
        request.setTo(newToUser);
        assertEquals(newToUser, request.getTo());
    }

    @Test
    void testSetFromToMultipleTimes() {
        User firstUser = userFactory.createUser("Jasmine", "password",
                "(Demo)", 21,  "Computer Science", new ArrayList<>(), new ArrayList<>(),
                LocalDateTime.now());
        User secondUser = userFactory.createUser("Imogen", "secondPassword", "Details", 21,
                "Music", new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());

        request.setFrom(firstUser);
        assertEquals(firstUser, request.getFrom());

        request.setFrom(secondUser);
        assertEquals(secondUser, request.getFrom());

        request.setTo(firstUser);
        assertEquals(firstUser, request.getTo());

        request.setTo(secondUser);
        assertEquals(secondUser, request.getTo());
    }

    @Test
    void testSetFromToNull() {
        assertThrows(NullPointerException.class, () -> {
            request.setFrom(null);
        });
    }

    @Test
    void testSetToToNull() {
        assertThrows(NullPointerException.class, () -> {
            request.setTo(null);
        });
    }
}
