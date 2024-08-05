package use_case.retrieve_chat;

import entity.Chat;
import entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the RetrieveChatInteractor.
 * This class contains unit tests for the RetrieveChatInteractor,
 * ensuring that the chat retrieval process works as expected.
 */
class RetrieveChatInteractorTest {

    private RetrieveChatInteractor interactor;
    private RetrieveChatOutputBoundary presenter;
    private RetrieveChatUserDataAccessInterface chatDataAccessObject;
    private Chat mockChat;

    /**
     * Sets up the test environment before each test method.
     * Initializes the mock objects and the interactor instance.
     */
    @BeforeEach
    void setUp() {
        presenter = mock(RetrieveChatOutputBoundary.class);
        chatDataAccessObject = mock(RetrieveChatUserDataAccessInterface.class);
        interactor = new RetrieveChatInteractor(presenter, chatDataAccessObject);

        mockChat = mock(Chat.class);
        when(mockChat.getChatName()).thenReturn("TestChat");
        when(mockChat.getUsers()).thenReturn(new ArrayList<>(Arrays.asList("User1", "User2")));
        when(mockChat.getNoOfMembers()).thenReturn(2);
        when(mockChat.getAllmessages()).thenReturn(new ArrayList<Message>());
        when(mockChat.getTime()).thenReturn(LocalDateTime.now());
    }

    /**
     * Tests the success scenario of retrieving a chat.
     * Verifies that the interactor retrieves the chat and calls the presenter's
     * presentChat method with the correct RetrieveChatOutputData.
     */
    @Test
    void retrieveChat_success() {
        RetrieveChatInputData inputData = new RetrieveChatInputData("TestChat");

        when(chatDataAccessObject.getChat("TestChat")).thenReturn(mockChat);

        interactor.retrieveChat(inputData);

        ArgumentCaptor<RetrieveChatOutputData> captor = ArgumentCaptor.forClass(RetrieveChatOutputData.class);
        verify(presenter).presentChat(captor.capture());

        RetrieveChatOutputData outputData = captor.getValue();
        assertNotNull(outputData);
        assertEquals("TestChat", outputData.getChatName());
        assertEquals(new ArrayList<>(Arrays.asList("User1", "User2")), outputData.getUsers());
        assertEquals(2, outputData.getNoOfmembers());
        assertEquals(new ArrayList<Message>(), outputData.getAllMessages());
        assertNotNull(outputData.getTime());
    }
}