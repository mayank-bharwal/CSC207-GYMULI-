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

class SendMessageControllerTest {
    private SendMessageController sendMessageController;
    private SendMessageInputBoundary mockSendMessageInputBoundary;

    @BeforeEach
    void setUp() {
        mockSendMessageInputBoundary = Mockito.mock(SendMessageInputBoundary.class);
        sendMessageController = new SendMessageController(mockSendMessageInputBoundary);
    }

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