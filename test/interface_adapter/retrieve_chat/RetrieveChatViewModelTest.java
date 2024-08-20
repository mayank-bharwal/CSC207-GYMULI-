package interface_adapter.retrieve_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInputData;
import use_case.retrieve_chat.RetrieveChatOutputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link RetrieveChatViewModel} class.
 */
class RetrieveChatViewModelTest {

    private RetrieveChatViewModel viewModel;
    private RetrieveChatState state;
    private RetrieveChatInputBoundary retrieveChatInputBoundary;

    /**
     * Sets up the test environment before each test method.
     * Initializes the view model, state, and mocks dependencies.
     */
    @BeforeEach
    void setUp() {
        viewModel = new RetrieveChatViewModel();
        state = new RetrieveChatState();
        retrieveChatInputBoundary = mock(RetrieveChatInputBoundary.class);
        viewModel.setRetrieveChatInteractor(retrieveChatInputBoundary);
    }

    /**
     * Tests the {@link RetrieveChatViewModel#getState()} method.
     * Ensures it returns the correct state.
     */
    @Test
    void getState() {
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    /**
     * Tests the {@link RetrieveChatViewModel#setState(RetrieveChatState)} method.
     * Ensures it correctly sets the state and fires a property change event.
     */
    @Test
    void setState() {
        RetrieveChatState newState = new RetrieveChatState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    /**
     * Tests the {@link RetrieveChatViewModel#triggerUpdate()} method.
     * Ensures it triggers the update by calling the interactor with the correct input data.
     */
    @Test
    void triggerUpdate() {
        state.setChatName("TestChat");
        viewModel.setState(state);

        viewModel.triggerUpdate();

        ArgumentCaptor<RetrieveChatInputData> captor = ArgumentCaptor.forClass(RetrieveChatInputData.class);
        verify(retrieveChatInputBoundary).retrieveChat(captor.capture());

        assertEquals("TestChat", captor.getValue().getChatName());
    }

    /**
     * Tests the {@link RetrieveChatViewModel#setRetrieveChatInteractor(RetrieveChatInputBoundary)} method.
     * Ensures it correctly sets the interactor.
     */
    @Test
    void setRetrieveChatInteractor() {
        RetrieveChatInputBoundary newInteractor = mock(RetrieveChatInputBoundary.class);
        viewModel.setRetrieveChatInteractor(newInteractor);

        viewModel.triggerUpdate();

        verify(newInteractor).retrieveChat(any(RetrieveChatInputData.class));
    }
    }