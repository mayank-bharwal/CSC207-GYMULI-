package use_case.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RetrieveChatInputData.
 */
class RetrieveChatInputDataTest {

    private RetrieveChatInputData inputData;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        inputData = new RetrieveChatInputData("TestChat");
    }

    /**
     * Tests the getChatName method.
     */
    @Test
    void getChatName() {
        assertEquals("TestChat", inputData.getChatName());
    }
}