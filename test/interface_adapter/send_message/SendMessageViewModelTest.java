package interface_adapter.send_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SendMessageViewModel class.
 */
class SendMessageViewModelTest {

    private SendMessageViewModel viewModel;
    private SendMessageState mockState;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        viewModel = new SendMessageViewModel();
        mockState = mock(SendMessageState.class);
        listener = mock(PropertyChangeListener.class);
    }

    /**
     * Tests the getState method.
     * Verifies that the correct state is returned.
     */
    @Test
    void getState() {
        viewModel.setState(mockState);
        assertEquals(mockState, viewModel.getState());
    }

    /**
     * Tests the setState method.
     * Verifies that the state is correctly set.
     */
    @Test
    void setState() {
        viewModel.setState(mockState);
        assertEquals(mockState, viewModel.getState());
    }

    /**
     * Tests the firePropertyChange method.
     * Verifies that the property change event is fired when the state changes.
     */
    @Test
    void firePropertyChange() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.setState(mockState);
        viewModel.firePropertyChange();

        verify(listener, times(1)).propertyChange(any());
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that a property change listener is correctly added.
     */
    @Test
    void addPropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChange();

        verify(listener, times(1)).propertyChange(any());
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that a property change listener is correctly removed.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChange();

        verify(listener, times(0)).propertyChange(any());
    }
}
