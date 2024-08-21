package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private CommonUser user;
    private List<String> interests;
    private List<String> friends;
    private List<String> chats;
    private LocalDateTime dateCreated;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        interests = new ArrayList<>(Arrays.asList("Reading", "Music", "Gym"));
        friends = new ArrayList<>(Arrays.asList("Barry"));
        chats = new ArrayList<>();
        dateCreated = LocalDateTime.now();

        user = new CommonUser("Jasmine", "password", "(Demo)", 21, "Computer Science",
                interests, friends, chats, dateCreated);
    }

    /**
     * Tests the getUsername method.
     */
    @Test
    void getUsername() {
        assertEquals("Jasmine", user.getUsername());
    }

    /**
     * Tests the getPassword method.
     */
    @Test
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    /**
     * Tests the getBio method.
     */
    @Test
    void getBio() {
        assertEquals("(Demo)", user.getBio());
    }

    /**
     * Tests the getProgramOfStudy method.
     */
    @Test
    void getProgramOfStudy() {
        assertEquals("Computer Science", user.getProgramOfStudy());
    }

    /**
     * Tests the getAge method.
     */
    @Test
    void getAge() {
        assertEquals(21, user.getAge());
    }

    /**
     * Tests the getInterests method.
     */
    @Test
    void getInterests() {
        assertEquals(interests, user.getInterests());
    }

    /**
     * Tests the getFriends method.
     */
    @Test
    void getFriends() {
        assertEquals(friends, user.getFriends());
    }

    /**
     * Tests the getChats method.
     */
    @Test
    void getChats() {
        assertEquals(chats, user.getChats());
    }

    /**
     * Tests the getDateCreated method.
     */
    @Test
    void getDateCreated() {
        assertEquals(dateCreated, user.getDateCreated());
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    void setUsername() {
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    /**
     * Tests the setPassword method.
     */
    @Test
    void setPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    /**
     * Tests the setBio method.
     */
    @Test
    void setBio() {
        user.setBio("New bio.");
        assertEquals("New bio.", user.getBio());
    }

    /**
     * Tests the setProgramOfStudy method.
     */
    @Test
    void setProgramOfStudy() {
        user.setProgramOfStudy("Mathematics");
        assertEquals("Mathematics", user.getProgramOfStudy());
    }

    /**
     * Tests the setAge method.
     */
    @Test
    void setAge() {
        user.setAge(25);
        assertEquals(25, user.getAge());
    }

    /**
     * Tests the setInterests method.
     */
    @Test
    void setInterests() {
        List<String> newInterests = new ArrayList<>(Arrays.asList("Coding", "Gaming"));
        user.setInterests(newInterests);
        assertEquals(newInterests, user.getInterests());
    }

    /**
     * Tests the setFriends method.
     */
    @Test
    void setFriends() {
        List<String> newFriends = new ArrayList<>(Arrays.asList("Charlie"));
        user.setFriends(newFriends);
        assertEquals(newFriends, user.getFriends());
    }

    /**
     * Tests the setChats method.
     * Verifies that the chats list is correctly set.
     */
    @Test
    void setChats() {
        List<String> newChats = Arrays.asList("chat1", "chat2");
        user.setChats(newChats);
        assertEquals(newChats, user.getChats());
    }

    /**
     * Tests the setDateCreated method.
     * Verifies that the dateCreated is correctly set.
     */
    @Test
    void setDateCreated() {
        LocalDateTime newDateCreated = LocalDateTime.of(2023, 7, 15, 10, 30);
        user.setDateCreated(newDateCreated);
        assertEquals(newDateCreated, user.getDateCreated());
    }
}