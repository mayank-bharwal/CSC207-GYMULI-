package use_case.make_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MakeChatInputData class.
 */
class MakeChatInputDataTest {

    private MakeChatInputData makeChatInputData;
    private final String chatName = "Test Chat";
    private final String user1 = "User1";
    private final String user2 = "User2";
    private final LocalDateTime time = LocalDateTime.now();

    /**
     * Sets up the test environment before each test.
     * Initializes the MakeChatInputData object with test data.
     */
    @BeforeEach
    void setUp() {
        makeChatInputData = new MakeChatInputData(chatName, user1, user2, time);
    }

    /**
     * Tests the getChatName method.
     * Verifies that the chat name returned by getChatName is the same as the one passed to the constructor.
     */
    @Test
    void getChatName() {
        assertEquals(chatName, makeChatInputData.getChatName());
    }

    /**
     * Tests the getUser_1 method.
     * Verifies that the user1 name returned by getUser_1 is the same as the one passed to the constructor.
     */
    @Test
    void getUser_1() {
        assertEquals(user1, makeChatInputData.getUser_1());
    }

    /**
     * Tests the getUser_2 method.
     * Verifies that the user2 name returned by getUser_2 is the same as the one passed to the constructor.
     */
    @Test
    void getUser_2() {
        assertEquals(user2, makeChatInputData.getUser_2());
    }

    /**
     * Tests the getTime method.
     * Verifies that the time returned by getTime is the same as the one passed to the constructor.
     */
    @Test
    void getTime() {
        assertEquals(time, makeChatInputData.getTime());
    }
}