package use_case.send_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SendMessageInputData class.
 */
class SendMessageInputDataTest {

    private SendMessageInputData inputData;
    private final String chatName = "Test Chat";
    private final String message = "Hello, World!";
    private final String sender = "senderUser";
    private final String receiver = "receiverUser";
    private final LocalDateTime now = LocalDateTime.now();

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        inputData = new SendMessageInputData(chatName, message, now, sender, receiver);
    }

    /**
     * Tests the getMessage method.
     * Verifies that it returns the correct message.
     */
    @Test
    void getMessage() {
        assertEquals(message, inputData.getMessage());
    }

    /**
     * Tests the getSender method.
     * Verifies that it returns the correct sender.
     */
    @Test
    void getSender() {
        assertEquals(sender, inputData.getSender());
    }

    /**
     * Tests the getReceiver method.
     * Verifies that it returns the correct receiver.
     */
    @Test
    void getReceiver() {
        assertEquals(receiver, inputData.getReceiver());
    }

    /**
     * Tests the getChatName method.
     * Verifies that it returns the correct chat name.
     */
    @Test
    void getChatName() {
        assertEquals(chatName, inputData.getChatName());
    }
}