package interface_adapter.delete_chat;

import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.delete_chat.DeleteChatInputBoundary;
import use_case.delete_chat.DeleteChatInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DeleteChatController class.
 */
class DeleteChatControllerTest {

    private DeleteChatController deleteChatController;
    private DeleteChatInputBoundary mockDeleteChatInputBoundary;
    private ViewModelManager mockViewModelManager;

    /**
     * Sets up the test environment before each test method.
     * Initializes the DeleteChatController and mocks its dependencies.
     */
    @BeforeEach
    void setUp() {
        mockDeleteChatInputBoundary = mock(DeleteChatInputBoundary.class);
        mockViewModelManager = mock(ViewModelManager.class);
        deleteChatController = new DeleteChatController(mockDeleteChatInputBoundary, mockViewModelManager);
    }

    /**
     * Tests the deleteChat method of DeleteChatController.
     * It verifies that the method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void deleteChat() {
        String chatname = "TestChat";
        ArgumentCaptor<DeleteChatInputData> captor = ArgumentCaptor.forClass(DeleteChatInputData.class);

        deleteChatController.deleteChat(chatname);

        verify(mockDeleteChatInputBoundary).deleteChat(captor.capture());
        DeleteChatInputData capturedInputData = captor.getValue();

        assertEquals(chatname, capturedInputData.getChatname());
    }
}