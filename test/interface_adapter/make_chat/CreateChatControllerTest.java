package interface_adapter.make_chat;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.make_chat.MakeChatInputBoundary;
import use_case.make_chat.MakeChatInputData;
import interface_adapter.ViewModelManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the functionality of the CreateChatController class.
 */
class CreateChatControllerTest {
    private CreateChatController createChatController;
    private MakeChatInputBoundary mockMakeChatInputBoundary;
    private ViewModelManager mockViewModelManager;
    private User mockCurrentUser;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockMakeChatInputBoundary = Mockito.mock(MakeChatInputBoundary.class);
        mockViewModelManager = Mockito.mock(ViewModelManager.class);
        mockCurrentUser = Mockito.mock(User.class);
        createChatController = new CreateChatController(mockMakeChatInputBoundary, mockViewModelManager);
    }

    /**
     * Tests the createChat method of CreateChatController.
     * It verifies that the createChat method correctly captures the input data, invokes the input boundary,
     * updates the current user's chats, and fires the appropriate property change events.
     */
    @Test
    void testCreateChat() {
        String chatName = "Test Chat";
        String user1 = "Alice";
        String user2 = "Bob";
        LocalDateTime now = LocalDateTime.now();

        List<String> mockChats = new ArrayList<>();
        when(mockCurrentUser.getChats()).thenReturn(mockChats);
        when(mockViewModelManager.getCurrentUser()).thenReturn(mockCurrentUser);

        createChatController.createChat(chatName, user1, user2);

        // Verify that MakeChatInputBoundary's makeChat method was called with the correct input data
        ArgumentCaptor<MakeChatInputData> captor = ArgumentCaptor.forClass(MakeChatInputData.class);
        verify(mockMakeChatInputBoundary).makeChat(captor.capture());
        MakeChatInputData capturedData = captor.getValue();

        assertEquals(chatName, capturedData.getChatName());
        assertEquals(user1, capturedData.getUser_1());
        assertEquals(user2, capturedData.getUser_2());
        assertEquals(now.getDayOfMonth(), capturedData.getTime().getDayOfMonth());

        // Verify that the chat name was added to the current user's chats
        verify(mockCurrentUser).getChats();
        mockChats.add(chatName);
        verify(mockCurrentUser).getChats();

        // Verify that the current user was updated in the ViewModelManager
        verify(mockViewModelManager).setCurrentUser(mockCurrentUser);

        // Verify that the property change event was fired
        verify(mockViewModelManager).firePropertyChanged("chatsUpdated", null, null);
    }
}