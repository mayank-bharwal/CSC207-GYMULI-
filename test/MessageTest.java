import entity.Message;
import entity.MessageFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    private Message message;
    private MessageFactory messageFactory;

    @BeforeEach
    void setUp() {
        messageFactory = new MessageFactory();
        message = messageFactory.createMessage("Test Chat", "Alice", "Barry",
                "Hello, this is a test message", LocalDateTime.now());
    }

    @Test
    void testGetChatName() {
        assertEquals("Test Chat", message.getChatName());
    }

    @Test
    void testGetSender() {
        assertEquals("Alice", message.getSender());
    }

    @Test
    void testGetReceiver() {
        assertEquals("Barry", message.getReceiver());
    }

    @Test
    void testGetMessage() {
        assertEquals("Hello, this is a test message", message.getMessage());
    }

    @Test
    void testGetTime() {
        assertNotNull(message.getTime());
    }

    @Test
    void testSetChatName() {
        message.setChatName("New Chat");
        assertEquals("New Chat", message.getChatName());
    }

    @Test
    void testSetSender() {
        message.setSender("Charlie");
        assertEquals("Charlie", message.getSender());
    }

    @Test
    void testSetReceiver() {
        message.setReceiver("Imogen");
        assertEquals("Imogen", message.getReceiver());
    }

    @Test
    void testSetMessage() {
        message.setMessage("New test message");
        assertEquals("New test message", message.getMessage());
    }

    @Test
    void testSetChatNameToNull() {
        message.setChatName(null);
        assertNull(message.getChatName());
    }

    @Test
    void testSetSenderToNull() {
        message.setSender(null);
        assertNull(message.getSender());
    }

    @Test
    void testSetReceiverToNull() {
        message.setReceiver(null);
        assertNull(message.getReceiver());
    }

    @Test
    void testSetMessageToNull() {
        message.setMessage(null);
        assertNull(message.getMessage());
    }

    @Test
    void testSetChatNameWithEmptyString() {
        message.setChatName("");
        assertEquals("", message.getChatName());
    }

    @Test
    void testSetSenderWithEmptyString() {
        message.setSender("");
        assertEquals("", message.getSender());
    }

    @Test
    void testSetReceiverWithEmptyString() {
        message.setReceiver("");
        assertEquals("", message.getReceiver());
    }

    @Test
    void testSetMessageWithEmptyString() {
        message.setMessage("");
        assertEquals("", message.getMessage());
    }

    @Test
    void testSetChatNameWithWhitespace() {
        message.setChatName(" ");
        assertEquals(" ", message.getChatName());
    }

    @Test
    void testSetSenderWithWhitespace() {
        message.setSender(" ");
        assertEquals(" ", message.getSender());
    }

    @Test
    void testSetReceiverWithWhitespace() {
        message.setReceiver(" ");
        assertEquals(" ", message.getReceiver());
    }

    @Test
    void testSetMessageWithWhitespace() {
        message.setMessage(" ");
        assertEquals(" ", message.getMessage());
    }
}
