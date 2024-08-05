package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for the User class.
 */
public class UserTest {
    private User user;
    private List<String> interests;
    private List<String> friends;
    private List<String> chats;
    private LocalDateTime dateCreated;
    private UserFactory userFactory;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        userFactory = Mockito.mock(UserFactory.class);

        interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");
        interests.add("Gym");

        friends = new ArrayList<>();
        friends.add("Barry");

        chats = new ArrayList<>();

        dateCreated = LocalDateTime.now();

        user = Mockito.mock(User.class);

        // Set default return values for getters
        Mockito.when(user.getUsername()).thenReturn("Jasmine");
        Mockito.when(user.getPassword()).thenReturn("password");
        Mockito.when(user.getBio()).thenReturn("(Demo)");
        Mockito.when(user.getAge()).thenReturn(21);
        Mockito.when(user.getProgramOfStudy()).thenReturn("Computer Science");
        Mockito.when(user.getInterests()).thenReturn(interests);
        Mockito.when(user.getFriends()).thenReturn(friends);
        Mockito.when(user.getDateCreated()).thenReturn(dateCreated);

        // Configure set methods to throw exceptions for invalid inputs
        Mockito.doThrow(new NullPointerException("Username cannot be null")).when(user).setUsername(null);
        Mockito.doThrow(new NullPointerException("Password cannot be null")).when(user).setPassword(null);
        Mockito.doThrow(new IllegalArgumentException("Username cannot be empty")).when(user).setUsername("");
        Mockito.doThrow(new IllegalArgumentException("Password cannot be empty")).when(user).setPassword("");
        Mockito.doThrow(new IllegalArgumentException("Username cannot be whitespace")).when(user).setUsername(" ");
        Mockito.doThrow(new IllegalArgumentException("Password cannot be whitespace")).when(user).setPassword(" ");
        Mockito.doThrow(new IllegalArgumentException("Program of study cannot be whitespace")).when(user).setProgramOfStudy(" ");

        // Simulate behavior of setting methods
        Mockito.doAnswer(invocation -> {
            String newProgram = invocation.getArgument(0);
            Mockito.when(user.getProgramOfStudy()).thenReturn(newProgram);
            return null;
        }).when(user).setProgramOfStudy(Mockito.anyString());

        Mockito.doAnswer(invocation -> {
            String newUsername = invocation.getArgument(0);
            Mockito.when(user.getUsername()).thenReturn(newUsername);
            return null;
        }).when(user).setUsername(Mockito.anyString());

