import entity.User;
import entity.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GroupTest {

    private UserFactory userFactory;
    private Group group;
    private ArrayList<User> users;
    private LocalDateTime dateCreated;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();

        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");

        List<String> friends = new ArrayList<>();
        friends.add("Barry");

        LocalDateTime userDateCreated = LocalDateTime.now();

        User user1 = userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                "Computer Science", interests, friends, dateCreated);
        User user2 = userFactory.createUser("Charlie", "password123", "Always Sunny", 22,
                "Law", interests, friends, dateCreated);

        users.add(user1);
        users.add(user2);

        group = new Group("Study Group", users, "A group for study sessions",
                "groupImage.jpg", dateCreated);
    }

    @Test
    void testGetGroupName() {
        assertEquals("Study Group", group.getGroupName());
    }

    @Test
    void testGetUsers() {
        assertEquals(users, group.getUsers());
    }

    @Test
    void testGetBio() {
        assertEquals("A group for study sessions", group.getBio());
    }

    @Test
    void testGetImage() {
        assertEquals("groupImage.jpg", group.getImage());
    }

    @Test
    void testGetDateCreated() {
        assertEquals(dateCreated, group.getDateCreated());
    }

    @Test
    void testSetGroupName() {
        group.setGroupName("New Group Name");
        assertEquals("New Group Name", group.getGroupName());
    }

    @Test
    void testSetUsers() {
        ArrayList<User> newUsers = new ArrayList<>();
        List<String> newInterests = new ArrayList<>();
        newInterests.add("Coding");
        newInterests.add("Traveling");

        User newUser = userFactory.createUser("Alice", "anotherPassword", "In Wonderland",
                24, "Chemistry", newInterests, new ArrayList<>(), LocalDateTime.now());
        newUsers.add(newUser);

        group.setUsers(newUsers);
        assertEquals(newUsers, group.getUsers());
    }

    @Test
    void testSetBio() {
        group.setBio("New group bio");
        assertEquals("New group bio", group.getBio());
    }

    @Test
    void testSetImage() {
        group.setImage("newGroupImage.jpg");
        assertEquals("newGroupImage.jpg", group.getImage());
    }

    @Test
    void testSetDateCreated() {
        LocalDateTime newDateCreated = LocalDateTime.now().plusDays(1);
        group.setDateCreated(newDateCreated);
        assertEquals(newDateCreated, group.getDateCreated());
    }

    @Test
    void testSetGroupNameToNull() {
        assertThrows(NullPointerException.class, () -> {
            group.setGroupName(null);
        });
    }

    @Test
    void testSetBioToNull() {
        assertThrows(NullPointerException.class, () -> {
            group.setBio(null);
        });
    }

    @Test
    void testSetImageToNull() {
        assertThrows(NullPointerException.class, () -> {
            group.setImage(null);
        });
    }

    @Test
    void testSetUsersToNull() {
        assertThrows(NullPointerException.class, () -> {
            group.setUsers(null);
        });
    }

    @Test
    void testSetGroupNameWithSpecialCharacters() {
        group.setGroupName("Gr0up!@#");
        assertEquals("Gr0up!@#", group.getGroupName());
    }

    @Test
    void testSetBioWithSpecialCharacters() {
        group.setBio("B!@#");
        assertEquals("B!@#", group.getBio());
    }

    @Test
    void testSetImageWithSpecialCharacters() {
        group.setImage("Image!@#.jpg");
        assertEquals("Image!@#.jpg", group.getImage());
    }

    @Test
    void testSetGroupNameWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setGroupName("");
        });
    }

    @Test
    void testSetBioWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setBio("");
        });
    }

    @Test
    void testSetImageWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setImage("");
        });
    }

    @Test
    void testSetGroupNameWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setGroupName(" ");
        });
    }

    @Test
    void testSetBioWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setBio(" ");
        });
    }

    @Test
    void testSetImageWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            group.setImage(" ");
        });
    }

    @Test
    void testSetEmptyUsers() {
        ArrayList<User> emptyUsers = new ArrayList<>();
        group.setUsers(emptyUsers);
        assertEquals(emptyUsers, group.getUsers());
    }
}