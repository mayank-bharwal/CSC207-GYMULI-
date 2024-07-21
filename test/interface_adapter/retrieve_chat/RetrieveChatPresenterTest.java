package interface_adapter.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.retrieve_chat.RetrieveChatOutputData;
import entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveChatPresenterTest {
    private RetrieveChatPresenter retrieveChatPresenter;
    private RetrieveChatViewModel mockRetrieveChatViewModel;

    @BeforeEach
    void setUp() {
        mockRetrieveChatViewModel = Mockito.mock(RetrieveChatViewModel.class);
        retrieveChatPresenter = new RetrieveChatPresenter(mockRetrieveChatViewModel);
    }

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

        RetrieveChatState expectedState = new RetrieveChatState();
        expectedState.setChatName(chatInfo.getChatName());
        expectedState.setUsers(chatInfo.getUsers());
        expectedState.setNoOfMembers(chatInfo.getNoOfmembers());
        expectedState.setAllMessages(chatInfo.getAllMessages());

        verify(mockRetrieveChatViewModel).setState(expectedState);
    }

    @Test
    void testPrepareFailView() {
        String error = "Error retrieving chat";

        retrieveChatPresenter.prepareFailView(error);

        RetrieveChatState expectedState = new RetrieveChatState();
        expectedState.setError(error);

        verify(mockRetrieveChatViewModel).setState(expectedState);
    }
}