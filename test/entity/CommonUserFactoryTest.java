package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the CommonUserFactory class.
 * This class ensures that the CommonUserFactory correctly creates instances of the CommonUser class.
 */
class CommonUserFactoryTest {

    private CommonUserFactory commonUserFactory;
    private String username;
    private String password;
    private String bio;
    private Integer age;
    private String programOfStudy;
    private List<String> interests;
    private List<String> friends;
    private List<String> chats;
    private LocalDateTime dateCreated;

    /**
     * Sets up the test environment before each test method.
     * Initializes the CommonUserFactory and the attributes that will be used to create a CommonUser.
     */
    @BeforeEach
    void setUp() {
        commonUserFactory = new CommonUserFactory();

        username = "Jasmine";
        password = "password";
        bio = "(Demo)";
        age = 21;
        programOfStudy = "Computer Science";
        interests = Arrays.asList("Reading", "Music", "Gym");
        friends = Arrays.asList("Barry");
        chats = Arrays.asList("chat1", "chat2");
        dateCreated = LocalDateTime.of(2023, 7, 15, 10, 30);
    }

    /**
     * Tests the createUser method of the CommonUserFactory.
     * This test verifies that the CommonUserFactory correctly creates a CommonUser
     * with the specified attributes.
     */
    @Test
    void createUser() {
        User createdUser = commonUserFactory.createUser(username, password, bio, age, programOfStudy,
                interests, friends, chats, dateCreated);

        assertEquals(username, createdUser.getUsername());
        assertEquals(password, createdUser.getPassword());
        assertEquals(bio, createdUser.getBio());
        assertEquals(age, createdUser.getAge());
        assertEquals(programOfStudy, createdUser.getProgramOfStudy());
        assertEquals(interests, createdUser.getInterests());
        assertEquals(friends, createdUser.getFriends());
        assertEquals(chats, createdUser.getChats());
        assertEquals(dateCreated, createdUser.getDateCreated());
    }
}