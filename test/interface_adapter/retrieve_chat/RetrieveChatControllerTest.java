package interface_adapter.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the RetrieveChatController class.
 */
public class RetrieveChatControllerTest {

    private RetrieveChatController retrieveChatController;
    private RetrieveChatInputBoundary mockRetrieveChatInputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockRetrieveChatInputBoundary = Mockito.mock(RetrieveChatInputBoundary.class);
        retrieveChatController = new RetrieveChatController(mockRetrieveChatInputBoundary);
    }

    /**
     * Tests the retrieveChat method of RetrieveChatController.
     * It verifies that the method correctly captures the input data and passes it to the input boundary.
     */
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