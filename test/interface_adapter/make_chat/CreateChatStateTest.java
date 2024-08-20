package interface_adapter.make_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CreateChatState} class.
 */
class CreateChatStateTest {

    private CreateChatState createChatState;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of {@link CreateChatState}.
     */
    @BeforeEach
    void setUp() {
        createChatState = new CreateChatState();
    }

    /**
     * Tests the {@link CreateChatState#isSuccess()} method.
     * Ensures it returns the correct success state.
     */
    @Test
    void isSuccess() {
        createChatState.setSuccess(true);
        assertTrue(createChatState.isSuccess());

        createChatState.setSuccess(false);
        assertFalse(createChatState.isSuccess());
    }

    /**
     * Tests the {@link CreateChatState#setSuccess(boolean)} method.
     * Ensures it correctly sets the success state.
     */
    @Test
    void setSuccess() {
        createChatState.setSuccess(true);
        assertTrue(createChatState.isSuccess());

        createChatState.setSuccess(false);
        assertFalse(createChatState.isSuccess());
    }

    /**
     * Tests the {@link CreateChatState#getError()} method.
     * Ensures it returns the correct error message.
     */
    @Test
    void getError() {
        createChatState.setError("Test Error");
        assertEquals("Test Error", createChatState.getError());
    }

    /**
     * Tests the {@link CreateChatState#setError(String)} method.
     * Ensures it correctly sets the error message.
     */
    @Test
    void setError() {
        createChatState.setError("Test Error");
        assertEquals("Test Error", createChatState.getError());
    }
}