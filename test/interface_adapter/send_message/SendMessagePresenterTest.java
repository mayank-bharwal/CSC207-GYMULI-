package interface_adapter.send_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.send_message.SendMessageOutputData;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendMessagePresenterTest {
    private SendMessagePresenter sendMessagePresenter;
    private SendMessageViewModel mockSendMessageViewModel;

    @BeforeEach
    void setUp() {
        mockSendMessageViewModel = Mockito.mock(SendMessageViewModel.class);
        sendMessagePresenter = new SendMessagePresenter(mockSendMessageViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        SendMessageOutputData message = new SendMessageOutputData("Alice", LocalDateTime.now(),
                false, "Chat");

        SendMessageState mockState = Mockito.mock(SendMessageState.class);
        when(mockSendMessageViewModel.getState()).thenReturn(mockState);

        sendMessagePresenter.prepareSuccessView(message);

        verify(mockState).setSuccess(true);
        verify(mockState).setErrorMessage(null);
        verify(mockSendMessageViewModel).setState(mockState);
        verify(mockSendMessageViewModel).firePropertyChange();
    }

    @Test
    void testPrepareFailView() {
        String error = "Error sending message";

        SendMessageState mockState = Mockito.mock(SendMessageState.class);
        when(mockSendMessageViewModel.getState()).thenReturn(mockState);

        sendMessagePresenter.prepareFailView(error);

        verify(mockState).setSuccess(false);
        verify(mockState).setErrorMessage(error);
        verify(mockSendMessageViewModel).setState(mockState);
        verify(mockSendMessageViewModel).firePropertyChange();
    }
}