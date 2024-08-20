package interface_adapter.make_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CreateChatViewModel} class.
 */
class CreateChatViewModelTest {

    private CreateChatViewModel viewModel;
    private CreateChatState state;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of {@link CreateChatViewModel} and mocks dependencies.
     */
    @BeforeEach
    void setUp() {
        viewModel = new CreateChatViewModel();
        state = new CreateChatState();
        listener = mock(PropertyChangeListener.class);
    }

    /**
     * Tests the {@link CreateChatViewModel#getState()} method.
     * Ensures it returns the correct state.
     */
    @Test
    void getState() {
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    /**
     * Tests the {@link CreateChatViewModel#setState(CreateChatState)} method.
     * Ensures it correctly sets the state.
     */
    @Test
    void setState() {
        CreateChatState newState = new CreateChatState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    /**
     * Tests the {@link CreateChatViewModel#firePropertyChanged()} method.
     * Ensures that a property change event is fired.
     */
    @Test
    void firePropertyChanged() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
        verify(listener, times(1)).propertyChange(any());
    }

    /**
     * Tests the {@link CreateChatViewModel#addPropertyChangeListener(PropertyChangeListener)} method.
     * Ensures that a property change listener is added.
     */
    @Test
    void addPropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();
        verify(listener, times(1)).propertyChange(any());
    }

    /**
     * Tests the {@link CreateChatViewModel#removePropertyChangeListener(PropertyChangeListener)} method.
     * Ensures that a property change listener is removed.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChanged();
        verify(listener, never()).propertyChange(any());
    }
}