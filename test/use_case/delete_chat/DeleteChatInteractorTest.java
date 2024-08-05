package use_case.delete_chat;

import entity.Chat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the DeleteChatInteractor.
 */
class DeleteChatInteractorTest {

    private DeleteChatInteractor interactor;
    private DeleteChatOutputBoundary outputBoundary;
    private DeleteChatUserDataAccessInterafce deleteChatDAO;
    private Chat mockChat;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = mock(DeleteChatOutputBoundary.class);
        deleteChatDAO = mock(DeleteChatUserDataAccessInterafce.class);
        interactor = new DeleteChatInteractor(deleteChatDAO, outputBoundary);

        List<String> users = new ArrayList<>();
        users.add("User1");
        users.add("User2");

        mockChat = mock(Chat.class);
        when(mockChat.getChatName()).thenReturn("TestChat");
        when(mockChat.getUsers()).thenReturn((ArrayList<String>) users);
    }

    /**
     * Tests the deleteChat method for a successful deletion.
     * Ensures that the chat is deleted and the pass view is set.
     */
    @Test
    void deleteChat_success() {
        DeleteChatInputData inputData = new DeleteChatInputData("TestChat");

        when(deleteChatDAO.ChatExists("TestChat")).thenReturn(true);
        when(deleteChatDAO.getChat("TestChat")).thenReturn(mockChat);

        interactor.deleteChat(inputData);

        verify(deleteChatDAO).DeleteChat("TestChat");
        verify(outputBoundary).setPassView(any(DeleteChatOutputData.class));
    }

    /**
     * Tests the deleteChat method for a failure when the chat does not exist.
     * Ensures that the delete operation is not performed and the fail view is set.
     */
    @Test
    void deleteChat_failure_chatDoesNotExist() {
        DeleteChatInputData inputData = new DeleteChatInputData("NonExistentChat");

        when(deleteChatDAO.ChatExists("NonExistentChat")).thenReturn(false);

        interactor.deleteChat(inputData);

        verify(deleteChatDAO, never()).DeleteChat("NonExistentChat");
        verify(outputBoundary).setFailView("The chat does not exist");
    }
}
