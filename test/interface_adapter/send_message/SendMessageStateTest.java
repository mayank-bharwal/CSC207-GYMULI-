package interface_adapter.send_message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SendMessageState class.
 */
class SendMessageStateTest {

    /**
     * Tests the isSuccess method.
     * Verifies that the success flag is correctly retrieved.
     */
    @Test
    void isSuccess() {
        SendMessageState state = new SendMessageState();
        state.setSuccess(true);
        assertTrue(state.isSuccess());

        state.setSuccess(false);
        assertFalse(state.isSuccess());
    }

    /**
     * Tests the setSuccess method.
     * Verifies that the success flag is correctly set.
     */
    @Test
    void setSuccess() {
        SendMessageState state = new SendMessageState();
        state.setSuccess(true);
        assertTrue(state.isSuccess());

        state.setSuccess(false);
        assertFalse(state.isSuccess());
    }

    /**
     * Tests the getErrorMessage method.
     * Verifies that the error message is correctly retrieved.
     */
    @Test
    void getErrorMessage() {
        SendMessageState state = new SendMessageState();
        state.setErrorMessage("An error occurred");
        assertEquals("An error occurred", state.getErrorMessage());
    }

    /**
     * Tests the setErrorMessage method.
     * Verifies that the error message is correctly set.
     */
    @Test
    void setErrorMessage() {
        SendMessageState state = new SendMessageState();
        state.setErrorMessage("An error occurred");
        assertEquals("An error occurred", state.getErrorMessage());

        state.setErrorMessage("Another error occurred");
        assertEquals("Another error occurred", state.getErrorMessage());
    }
}