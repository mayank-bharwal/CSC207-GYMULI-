import entity.Message;
import entity.MessageFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals("Test message", message.getMessage());
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
        assertThrows(NullPointerException.class, () -> {
            message.setChatName(null);
        });
    }

    @Test
    void testSetSenderToNull() {
        assertThrows(NullPointerException.class, () -> {
            message.setSender(null);
        });
    }

    @Test
    void testSetReceiverToNull() {
        assertThrows(NullPointerException.class, () -> {
            message.setReceiver(null);
        });
    }

    @Test
    void testSetMessageToNull() {
        assertThrows(NullPointerException.class, () -> {
            message.setMessage(null);
        });
    }

    @Test
    void testSetChatNameWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setChatName("");
        });
    }

    @Test
    void testSetSenderWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setSender("");
        });
    }

    @Test
    void testSetReceiverWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setReceiver("");
        });
    }

    @Test
    void testSetMessageWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setMessage("");
        });
    }

    @Test
    void testSetChatNameWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setChatName(" ");
        });
    }

    @Test
    void testSetSenderWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setSender(" ");
        });
    }

    @Test
    void testSetReceiverWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setReceiver(" ");
        });
    }

    @Test
    void testSetMessageWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            message.setMessage(" ");
        });
    }
}
