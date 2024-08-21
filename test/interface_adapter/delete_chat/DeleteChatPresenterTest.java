package interface_adapter.delete_chat;

import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.delete_chat.DeleteChatOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DeleteChatPresenter class.
 */
class DeleteChatPresenterTest {

    private DeleteChatPresenter deleteChatPresenter;
    private DeleteChatViewModel mockDeleteChatViewModel;
    private ViewModelManager mockViewModelManager;

    /**
     * Sets up the test environment before each test method.
     * Initializes the DeleteChatPresenter and mocks its dependencies.
     */
    @BeforeEach
    void setUp() {
        mockDeleteChatViewModel = mock(DeleteChatViewModel.class);
        mockViewModelManager = mock(ViewModelManager.class);
        deleteChatPresenter = new DeleteChatPresenter(mockDeleteChatViewModel, mockViewModelManager);
    }

    /**
     * Tests the setPassView method.
     * Verifies that the view model state is updated to indicate success and that property change events are fired.
     */
    @Test
    void setPassView() {
        DeleteChatState mockState = mock(DeleteChatState.class);
        when(mockDeleteChatViewModel.getState()).thenReturn(mockState);

        String chatname = "";
        ArrayList<String> members = new ArrayList<>();
        boolean failView = false;

        DeleteChatOutputData chatData = new DeleteChatOutputData(chatname, members, failView);

        deleteChatPresenter.setPassView(chatData);

        verify(mockState).setSuccess(true);
        verify(mockState).setError(null);
        verify(mockDeleteChatViewModel).setState(mockState);
        verify(mockDeleteChatViewModel).firePropertyChanged();
        verify(mockViewModelManager).firePropertyChanged("ChatDeleted", null, null);
    }

    /**
     * Tests the setFailView method.
     * Verifies that the view model state is updated to indicate failure and that property change events are fired.
     */
    @Test
    void setFailView() {
        DeleteChatState mockState = mock(DeleteChatState.class);
        when(mockDeleteChatViewModel.getState()).thenReturn(mockState);

        String errorMessage = "Failed to delete chat";

        deleteChatPresenter.setFailView(errorMessage);

        verify(mockState).setSuccess(false);
        verify(mockState).setError(errorMessage);
        verify(mockDeleteChatViewModel).setState(mockState);
        verify(mockDeleteChatViewModel).firePropertyChanged();
    }
}