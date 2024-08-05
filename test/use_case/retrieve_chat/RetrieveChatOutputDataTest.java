package use_case.retrieve_chat;

import entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RetrieveChatOutputData class.
 */
class RetrieveChatOutputDataTest {

    private RetrieveChatOutputData outputData;
    private final String chatName = "TestChat";
    private final List<String> users = new ArrayList<>(Arrays.asList("User1", "User2"));
    private final int noOfMembers = 2;
    private final List<Message> messages = new ArrayList<>();
    private final LocalDateTime time = LocalDateTime.now();
    private final boolean failView = false;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        outputData = new RetrieveChatOutputData(chatName, (ArrayList<String>) users, noOfMembers,
                (ArrayList<Message>) messages, time, failView);
    }

    /**
     * Tests the getChatName method.
     */
    @Test
    void getChatName() {
        assertEquals(chatName, outputData.getChatName());
    }

    /**
     * Tests the getUsers method.
     */
    @Test
    void getUsers() {
        assertEquals(users, outputData.getUsers());
    }

    /**
     * Tests the getNoOfMembers method.
     */
    @Test
    void getNoOfmembers() {
        assertEquals(noOfMembers, outputData.getNoOfmembers());
    }

    /**
     * Tests the getMessages method.
     */
    @Test
    void getAllMessages() {
        assertEquals(messages, outputData.getAllMessages());
    }

    /**
     * Tests the getTime method.
     */
    @Test
    void getTime() {
        assertEquals(time, outputData.getTime());
    }
}