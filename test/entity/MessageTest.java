package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class.
 */
public class MessageTest {
    private Message message;
    private MessageFactory messageFactory;

    /**
     * Set up the test environment before each test.
     * Initialize the MessageFactory and create a sample Message instance.
     */
    @BeforeEach
    void setUp() {
        messageFactory = new MessageFactory();
        message = messageFactory.createMessage("Test Chat", "Alice", "Barry",
                "Hello, this is a test message", LocalDateTime.now());
    }

    /**
     * Test the getChatName method of the Message class.
     */
    @Test
    void testGetChatName() {
        assertEquals("Test Chat", message.getChatName());
    }

    /**
     * Test the getSender method of the Message class.
     */
    @Test
    void testGetSender() {
        assertEquals("Alice", message.getSender());
    }

    /**
     * Test the getReceiver method of the Message class.
     */
    @Test
    void testGetReceiver() {
        assertEquals("Barry", message.getReceiver());
    }

    /**
     * Test the getMessage method of the Message class.
     */
    @Test
    void testGetMessage() {
        assertEquals("Hello, this is a test message", message.getMessage());
    }

    /**
     * Test the getTime method of the Message class.
     */
    @Test
    void testGetTime() {
        assertNotNull(message.getTime());
    }

    /**
     * Test the setChatName method of the Message class.
     */
    @Test
    void testSetChatName() {
        message.setChatName("New Chat");
        assertEquals("New Chat", message.getChatName());
    }

    /**
     * Test the setSender method of the Message class.
     */
    @Test
    void testSetSender() {
        message.setSender("Charlie");
        assertEquals("Charlie", message.getSender());
    }

    /**
     * Test the setReceiver method of the Message class.
     */
    @Test
    void testSetReceiver() {
        message.setReceiver("Imogen");
        assertEquals("Imogen", message.getReceiver());
    }

    /**
     * Test the setMessage method of the Message class.
     */
    @Test
    void testSetMessage() {
        message.setMessage("New test message");
        assertEquals("New test message", message.getMessage());
    }

    /**
     * Test the setChatName method of the Message class when setting it to null.
     */
    @Test
    void testSetChatNameToNull() {
        message.setChatName(null);
        assertNull(message.getChatName());
    }

    /**
     * Test the setSender method of the Message class when setting it to null.
     */
    @Test
    void testSetSenderToNull() {
        message.setSender(null);
        assertNull(message.getSender());
    }

    /**
     * Test the setReceiver method of the Message class when setting it to null.
     */
    @Test
    void testSetReceiverToNull() {
        message.setReceiver(null);
        assertNull(message.getReceiver());
    }

    /**
     * Test the setMessage method of the Message class when setting it to null.
     */
    @Test
    void testSetMessageToNull() {
        message.setMessage(null);
        assertNull(message.getMessage());
    }

    /**
     * Test the setChatName method of the Message class when setting it to an empty string.
     */
    @Test
    void testSetChatNameWithEmptyString() {
        message.setChatName("");
        assertEquals("", message.getChatName());
    }

    /**
     * Test the setSender method of the Message class when setting it to an empty string.
     */
    @Test
    void testSetSenderWithEmptyString() {
        message.setSender("");
        assertEquals("", message.getSender());
    }

    /**
     * Test the setReceiver method of the Message class when setting it to an empty string.
     */
    @Test
    void testSetReceiverWithEmptyString() {
        message.setReceiver("");
        assertEquals("", message.getReceiver());
    }

    /**
     * Test the setMessage method of the Message class when setting it to an empty string.
     */
    @Test
    void testSetMessageWithEmptyString() {
        message.setMessage("");
        assertEquals("", message.getMessage());
    }

    /**
     * Test the setChatName method of the Message class when setting it to a string with only whitespace.
     */
    @Test
    void testSetChatNameWithWhitespace() {
        message.setChatName(" ");
        assertEquals(" ", message.getChatName());
    }

    /**
     * Test the setSender method of the Message class when setting it to a string with only whitespace.
     */
    @Test
    void testSetSenderWithWhitespace() {
        message.setSender(" ");
        assertEquals(" ", message.getSender());
    }

    /**
     * Test the setReceiver method of the Message class when setting it to a string with only whitespace.
     */
    @Test
    void testSetReceiverWithWhitespace() {
        message.setReceiver(" ");
        assertEquals(" ", message.getReceiver());
    }

    /**
     * Test the setMessage method of the Message class when setting it to a string with only whitespace.
     */
    @Test
    void testSetMessageWithWhitespace() {
        message.setMessage(" ");
        assertEquals(" ", message.getMessage());
    }
}