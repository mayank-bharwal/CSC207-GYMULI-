package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import interface_adapter.retrieve_chat.RetrieveChatState;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ChatView;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for the ChatViewFactory class.
 * This test ensures that the ChatViewFactory#create(ViewModelManager, SendMessageController, SendMessageViewModel,
 * RetrieveChatViewModel)
 * method correctly creates a ChatView object when provided with valid dependencies.
 */
class ChatViewFactoryTest {

    private ViewModelManager viewModelManager;
    private SendMessageController sendMessageController;
    private SendMessageViewModel sendMessageViewModel;
    private RetrieveChatViewModel retrieveChatViewModel;

    /**
     * Sets up the test environment by mocking the necessary dependencies.
     * Mocks include ViewModelManager, SendMessageController, SendMessageViewModel, and RetrieveChatViewModel.
     * Also mocks the behavior of RetrieveChatViewModel#getState() to prevent null pointer exceptions.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        sendMessageController = mock(SendMessageController.class);
        sendMessageViewModel = mock(SendMessageViewModel.class);
        retrieveChatViewModel = mock(RetrieveChatViewModel.class);

        RetrieveChatState retrieveChatState = mock(RetrieveChatState.class);
        when(retrieveChatViewModel.getState()).thenReturn(retrieveChatState);
        when(retrieveChatState.getAllMessages()).thenReturn(Collections.emptyList());
    }

    /**
     * Tests that the ChatViewFactory#create(ViewModelManager, SendMessageController, SendMessageViewModel,
     * RetrieveChatViewModel)
     * method successfully creates a non-null ChatView instance when valid dependencies are provided.
     */
    @Test
    void create() {
        ChatView chatView = ChatViewFactory.create(viewModelManager, sendMessageController, sendMessageViewModel,
                retrieveChatViewModel);

        assertNotNull(chatView, "ChatView should not be null");
    }
}