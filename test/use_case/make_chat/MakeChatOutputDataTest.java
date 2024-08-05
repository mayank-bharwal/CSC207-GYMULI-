package use_case.make_chat;

import entity.Chat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MakeChatOutputData class.
 */
class MakeChatOutputDataTest {

    private Chat chat;
    private MakeChatOutputData outputData;

    /**
     * Sets up the test environment before each test.
     * Initializes the necessary mock objects and the MakeChatOutputData instance.
     */
    @BeforeEach
    void setUp() {
        chat = Mockito.mock(Chat.class);
        outputData = new MakeChatOutputData(chat, false);
    }

    /**
     * Tests the getCurrentChat method.
     * Verifies that the method returns the correct chat instance.
     */
    @Test
    void getCurrentChat() {
        assertEquals(chat, outputData.getCurentChat());
    }
}