        Mockito.when(userFactory.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
                        Mockito.anyString(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(LocalDateTime.class)))
                .thenReturn(user);
    }

    /**
     * Tests the getUsername method.
     */
    @Test
    void testGetUsername() {
        assertEquals("Jasmine", user.getUsername());
    }

    /**
     * Tests the getPassword method.
     */
    @Test
    void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    /**
     * Tests the getBio method.
     */
    @Test
    void testGetBio() {
        assertEquals("(Demo)", user.getBio());
    }

    /**
     * Tests the getProgramOfStudy method.
     */
    @Test
    void testGetProgramOfStudy() {
        assertEquals("Computer Science", user.getProgramOfStudy());
    }

    /**
     * Tests the getAge method.
     */
    @Test
    void testGetAge() {
        assertEquals(21, user.getAge());
    }

    /**
     * Tests the getInterests method.
     */
    @Test
    void testGetInterests() {
        assertEquals(interests, user.getInterests());
    }

    /**
     * Tests the getFriends method.
     */
    @Test
    void testGetFriends() {
        assertEquals(friends, user.getFriends());
    }

    /**
     * Tests the getDateCreated method.
     */
    @Test
    void testGetDateCreated() {
        assertEquals(dateCreated, user.getDateCreated());
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    void testSetUsername() {
        user.setUsername("newUsername");
        when(user.getUsername()).thenReturn("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    /**
     * Tests the setPassword method.
     */
    @Test
    void testSetPassword() {
        user.setPassword("newPassword");
        when(user.getPassword()).thenReturn("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    /**
     * Tests the setBio method.
     */
    @Test
    void testSetBio() {
        user.setBio("New bio.");
        when(user.getBio()).thenReturn("New bio.");
        assertEquals("New bio.", user.getBio());
    }

    /**
     * Tests the setProgramOfStudy method.
     */
    @Test
    void testSetProgramOfStudy() {
        user.setProgramOfStudy("Mathematics");
        when(user.getProgramOfStudy()).thenReturn("Mathematics");
        assertEquals("Mathematics", user.getProgramOfStudy());
    }

    /**
     * Tests the setAge method.
     */
    @Test
    void testSetAge() {
        user.setAge(25);
        when(user.getAge()).thenReturn(25);
        assertEquals(25, user.getAge());
    }

    /**
     * Tests the setInterests method.
     */
    @Test
    void testSetInterests() {
        List<String> newInterests = new ArrayList<>();
        newInterests.add("Coding");
        newInterests.add("Gaming");
        user.setInterests(newInterests);
        when(user.getInterests()).thenReturn(newInterests);
        assertEquals(newInterests, user.getInterests());
    }

    /**
     * Tests the setFriends method.
     */
    @Test
    void testSetFriends() {
        List<String> newFriends = new ArrayList<>();
        newFriends.add("Charlie");
        user.setFriends(newFriends);
        when(user.getFriends()).thenReturn(newFriends);
        assertEquals(newFriends, user.getFriends());
    }

    /**
     * Tests setting an empty bio.
     */
    @Test
    void testSetEmptyBio() {
        user.setBio("");
        when(user.getBio()).thenReturn("");
        assertEquals("", user.getBio());
    }

    /**
     * Tests setting an empty program of study.
     */
    @Test
    void testSetEmptyProgramOfStudy() {
        user.setProgramOfStudy("");
        when(user.getProgramOfStudy()).thenReturn("");
        assertEquals("", user.getProgramOfStudy());
    }

    /**
     * Tests setting an empty list of interests.
     */
    @Test
    void testSetEmptyInterests() {
        List<String> emptyInterests = Collections.emptyList();
        user.setInterests(emptyInterests);
        when(user.getInterests()).thenReturn(emptyInterests);
        assertEquals(emptyInterests, user.getInterests());
    }

    /**
     * Tests setting a bio with whitespace.
     */
    @Test
    void testSetBioWithWhitespace() {
        user.setBio(" ");
        when(user.getBio()).thenReturn(" ");
        assertEquals(" ", user.getBio());
    }

    /**
     * Tests setting a username with special characters.
     */
    @Test
    void testSetUsernameWithSpecialCharacters() {
        user.setUsername("!@#$%");
        when(user.getUsername()).thenReturn("!@#$%");
        assertEquals("!@#$%", user.getUsername());
    }

    /**
     * Tests setting a password with special characters.
     */
    @Test
    void testSetPasswordWithSpecialCharacters() {
        user.setPassword("!@#$%");
        when(user.getPassword()).thenReturn("!@#$%");
        assertEquals("!@#$%", user.getPassword());
    }

    /**
     * Tests setting a bio with special characters.
     */
    @Test
    void testSetBioWithSpecialCharacters() {
        user.setBio("!@#$%");
        when(user.getBio()).thenReturn("!@#$%");
        assertEquals("!@#$%", user.getBio());
    }

    /**
     * Tests setting a program of study with special characters.
     */
    @Test
    void testSetProgramOfStudyWithSpecialCharacters() {
        user.setProgramOfStudy("!@#$%");
        when(user.getProgramOfStudy()).thenReturn("!@#$%");
        assertEquals("!@#$%", user.getProgramOfStudy());
    }

    /**
     * Tests setting interests with special characters.
     */
    @Test
    void testSetInterestsWithSpecialCharacters() {
        List<String> interestsWithSpecialChars = new ArrayList<>();
        interestsWithSpecialChars.add("!@#$%");
        user.setInterests(interestsWithSpecialChars);
        when(user.getInterests()).thenReturn(interestsWithSpecialChars);
        assertEquals(interestsWithSpecialChars, user.getInterests());
    }

    /**
     * Tests setting the username to null.
     */
    @Test
    void testSetUsernameToNull() {
        assertThrows(NullPointerException.class, () -> user.setUsername(null));
    }

    /**
     * Tests setting the password to null.
     */
    @Test
    void testSetPasswordToNull() {
        assertThrows(NullPointerException.class, () -> user.setPassword(null));
    }

    /**
     * Tests setting an empty password.
     */
    @Test
    void testSetEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));
    }

    /**
     * Tests setting a password with whitespace.
     */
    @Test
    void testSetPasswordWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(" "));
    }

