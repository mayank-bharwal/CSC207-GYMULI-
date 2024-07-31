package interface_adapter.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.retrieve_chat.RetrieveChatOutputData;
import entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the RetrieveChatPresenter class.
 */
public class RetrieveChatPresenterTest {
    private RetrieveChatPresenter retrieveChatPresenter;
    private RetrieveChatViewModel mockRetrieveChatViewModel;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockRetrieveChatViewModel = Mockito.mock(RetrieveChatViewModel.class);
        retrieveChatPresenter = new RetrieveChatPresenter(mockRetrieveChatViewModel);
    }

    /**
     * Tests the prepareSuccessView method of RetrieveChatPresenter.
     * It verifies that the method correctly updates the view model upon successful chat retrieval.
     */
    @Test
    void testPrepareSuccessView() {
        // Create a mock RetrieveChatOutputData object
        String chatName = "Test Chat";
        ArrayList<String> users = new ArrayList<>(Arrays.asList("Alice", "Barry", "Charlie"));
        int noOfMembers = users.size();
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.add(new Message("Test Chat", "Alice", "Barry", "Hello", LocalDateTime.now()));
        LocalDateTime time = LocalDateTime.now();
        boolean useCaseFailed = false;

        RetrieveChatOutputData chatInfo = new RetrieveChatOutputData(chatName, users, noOfMembers, allMessages, time, useCaseFailed);

        retrieveChatPresenter.prepareSuccessView(chatInfo);

        ArgumentCaptor<RetrieveChatState> captor = ArgumentCaptor.forClass(RetrieveChatState.class);
        verify(mockRetrieveChatViewModel).setState(captor.capture());
        RetrieveChatState capturedState = captor.getValue();

        assertEquals(chatInfo.getChatName(), capturedState.getChatName());
        assertEquals(chatInfo.getUsers(), capturedState.getUsers());
        assertEquals(chatInfo.getNoOfmembers(), capturedState.getNoOfMembers());
        assertEquals(chatInfo.getAllMessages(), capturedState.getAllMessages());
    }

    /**
     * Tests the prepareFailView method of RetrieveChatPresenter.
     * It verifies that the method correctly updates the view model with the appropriate error message upon chat retrieval failure.
     */
    @Test
    void testPrepareFailView() {
        String error = "Error retrieving chat";

        retrieveChatPresenter.prepareFailView(error);

        ArgumentCaptor<RetrieveChatState> captor = ArgumentCaptor.forClass(RetrieveChatState.class);
        verify(mockRetrieveChatViewModel).setState(captor.capture());
        RetrieveChatState capturedState = captor.getValue();

        assertEquals(error, capturedState.getError());
    }
}
