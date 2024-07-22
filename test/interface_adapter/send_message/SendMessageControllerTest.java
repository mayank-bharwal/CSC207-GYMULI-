package interface_adapter.send_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the SendMessageController class.
 */
class SendMessageControllerTest {
    private SendMessageController sendMessageController;
    private SendMessageInputBoundary mockSendMessageInputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockSendMessageInputBoundary = Mockito.mock(SendMessageInputBoundary.class);
        sendMessageController = new SendMessageController(mockSendMessageInputBoundary);
    }

    /**
     * Tests the execute method of SendMessageController.
     * It verifies that the method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void testExecute() {
        String chatName = "Chat";
        String message = "Test message";
        LocalDateTime time = LocalDateTime.now();
        String sender = "Alice";
        String receiver = "Barry";

        sendMessageController.execute(chatName, message, time, sender, receiver);

        ArgumentCaptor<SendMessageInputData> captor = ArgumentCaptor.forClass(SendMessageInputData.class);
        verify(mockSendMessageInputBoundary).execute(captor.capture());
        SendMessageInputData capturedData = captor.getValue();

        assertEquals(chatName, capturedData.getChatName());
        assertEquals(message, capturedData.getMessage());
        assertEquals(sender, capturedData.getSender());
        assertEquals(receiver, capturedData.getReceiver());
    }
}