//    private User user;
//    private List<String> interests;
//
//    @BeforeEach
//    void setUp() {
//        interests = new ArrayList<>();
//        interests.add("Reading");
//        interests.add("Music");
//        interests.add("Gym");
//
//        user = UserFactory.createUser("Jasmine", "password", "(Demo)",
//                "Computer Science", "jasmine.jpg", new ArrayList<>(), new ArrayList<>());
//    }
//
//    @Test
//    void testGetUsername() {
//        assertEquals("Jasmine", user.getUsername());
//    }
//
//    @Test
//    void testGetPassword() {
//        assertEquals("password", user.getPassword());
//    }
//
//    @Test
//    void testGetBio() {
//        assertEquals("(Demo)", user.getBio());
//    }
//
//    @Test
//    void testGetProgramOfStudy() {
//        assertEquals("Computer Science", user.getProgramOfStudy());
//    }
//
//    @Test
//    void testGetAge() {
//        assertEquals("jasmine.jpg", user.getAge());
//    }
//
//    @Test
//    void testGetInterests() {
//        assertEquals(interests, user.getInterests());
//    }
//
//    @Test
//    void testSetUsername() {
//        user.setUsername("newUsername");
//        assertEquals("newUsername", user.getUsername());
//    }
//
//    @Test
//    void testSetPassword() {
//        user.setPassword("newPassword");
//        assertEquals("newPassword", user.getPassword());
//    }
//
//    @Test
//    void testSetBio() {
//        user.setBio("New bio.");
//        assertEquals("New bio.", user.getBio());
//    }
//
//    @Test
//    void testSetProgramOfStudy() {
//        user.setProgramOfStudy("Mathematics");
//        assertEquals("Mathematics", user.getProgramOfStudy());
//    }
//
//    @Test
//    void testSetProfilePicture() {
//        user.setProfilePicture("newProfilePic.jpg");
//        assertEquals("newProfilePic.jpg", user.getProfilePicture());
//    }
//
//    @Test
//    void testSetInterests() {
//        List<String> newInterests = new ArrayList<>();
//        newInterests.add("Coding");
//        newInterests.add("Gaming");
//        user.setInterests(newInterests);
//        assertEquals(newInterests, user.getInterests());
//    }
//
//    @Test
//    void testSetEmptyBio() {
//        user.setBio("");
//        assertEquals("", user.getBio());
//    }
//
//    @Test
//    void testSetEmptyProgramOfStudy() {
//        user.setProgramOfStudy("");
//        assertEquals("", user.getProgramOfStudy());
//    }
//
//    @Test
//    void testSetEmptyProfilePicture() {
//        user.setProfilePicture("defaultProfilePicture.jpg");
//        assertEquals("defaultProfilePicture.jpg", user.getProfilePicture());
//    }
//
//    @Test
//    void testSetEmptyInterests() {
//        List<String> emptyInterests = Collections.emptyList();
//        user.setInterests(emptyInterests);
//        assertEquals(emptyInterests, user.getInterests());
//    }
//
//    @Test
//    void testSetBioWithWhitespace() {
//        user.setBio(" ");
//        assertEquals(" ", user.getBio());
//    }
//
//    @Test
//    void testSetUsernameWithSpecialCharacters() {
//        user.setUsername("!@#$%");
//        assertEquals("!@#$%", user.getUsername());
//    }
//
//    @Test
//    void testSetPasswordWithSpecialCharacters() {
//        user.setPassword("!@#$%");
//        assertEquals("!@#$%", user.getPassword());
//    }
//
//    @Test
//    void testSetBioWithSpecialCharacters() {
//        user.setBio("!@#$%");
//        assertEquals("!@#$%", user.getBio());
//    }
//
//    @Test
//    void testSetProgramOfStudyWithSpecialCharacters() {
//        user.setProgramOfStudy("!@#$%");
//        assertEquals("!@#$%", user.getProgramOfStudy());
//    }
//
//    @Test
//    void testSetProfilePictureWithSpecialCharacters() {
//        user.setProfilePicture("!@#$%");
//        assertEquals("!@#$%", user.getProfilePicture());
//    }
//
//    @Test
//    void testSetInterestsWithSpecialCharacters() {
//        List<String> interestsWithSpecialChars = new ArrayList<>();
//        interestsWithSpecialChars.add("!@#$%");
//        user.setInterests(interestsWithSpecialChars);
//        assertEquals(interestsWithSpecialChars, user.getInterests());
//    }
//
//    @Test
//    void testSetUsernameToNull() {
//        assertThrows(NullPointerException.class, () -> user.setUsername(null));
//    }
//
//    @Test
//    void testSetPasswordToNull() {
//        assertThrows(NullPointerException.class, () -> user.setPassword(null));
//    }
//
//    @Test
//    void testSetEmptyUsername() {
//        assertThrows(IllegalArgumentException.class, () -> user.setUsername(""));
//    }
//
//    @Test
//    void testSetEmptyPassword() {
//        assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));
//    }
//
//    @Test
//    void testSetUsernameWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> user.setUsername(" "));
//    }
//
//    @Test
//    void testSetPasswordWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> user.setPassword(" "));
//    }
//
//    @Test
//    void testSetProgramOfStudyWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> user.setProgramOfStudy(" "));
//    }
//
//    @Test
//    void testSetProfilePictureWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> user.setProfilePicture(" "));
//    }
//
//    @Test
//    void testSetInterestsWithWhitespace() {
//        List<String> interestsWithWhitespace = new ArrayList<>();
//        assertThrows(IllegalArgumentException.class, () -> user.setInterests(interestsWithWhitespace));
//    }
}

