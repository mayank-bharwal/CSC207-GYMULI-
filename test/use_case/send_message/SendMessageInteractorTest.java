package use_case.send_message;

import entity.Message;
import entity.MessageFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SendMessageInteractor class.
 */
class SendMessageInteractorTest {

    private SendMessageInteractor interactor;
    private SendMessageUserDataAccessInterface userDataAccessObject;
    private SendMessageOutputBoundary userPresenter;
    private MessageFactory messageFactory;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(SendMessageUserDataAccessInterface.class);
        userPresenter = mock(SendMessageOutputBoundary.class);
        messageFactory = mock(MessageFactory.class);
        interactor = new SendMessageInteractor(userDataAccessObject, userPresenter, messageFactory);
    }

    /**
     * Tests the execute method with valid input data.
     * Verifies that a message is created and saved, and success view is prepared.
     */
    @Test
    void execute_withValidInput() {
        String chatName = "Test Chat";
        String messageContent = "Hello, World!";
        String sender = "senderUser";
        String receiver = "receiverUser";
        LocalDateTime now = LocalDateTime.now();
        SendMessageInputData inputData = new SendMessageInputData(chatName, messageContent, now, sender, receiver);

        when(userDataAccessObject.filter(messageContent)).thenReturn(messageContent);
        Message mockMessage = mock(Message.class);
        when(mockMessage.getMessage()).thenReturn(messageContent);
        when(mockMessage.getChatName()).thenReturn(chatName);
        when(messageFactory.createMessage(eq(chatName), eq(sender), eq(receiver), eq(messageContent), any(LocalDateTime.class))).thenReturn(mockMessage);

        interactor.execute(inputData);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(userDataAccessObject).saveMessage(messageCaptor.capture());
        Message savedMessage = messageCaptor.getValue();
        assertNotNull(savedMessage);
        assertEquals(mockMessage, savedMessage);

        ArgumentCaptor<SendMessageOutputData> outputDataCaptor = ArgumentCaptor.forClass(SendMessageOutputData.class);
        verify(userPresenter).prepareSuccessView(outputDataCaptor.capture());

        SendMessageOutputData outputData = outputDataCaptor.getValue();
        assertNotNull(outputData);
        assertEquals(messageContent, mockMessage.getMessage());
        assertEquals(chatName, outputData.getChatName("Test Chat"));
    }

    /**
     * Tests the execute method with an empty message.
     * Verifies that the fail view is prepared with an appropriate message.
     */
    @Test
    void execute_withEmptyMessage() {
        String chatName = "Test Chat";
        String message = "";
        String sender = "senderUser";
        String receiver = "receiverUser";
        LocalDateTime now = LocalDateTime.now();
        SendMessageInputData inputData = new SendMessageInputData(chatName, message, now, sender, receiver);

        when(userDataAccessObject.filter(message)).thenReturn(message);

        interactor.execute(inputData);

        verify(userDataAccessObject, never()).saveMessage(any(Message.class));
        verify(userPresenter).prepareFailView("Chat field is empty");
    }
}