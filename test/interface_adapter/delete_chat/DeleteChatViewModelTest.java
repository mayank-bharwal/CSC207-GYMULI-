package interface_adapter.delete_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DeleteChatViewModel class.
 */
class DeleteChatViewModelTest {

    private DeleteChatViewModel viewModel;
    private DeleteChatState state;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        viewModel = new DeleteChatViewModel();
        state = new DeleteChatState();
    }

    /**
     * Tests the getState method.
     * Verifies that the correct state is returned.
     */
    @Test
    void getState() {
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    /**
     * Tests the firePropertyChanged method.
     * Verifies that the property change event is fired.
     */
    @Test
    void firePropertyChanged() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        verify(listener).propertyChange(any());
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that a property change listener is correctly added.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        verify(listener).propertyChange(any());
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that a property change listener is correctly removed.
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
        viewModel.removePropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        verify(listener, never()).propertyChange(any());
    }
}