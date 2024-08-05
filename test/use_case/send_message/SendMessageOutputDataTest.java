package use_case.send_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SendMessageOutputData class.
 */
class SendMessageOutputDataTest {

    private SendMessageOutputData outputData;
    private final String message = "Hello, World!";
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final boolean useCaseFailed = false;
    private final String chatName = "Test Chat";

    @BeforeEach
    void setUp() {
        outputData = new SendMessageOutputData(message, creationTime, useCaseFailed, chatName);
    }

//    /**
//     * Tests the getMessage method.
//     * Verifies that the message is returned correctly.
//     */
//    @Test
//    void getMessage() {
//        assertEquals(message, outputData.getMessage().getMessage());
//    }

    /**
     * Tests the getCreationTime method.
     * Verifies that the creation time is returned correctly.
     */
    @Test
    void getCreationTime() {
        assertEquals(creationTime, outputData.getCreationTime());
    }

    /**
     * Tests the setCreationTime method.
     * Verifies that the creation time is set and returned correctly.
     */
    @Test
    void setCreationTime() {
        LocalDateTime newTime = LocalDateTime.now().plusHours(1);
        outputData.setCreationTime(newTime);
        assertEquals(newTime, outputData.getCreationTime());
    }

    /**
     * Tests the getChatName method.
     * Verifies that the chat name is returned correctly.
     */
    @Test
    void getChatName() {
        assertEquals(chatName, outputData.getChatName("Test Chat"));
    }
}