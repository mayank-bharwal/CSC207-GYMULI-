package use_case.delete_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DeleteChatInputData.
 */
class DeleteChatInputDataTest {

    private DeleteChatInputData deleteChatInputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        deleteChatInputData = new DeleteChatInputData("TestChat");
    }

    /**
     * Tests the getChatName method.
     * Ensures that it returns the correct chat name.
     */
    @Test
    void getChatName() {
        assertEquals("TestChat", deleteChatInputData.getChatname());
    }
}