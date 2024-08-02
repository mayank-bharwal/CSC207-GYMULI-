import entity.Requests;
import entity.User;
import entity.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestsTest {
    private User fromUser;
    private User toUser;
    private UserFactory userFactory;
    private Requests request;

    @BeforeEach
    void setUp() {
        userFactory = Mockito.mock(UserFactory.class);

        fromUser = createMockUser("Jasmine", "password", "(Demo)", 21, "Computer Science");
        toUser = createMockUser("Imogen", "secondPassword", "Details", 21, "Music");

        Mockito.when(userFactory.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyInt(), Mockito.anyString(), Mockito.anyList(), Mockito.anyList(),
                        Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenReturn(fromUser)
                .thenReturn(toUser);

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

        List<String> newFriends = new ArrayList<>();
        List<String> newChats = new ArrayList<>();

        User newFromUser = userFactory.createUser("Barry", "newPassword", "Seeking Direction",
                23, "Arts", newInterests, newFriends, newChats, LocalDateTime.now());
        request.setFrom(newFromUser);
        assertEquals(newFromUser, request.getFrom());
    }

    @Test
    void testSetTo() {
        List<String> newInterests = new ArrayList<>();
        newInterests.add("Hiking");
        newInterests.add("Traveling");

        List<String> newFriends = new ArrayList<>();
        List<String> newChats = new ArrayList<>();

        User newToUser = userFactory.createUser("Alice", "anotherPassword", "In Wonderland",
                24, "Chemistry", newInterests, newFriends, newChats, LocalDateTime.now());
        request.setTo(newToUser);
        assertEquals(newToUser, request.getTo());
    }

    @Test
    void testSetFromToMultipleTimes() {
        User firstUser = userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                "Computer Science", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());
        User secondUser = userFactory.createUser("Imogen", "secondPassword", "Details", 21,
                "Music", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), LocalDateTime.now());

        request.setFrom(firstUser);
        assertEquals(firstUser, request.getFrom());

        request.setFrom(secondUser);
        assertEquals(secondUser, request.getFrom());

        request.setTo(firstUser);
        assertEquals(firstUser, request.getTo());

        request.setTo(secondUser);
        assertEquals(secondUser, request.getTo());
    }

    private User createMockUser(String username, String password, String bio, Integer age, String program) {
        User mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getUsername()).thenReturn(username);
        Mockito.when(mockUser.getPassword()).thenReturn(password);
        Mockito.when(mockUser.getBio()).thenReturn(bio);
        Mockito.when(mockUser.getAge()).thenReturn(age);
        Mockito.when(mockUser.getProgramOfStudy()).thenReturn(program);
        Mockito.when(mockUser.getInterests()).thenReturn(new ArrayList<>());
        Mockito.when(mockUser.getFriends()).thenReturn(new ArrayList<>());
        Mockito.when(mockUser.getChats()).thenReturn(new ArrayList<>());
        Mockito.when(mockUser.getDateCreated()).thenReturn(LocalDateTime.now());
        return mockUser;
    }
}
