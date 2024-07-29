import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserFactoryTest {
    private UserFactory userFactory;
    private String username;
    private String password;
    private String bio;
    private Integer age;
    private String program;
    private List<String> interests;
    private List<String> friends;
    private List<String> chats;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        userFactory = Mockito.mock(UserFactory.class);
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
        chats = new ArrayList<>();
        date = LocalDateTime.now();

        User mockUser = Mockito.mock(User.class);
        when(mockUser.getUsername()).thenReturn(username);
        when(mockUser.getPassword()).thenReturn(password);
        when(mockUser.getBio()).thenReturn(bio);
        when(mockUser.getAge()).thenReturn(age);
        when(mockUser.getProgramOfStudy()).thenReturn(program);
        when(mockUser.getInterests()).thenReturn(interests);
        when(mockUser.getFriends()).thenReturn(friends);
        when(mockUser.getChats()).thenReturn(chats);
        when(mockUser.getDateCreated()).thenReturn(date);

        when(userFactory.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenReturn(mockUser);

        when(userFactory.createUser(Mockito.eq(""), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenThrow(new IllegalArgumentException("Username cannot be empty"));

        when(userFactory.createUser(Mockito.anyString(), Mockito.eq(""), Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenThrow(new IllegalArgumentException("Password cannot be empty"));

        when(userFactory.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.eq(""), Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenThrow(new IllegalArgumentException("Bio cannot be empty"));

        when(userFactory.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
                Mockito.eq(""), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenThrow(new IllegalArgumentException("Program of Study cannot be empty"));
    }

    @Test
    void testCreateUser() {
        User user = userFactory.createUser(username, password, bio, age, program, interests, friends, chats, date);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(bio, user.getBio());
        assertEquals(age, user.getAge());
        assertEquals(program, user.getProgramOfStudy());
        assertEquals(interests, user.getInterests());
        assertEquals(friends, user.getFriends());
        assertEquals(chats, user.getChats());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithSpecialCharacters() {
        User user = userFactory.createUser("J@sm!ne", "p@$$w0rd", "(D!@#mo)", 21,
                "C0mput3r Sc!3nce", interests, friends, chats, date);

        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> bioCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> ageCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> programCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List> interestsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> friendsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> chatsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<LocalDateTime> dateCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(userFactory).createUser(usernameCaptor.capture(), passwordCaptor.capture(), bioCaptor.capture(),
                ageCaptor.capture(), programCaptor.capture(), interestsCaptor.capture(), friendsCaptor.capture(),
                chatsCaptor.capture(), dateCaptor.capture());

        assertNotNull(user);
        assertEquals("J@sm!ne", usernameCaptor.getValue());
        assertEquals("p@$$w0rd", passwordCaptor.getValue());
        assertEquals("(D!@#mo)", bioCaptor.getValue());
        assertEquals(21, ageCaptor.getValue());
        assertEquals("C0mput3r Sc!3nce", programCaptor.getValue());
        assertEquals(interests, interestsCaptor.getValue());
        assertEquals(friends, friendsCaptor.getValue());
        assertEquals(chats, chatsCaptor.getValue());
        assertEquals(date, dateCaptor.getValue());
    }

    @Test
    void testCreateUserWithSpecialCharacterInterests() {
        List<String> specialCharInterests = new ArrayList<>();
        specialCharInterests.add("C0d!ng");
        specialCharInterests.add("G@m!ng");

        User mockUser = createMockUser("Jasmine", "password", "Demo Bio", age, "Computer Science", specialCharInterests, friends, chats, date);

        when(userFactory.createUser("Jasmine", "password", "Demo Bio", age, "Computer Science", specialCharInterests, friends, chats, date))
                .thenReturn(mockUser);

        User user = userFactory.createUser("Jasmine", "password", "Demo Bio", age, "Computer Science", specialCharInterests, friends, chats, date);

        System.out.println("Expected: Jasmine");
        System.out.println("Actual: " + user.getUsername());

        assertNotNull(user);
        assertEquals("Jasmine", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("Demo Bio", user.getBio());
        assertEquals(age, user.getAge());
        assertEquals("Computer Science", user.getProgramOfStudy());
        assertEquals(specialCharInterests, user.getInterests());
        assertEquals(friends, user.getFriends());
        assertEquals(chats, user.getChats());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithSpecialCharacterFriends() {
        List<String> specialCharFriends = new ArrayList<>();
        specialCharFriends.add("Charl!e");

        User mockUser = createMockUser("Jasmine", "password", "Demo Bio", age, "Computer Science", interests, specialCharFriends, chats, date);

        when(userFactory.createUser("Jasmine", "password", "Demo Bio", age, "Computer Science", interests, specialCharFriends, chats, date))
                .thenReturn(mockUser);

        User user = userFactory.createUser("Jasmine", "password", "Demo Bio", age, "Computer Science", interests, specialCharFriends, chats, date);

        assertEquals("Jasmine", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("Demo Bio", user.getBio());
        assertEquals(age, user.getAge());
        assertEquals("Computer Science", user.getProgramOfStudy());
        assertEquals(interests, user.getInterests());
        assertEquals(specialCharFriends, user.getFriends());
        assertEquals(chats, user.getChats());
        assertEquals(date, user.getDateCreated());
    }

    @Test
    void testCreateUserWithEmptyStrings() {
        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("", "password", "(Demo)", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "", "(Demo)", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21, "",
                    interests, friends, chats, date);
        });
    }

    @Test
    void testCreateUserWithWhitespaceStrings() {
        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser(" ", "password", "(Demo)", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", " ", "(Demo)", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", " ", 21, "Computer Science",
                    interests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21, " ",
                    interests, friends, chats, date);
        });
    }

    @Test
    void testCreateUserWithEmptyInterestsAndFriends() {
        List<String> emptyInterests = Collections.emptyList();
        List<String> emptyFriends = Collections.emptyList();

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", emptyInterests, friends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", interests, emptyFriends, chats, date);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                    "Computer Science", emptyInterests, emptyFriends, chats, date);
        });
    }

    private User createMockUser(String username, String password, String bio, Integer age, String program,
                                List<String> interests, List<String> friends, List<String> chats, LocalDateTime date) {
        User mockUser = Mockito.mock(User.class);
        when(mockUser.getUsername()).thenReturn(username);
        when(mockUser.getPassword()).thenReturn(password);
        when(mockUser.getBio()).thenReturn(bio);
        when(mockUser.getAge()).thenReturn(age);
        when(mockUser.getProgramOfStudy()).thenReturn(program);
        when(mockUser.getInterests()).thenReturn(interests);
        when(mockUser.getFriends()).thenReturn(friends);
        when(mockUser.getChats()).thenReturn(chats);
        when(mockUser.getDateCreated()).thenReturn(date);
        return mockUser;
    }
}