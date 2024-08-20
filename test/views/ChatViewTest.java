package views;

import interface_adapter.ViewModelManager;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import interface_adapter.retrieve_chat.RetrieveChatState;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the ChatView class.
 */
class ChatViewTest {
    private ChatView chatView;
    private ViewModelManager viewModelManager;
    private SendMessageController sendMessageController;
    private SendMessageViewModel sendMessageViewModel;
    private RetrieveChatViewModel retrieveChatViewModel;

    /**
     * Sets up the test environment before each test.
     * Mocks the necessary dependencies and creates an instance of ChatView.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        sendMessageController = mock(SendMessageController.class);
        sendMessageViewModel = mock(SendMessageViewModel.class);
        retrieveChatViewModel = mock(RetrieveChatViewModel.class);

        User currentUser = mock(User.class);
        when(currentUser.getUsername()).thenReturn("senderUser");
        when(viewModelManager.getCurrentUser()).thenReturn(currentUser);

        RetrieveChatState chatState = mock(RetrieveChatState.class);
        when(chatState.getUsers()).thenReturn(new ArrayList<>(Arrays.asList("senderUser", "receiverUser")));
        when(chatState.getChatName()).thenReturn("Test Chat");
        when(retrieveChatViewModel.getState()).thenReturn(chatState);

        chatView = new ChatView(viewModelManager, sendMessageController, sendMessageViewModel, retrieveChatViewModel);
    }

    /**
     * Tests the propertyChange method of the ChatView class.
     * Verifies the behavior when "state" and "activeView" properties change.
     */
    @Test
    void propertyChange() {
        PropertyChangeEvent stateChangeEvent = new PropertyChangeEvent(this, "state", null, null);
        chatView.propertyChange(stateChangeEvent);
        verify(retrieveChatViewModel, atLeastOnce()).getState();

        PropertyChangeEvent activeViewChangeEventToChatView = new PropertyChangeEvent(this, "activeView", null, ChatView.viewName);
        chatView.propertyChange(activeViewChangeEventToChatView);

        PropertyChangeEvent activeViewChangeEventToAnotherView = new PropertyChangeEvent(this, "activeView", null, "AnotherView");
        chatView.propertyChange(activeViewChangeEventToAnotherView);
    }
}