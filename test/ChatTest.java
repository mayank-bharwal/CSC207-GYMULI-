import entity.User;
import entity.UserFactory;
import entity.Chat;
import entity.ChatFactory;
import entity.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatTest {
    private Chat chat;
    private ArrayList<User> users;
    private ArrayList<Message> messages;
    private ChatFactory chatFactory;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();

        List<String> interests = new ArrayList<>();
        interests.add("Reading");
        interests.add("Music");

        List<String> friends = new ArrayList<>();
        friends.add("Alice");
        friends.add("Barry");

        List<String> chats = new ArrayList<>();

        LocalDateTime userDateCreated = LocalDateTime.now();

        User user1 = userFactory.createUser("Jasmine", "password", "(Demo)", 21,
                "Computer Science", interests, friends, chats, userDateCreated);
        User user2 = userFactory.createUser("Charlie", "password123", "Always Sunny", 22,
                "Law", interests, friends, chats, userDateCreated);

        users.add(user1);
        users.add(user2);

        messages = new ArrayList<>();
        Message message1 = new Message("Test Chat", "Jasmine", "Charlie",
                "Hello, this is a test message", LocalDateTime.now());
        Message message2 = new Message("Test Chat", "Charlie", "Jasmine",
                "This is a response", LocalDateTime.now());
        messages.add(message1);
        messages.add(message2);

        chatFactory = new ChatFactory();
//        chat = chatFactory.createChat("Test Chat", users, users.size(), messages);
    }

    @Test
    void testGetChatName() {
        assertEquals("Test Chat", chat.getChatName());
    }

    @Test
    void testGetUsers() {
        assertEquals(users, chat.getUsers());
    }

    @Test
    void testGetNoOfMembers() {
        assertEquals(users.size(), chat.getNoOfMembers());
    }

    @Test
    void testGetAllMessages() {
        assertEquals(messages, chat.getAllmessages());
    }

    @Test
    void testGetTime() {
        assertNotNull(chat.getTime());
    }

    @Test
    void testSetChatName() {
        chat.setChatName("New Chat");
        assertEquals("New Chat", chat.getChatName());
    }

    @Test
    void testSetUsers() {
        ArrayList<User> newUsers = new ArrayList<>();
        List<String> newInterests = new ArrayList<>();
        List<String> newFriends = new ArrayList<>();
        List<String> newChats = new ArrayList<>();
        newInterests.add("Hiking");
        newInterests.add("Traveling");

        User newUser = userFactory.createUser("Alice", "newPassword", "In Wonderland", 24,
                "Chemistry", newInterests, newFriends, newChats, LocalDateTime.now());
        newUsers.add(newUser);
        assertEquals(newUsers, chat.getUsers());
    }

    @Test
    void testSetNoOfMembers() {
        chat.setNoOfMembers(5);
        assertEquals(5, chat.getNoOfMembers());
    }

    @Test
    void testSetAllMessages() {
        ArrayList<Message> newMessages = new ArrayList<>();
        Message newMessage = new Message("Chat Log", "Barry", "Imogen",
                "New test message", LocalDateTime.now());
        newMessages.add(newMessage);

        chat.setAllmessages(newMessages);
        assertEquals(newMessages, chat.getAllmessages());
    }

    @Test
    void testSetChatNameToNull() {
        assertThrows(NullPointerException.class, () -> {
            chat.setChatName(null);
        });
    }

    @Test
    void testSetChatNameWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            chat.setChatName("");
        });
    }

    @Test
    void testSetChatNameWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            chat.setChatName(" ");
        });
    }

    @Test
    void testSetUsersToNull() {
        assertThrows(NullPointerException.class, () -> {
            chat.setUsers(null);
        });
    }

    @Test
    void testSetAllMessagesToNull() {
        assertThrows(NullPointerException.class, () -> {
            chat.setAllmessages(null);
        });
    }

    @Test
    void testSetUsersWithEmptyList() {
        ArrayList<String> emptyUsers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            chat.setUsers(emptyUsers);
        });
    }

    @Test
    void testSetAllMessagesWithEmptyList() {
        ArrayList<Message> emptyMessages = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            chat.setAllmessages(emptyMessages);
        });
    }
}