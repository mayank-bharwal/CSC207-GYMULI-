package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Chat class.
 */
public class ChatTest {
    private Chat chat;
    private ArrayList<User> users;
    private ArrayList<Message> messages;
    private UserFactory userFactory;
    private ChatFactory chatFactory;
    private ArrayList<String> userNames;

    /**
     * Set up the test environment before each test.
     * Initialize mock objects and create a sample Chat instance.
     */
    @BeforeEach
    void setUp() {
        userFactory = mock(UserFactory.class);  // Mock UserFactory
        chatFactory = new ChatFactory();

        users = new ArrayList<>();
        userNames = new ArrayList<>();

        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");

        List<String> friends = new ArrayList<>();
        friends.add("Alice");
        friends.add("Barry");

        List<String> chats = new ArrayList<>();

        LocalDateTime userDateCreated = LocalDateTime.now();

        User user1 = mock(User.class);
        when(user1.getUsername()).thenReturn("Jasmine");
        when(user1.getPassword()).thenReturn("password");
        when(user1.getBio()).thenReturn("(Demo)");
        when(user1.getAge()).thenReturn(21);
        when(user1.getProgramOfStudy()).thenReturn("Computer Science");
        when(user1.getInterests()).thenReturn(interests);
        when(user1.getFriends()).thenReturn(friends);
        when(user1.getChats()).thenReturn(chats);
        when(user1.getDateCreated()).thenReturn(userDateCreated);

        User user2 = mock(User.class);
        when(user2.getUsername()).thenReturn("Charlie");
        when(user2.getPassword()).thenReturn("password123");
        when(user2.getBio()).thenReturn("Always Sunny");
        when(user2.getAge()).thenReturn(22);
        when(user2.getProgramOfStudy()).thenReturn("Law");
        when(user2.getInterests()).thenReturn(interests);
        when(user2.getFriends()).thenReturn(friends);
        when(user2.getChats()).thenReturn(chats);
        when(user2.getDateCreated()).thenReturn(userDateCreated);

        when(userFactory.createUser(eq("Jasmine"), eq("password"), eq("(Demo)"), eq(21), eq("Computer Science"), eq(interests), eq(friends), eq(chats), eq(userDateCreated)))
                .thenReturn(user1);
        when(userFactory.createUser(eq("Charlie"), eq("password123"), eq("Always Sunny"), eq(22), eq("Law"), eq(interests), eq(friends), eq(chats), eq(userDateCreated)))
                .thenReturn(user2);

        users.add(user1);
        users.add(user2);

        userNames.add("Jasmine");
        userNames.add("Charlie");

        messages = new ArrayList<>();
        Message message1 = new Message("Test Chat", "Jasmine", "Charlie",
                "Hello, this is a test message", LocalDateTime.now());
        Message message2 = new Message("Test Chat", "Charlie", "Jasmine",
                "This is a response", LocalDateTime.now());
        messages.add(message1);
        messages.add(message2);

        chat = chatFactory.createChat("Test Chat", userNames, users.size(), messages, LocalDateTime.now());
    }

    /**
     * Test the getChatName method of the Chat class.
     */
    @Test
    void testGetChatName() {
        assertEquals("Test Chat", chat.getChatName());
    }

    /**
     * Test the getUsers method of the Chat class.
     */
    @Test
    void testGetUsers() {
        assertEquals(userNames, chat.getUsers());
    }

    /**
     * Test the getNoOfMembers method of the Chat class.
     */
    @Test
    void testGetNoOfMembers() {
        assertEquals(userNames.size(), chat.getNoOfMembers());
    }

    /**
     * Test the getAllmessages method of the Chat class.
     */
    @Test
    void testGetAllMessages() {
        assertEquals(messages, chat.getAllmessages());
    }

    /**
     * Test the getTime method of the Chat class.
     */
    @Test
    void testGetTime() {
        assertNotNull(chat.getTime());
    }

    /**
     * Test the setChatName method of the Chat class.
     */
    @Test
    void testSetChatName() {
        chat.setChatName("New Chat");
        assertEquals("New Chat", chat.getChatName());
    }

    /**
     * Test the setUsers method of the Chat class.
     */
    @Test
    void testSetUsers() {
        ArrayList<User> newUsers = new ArrayList<>();
        ArrayList<String> newUserNames = new ArrayList<>();
        List<String> newInterests = new ArrayList<>();
        List<String> newFriends = new ArrayList<>();
        List<String> newChats = new ArrayList<>();
        newInterests.add("Hiking");
        newInterests.add("Traveling");

        User newUser = mock(User.class);
        when(newUser.getUsername()).thenReturn("Alice");
        when(newUser.getPassword()).thenReturn("newPassword");
        when(newUser.getBio()).thenReturn("In Wonderland");
        when(newUser.getAge()).thenReturn(24);
        when(newUser.getProgramOfStudy()).thenReturn("Chemistry");
        when(newUser.getInterests()).thenReturn(newInterests);
        when(newUser.getFriends()).thenReturn(newFriends);
        when(newUser.getChats()).thenReturn(newChats);
        when(newUser.getDateCreated()).thenReturn(LocalDateTime.now());

        when(userFactory.createUser(eq("Alice"), eq("newPassword"), eq("In Wonderland"), eq(24), eq("Chemistry"), eq(newInterests), eq(newFriends), eq(newChats), any(LocalDateTime.class)))
                .thenReturn(newUser);

        newUsers.add(newUser);
        newUserNames.add("Alice");
        chat.setUsers(newUserNames);
        assertEquals(newUserNames, chat.getUsers());
    }

    /**
     * Test the setNoOfMembers method of the Chat class.
     */
    @Test
    void testSetNoOfMembers() {
        chat.setNoOfMembers(5);
        assertEquals(5, chat.getNoOfMembers());
    }

    /**
     * Test the setAllmessages method of the Chat class.
     */
    @Test
    void testSetAllMessages() {
        ArrayList<Message> newMessages = new ArrayList<>();
        Message newMessage = new Message("Chat Log", "Barry", "Imogen",
                "New test message", LocalDateTime.now());
        newMessages.add(newMessage);

        chat.setAllmessages(newMessages);
        assertEquals(newMessages, chat.getAllmessages());
    }

    /**
     * Test the setChatName method of the Chat class when setting it to null.
     */
    @Test
    void testSetChatNameToNull() {
        chat.setChatName(null);
        assertEquals(null, chat.getChatName());
    }

//    @Test
//    void testSetChatNameWithEmptyString() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            chat.setChatName("");
//        });
//    }
//
//    @Test
//    void testSetChatNameWithWhitespace() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            chat.setChatName(" ");
//        });
//    }
//
//    @Test
//    void testSetUsersToNull() {
//        assertThrows(NullPointerException.class, () -> {
//            chat.setUsers(null);
//        });
//    }
//
//    @Test
//    void testSetAllMessagesToNull() {
//        assertThrows(NullPointerException.class, () -> {
//            chat.setAllmessages(null);
//        });
//    }
//
//    @Test
//    void testSetUsersWithEmptyList() {
//        ArrayList<String> emptyUsers = new ArrayList<>();
//        assertThrows(IllegalArgumentException.class, () -> {
//            chat.setUsers(emptyUsers);
//        });
//    }
//
//    @Test
//    void testSetAllMessagesWithEmptyList() {
//        ArrayList<Message> emptyMessages = new ArrayList<>();
//        assertThrows(IllegalArgumentException.class, () -> {
//            chat.setAllmessages(emptyMessages);
//        });
//    }
}