package interface_adapter.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class RetrieveChatControllerTest {

    private RetrieveChatController retrieveChatController;
    private RetrieveChatInputBoundary mockRetrieveChatInputBoundary;

    @BeforeEach
    void setUp() {
        mockRetrieveChatInputBoundary = Mockito.mock(RetrieveChatInputBoundary.class);
        retrieveChatController = new RetrieveChatController(mockRetrieveChatInputBoundary);
    }

    @Test
    void testRetrieveChat() {
        String chatName = "TestChat";
        ArgumentCaptor<RetrieveChatInputData> captor = ArgumentCaptor.forClass(RetrieveChatInputData.class);

        retrieveChatController.retrieveChat(chatName);

        verify(mockRetrieveChatInputBoundary).retrieveChat(captor.capture());
        RetrieveChatInputData capturedInputData = captor.getValue();

        assertEquals(chatName, capturedInputData.getChatName());
    }
}