package interface_adapter.refresh_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RefreshUserViewModel class.
 */
class RefreshUserViewModelTest {

    private RefreshUserViewModel viewModel;
    private RefreshUserState state;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new instance of RefreshUserViewModel and mocks the PropertyChangeListener.
     */
    @BeforeEach
    void setUp() {
        viewModel = new RefreshUserViewModel();
        state = new RefreshUserState();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests the setState method.
     * Verifies that the state is correctly updated.
     */
    @Test
    void setState() {
        RefreshUserState newState = new RefreshUserState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    /**
     * Tests the firePropertyChange method.
     * Verifies that a property change event is fired with the correct property name and values.
     */
    @Test
    void firePropertyChange() {
        viewModel.firePropertyChange("state", null, state);
        verify(listener).propertyChange(any());
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that a property change listener is correctly added.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeSupport support = mock(PropertyChangeSupport.class);
        PropertyChangeListener testListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(testListener);

        viewModel.firePropertyChange("state", null, state);
        verify(testListener).propertyChange(any());
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that a property change listener is correctly removed.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChange("state", null, state);
        verify(listener, never()).propertyChange(any());
    }
}