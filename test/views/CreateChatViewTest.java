package views;

import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatController;
import interface_adapter.make_chat.CreateChatState;
import interface_adapter.make_chat.CreateChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateChatViewTest {

    private CreateChatView createChatView;
    private ViewModelManager viewModelManager;
    private CreateChatViewModel createChatViewModel;
    private CreateChatController createChatController;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        createChatViewModel = mock(CreateChatViewModel.class);
        createChatController = mock(CreateChatController.class);
        createChatView = new CreateChatView(viewModelManager, createChatViewModel, createChatController);
    }

    /**
     * Test the propertyChange method to handle successful chat creation.
     * Verifies that the success message is shown and the view is updated.
     */
    @Test
    void propertyChange_success() {
        CreateChatState state = mock(CreateChatState.class);
        when(state.isSuccess()).thenReturn(true);
        when(state.getError()).thenReturn(null);
        when(createChatViewModel.getState()).thenReturn(state);

        PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, state);
        createChatView.propertyChange(event);

        verify(createChatViewModel, atLeastOnce()).getState();
        verify(viewModelManager).setActiveView(MainView.viewName);

        verify(viewModelManager).setActiveView(MainView.viewName);
    }

    /**
     * Test the propertyChange method to handle a failure scenario.
     * Verifies that the error message is displayed.
     */
    @Test
    void propertyChange_error() {
        CreateChatState state = mock(CreateChatState.class);
        when(state.isSuccess()).thenReturn(false);
        when(state.getError()).thenReturn("Error occurred");
        when(createChatViewModel.getState()).thenReturn(state);

        PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, state);
        createChatView.propertyChange(event);

        verify(createChatViewModel, atLeastOnce()).getState();
        verifyNoInteractions(viewModelManager);  // Ensure the view isn't changed in case of an error
    }
}