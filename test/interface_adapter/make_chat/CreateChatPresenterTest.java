package interface_adapter.make_chat;

import entity.Chat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.make_chat.MakeChatOutputData;
import entity.ChatFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the functionality of the CreateChatPresenter class.
 */
class CreateChatPresenterTest {
    private CreateChatPresenter createChatPresenter;
    private CreateChatViewModel mockCreateChatViewModel;
    private ChatFactory chatFactory;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockCreateChatViewModel = Mockito.mock(CreateChatViewModel.class);
        createChatPresenter = new CreateChatPresenter(mockCreateChatViewModel);
        chatFactory = new ChatFactory();
    }

    /**
     * Tests the setPassView method of CreateChatPresenter.
     * It verifies that the method correctly updates the view model upon successful chat creation.
     */
    @Test
    void testSetPassView() {
        Chat chat = chatFactory.createChat("Test Chat", new ArrayList<>(), 0, new ArrayList<>(), LocalDateTime.now());
        MakeChatOutputData chatOutputData = new MakeChatOutputData(chat, false);

        CreateChatState mockState = Mockito.mock(CreateChatState.class);
        when(mockCreateChatViewModel.getState()).thenReturn(mockState);

        createChatPresenter.setPassView(chatOutputData);

        verify(mockState).setSuccess(true);
        verify(mockState).setError(null);
        verify(mockCreateChatViewModel).setState(mockState);
        verify(mockCreateChatViewModel).firePropertyChanged();
    }

    /**
     * Tests the setFailView method of CreateChatPresenter.
     * It verifies that the method correctly updates the view model with the appropriate error message upon chat creation failure.
     */
    @Test
    void testSetFailView() {
        String error = "Error creating chat";

        CreateChatState mockState = Mockito.mock(CreateChatState.class);
        when(mockCreateChatViewModel.getState()).thenReturn(mockState);

        createChatPresenter.setFailView(error);

        verify(mockState).setError(error);
        verify(mockState).setSuccess(false);
        verify(mockCreateChatViewModel).setState(mockState);
        verify(mockCreateChatViewModel).firePropertyChanged();
    }
}