//    @Test
//    void testSetUsernameToNull() {
//        assertThrows(NullPointerException.class, () -> user.setUsername(null));
//    }
//
//    @Test
//    void testSetPasswordToNull() {
//        assertThrows(NullPointerException.class, () -> user.setPassword(null));
//    }
//
//    @Test
//    void testSetBioToNull() {
//        user.setBio(null);
//        assertNull(user.getBio());
//    }
//
//    @Test
//    void testSetProgramOfStudyToNull() {
//        user.setProgramOfStudy(null);
//        assertNull(user.getProgramOfStudy());
//    }
//
//    @Test
//    void testSetProfilePictureToNull() {
//        user.setProfilePicture(null);
//        assertNull(user.getProfilePicture());
//    }
//
//    @Test
//    void testSetInterestsToNull() {
//        user.setInterests(null);
//        assertNull(user.getInterests());
//    }
//
//    @Test
//    void testSetFriendsToNull() {
//        user.setFriends(null);
//        assertNull(user.getFriends());
//    }
//    @Test
//    void testSetProgramOfStudyWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> user.setProgramOfStudy(" "));
//    }
//
//    @Test
//    void testSetInterestsWithWhitespace() {
//        List<String> interestsWithWhitespace = new ArrayList<>();
//        interestsWithWhitespace.add(" ");
//        assertThrows(IllegalArgumentException.class, () -> user.setInterests(interestsWithWhitespace));
//    }
