package interface_adapter.delete_chat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DeleteChatState class.
 */
class DeleteChatStateTest {

    /**
     * Tests the isSuccess method.
     * Verifies that the correct success state is returned.
     */
    @Test
    void isSuccess() {
        DeleteChatState state = new DeleteChatState();
        state.setSuccess(true);
        assertTrue(state.isSuccess());

        state.setSuccess(false);
        assertFalse(state.isSuccess());
    }

    /**
     * Tests the setSuccess method.
     * Verifies that the success state is correctly set.
     */
    @Test
    void setSuccess() {
        DeleteChatState state = new DeleteChatState();
        state.setSuccess(true);
        assertTrue(state.isSuccess());

        state.setSuccess(false);
        assertFalse(state.isSuccess());
    }

    /**
     * Tests the getError method.
     * Verifies that the correct error message is returned.
     */
    @Test
    void getError() {
        DeleteChatState state = new DeleteChatState();
        state.setError("Error occurred");
        assertEquals("Error occurred", state.getError());
    }

    /**
     * Tests the setError method.
     * Verifies that the error message is correctly set.
     */
    @Test
    void setError() {
        DeleteChatState state = new DeleteChatState();
        state.setError("Error occurred");
        assertEquals("Error occurred", state.getError());
    }
}