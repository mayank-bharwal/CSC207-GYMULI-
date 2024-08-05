package use_case.make_chat;

import entity.Chat;
import entity.ChatFactory;
import entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the MakeChatInteractor class.
 */
class MakeChatInteractorTest {

    private MakeChatInteractor interactor;
    private MakeChatUserDataAccessInterface userDataAccess;
    private MakeChatOutputBoundary outputBoundary;
    private ChatFactory chatFactory;

    /**
     * Sets up the test environment before each test.
     * Initializes the necessary mock objects and the MakeChatInteractor instance.
     */
    @BeforeEach
    void setUp() {
        userDataAccess = mock(MakeChatUserDataAccessInterface.class);
        outputBoundary = mock(MakeChatOutputBoundary.class);
        chatFactory = mock(ChatFactory.class);
        interactor = new MakeChatInteractor(userDataAccess, outputBoundary, chatFactory);
    }

    /**
     * Tests the makeChat method for a successful chat creation.
     * Verifies that the chat is created and saved correctly, and the pass view is set with the correct output data.
     */
    @Test
    void makeChat_successful() {
        String chatName = "Test Chat";
        String user1 = "User1";
        String user2 = "User2";
        LocalDateTime time = LocalDateTime.now();
        MakeChatInputData inputData = new MakeChatInputData(chatName, user1, user2, time);

        when(userDataAccess.ChatExists(chatName)).thenReturn(false);
        when(userDataAccess.UserExists(user2)).thenReturn(true);
        Chat chat = mock(Chat.class);

        when(chatFactory.createChat(eq(chatName), any(ArrayList.class), eq(2), any(ArrayList.class), any(LocalDateTime.class))).thenReturn(chat);

        interactor.makeChat(inputData);

        ArgumentCaptor<MakeChatOutputData> outputDataCaptor = ArgumentCaptor.forClass(MakeChatOutputData.class);
        verify(userDataAccess).saveChat(eq(user1), eq(user2), eq(chat));
        verify(outputBoundary).setPassView(outputDataCaptor.capture());

        MakeChatOutputData outputData = outputDataCaptor.getValue();
        assertNotNull(outputData);
        assertEquals(chat, outputData.getCurentChat());
    }

    /**
     * Tests the makeChat method when the chat name already exists.
     * Verifies that the fail view is set with the appropriate message and the chat is not saved.
     */
    @Test
    void makeChat_chatNameAlreadyExists() {
        String chatName = "Test Chat";
        String user1 = "User1";
        String user2 = "User2";
        LocalDateTime time = LocalDateTime.now();
        MakeChatInputData inputData = new MakeChatInputData(chatName, user1, user2, time);

        when(userDataAccess.ChatExists(chatName)).thenReturn(true);

        interactor.makeChat(inputData);

        verify(outputBoundary).setFailView("Chat name already exists");
        verify(userDataAccess, never()).saveChat(anyString(), anyString(), any(Chat.class));
    }

    /**
     * Tests the makeChat method when the specified user does not exist.
     * Verifies that the fail view is set with the appropriate message and the chat is not saved.
     */
    @Test
    void makeChat_userDoesNotExist() {
        String chatName = "Test Chat";
        String user1 = "User1";
        String user2 = "NonexistentUser";
        LocalDateTime time = LocalDateTime.now();
        MakeChatInputData inputData = new MakeChatInputData(chatName, user1, user2, time);

        when(userDataAccess.ChatExists(chatName)).thenReturn(false);
        when(userDataAccess.UserExists(user2)).thenReturn(false);

        interactor.makeChat(inputData);

        verify(outputBoundary).setFailView("User doesn't exist");
        verify(userDataAccess, never()).saveChat(anyString(), anyString(), any(Chat.class));
    }
}
