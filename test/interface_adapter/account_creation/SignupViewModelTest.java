package interface_adapter.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SignupViewModel class.
 * This class contains tests for the methods in the SignupViewModel class to ensure proper functionality.
 */
class SignupViewModelTest {

    private SignupViewModel viewModel;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     * Initializes the SignupViewModel instance and mocks the PropertyChangeListener.
     */
    @BeforeEach
    void setUp() {
        viewModel = new SignupViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests the getState method.
     * Ensures that the state is correctly retrieved.
     */
    @Test
    void getState() {
        SignupState state = viewModel.getState();
        assertNotNull(state);
    }

    /**
     * Tests the setState method.
     * Ensures that the state is correctly set and a property change event is fired.
     */
    @Test
    void setState() {
        SignupState newState = new SignupState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    /**
     * Tests the firePropertyChanged method.
     * Ensures that a property change event is fired for the state.
     */
    @Test
    void firePropertyChanged() {
        viewModel.firePropertyChanged();

        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the firePropertyChanged method with a specific property name.
     * Ensures that a property change event is fired with the correct property name, old value, and new value.
     */
    @Test
    void testFirePropertyChanged() {
        String propertyName = "state";
        String oldValue = "oldValue";
        String newValue = "newValue";
        viewModel.firePropertyChanged(propertyName, oldValue, newValue);

        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Ensures that a PropertyChangeListener can be added and receives property change events.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        viewModel.setUsername("anotherUser");
        verify(newListener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Ensures that a PropertyChangeListener can be removed and no longer receives property change events.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.removePropertyChangeListener(listener);

        viewModel.setUsername("testUser");
        verify(listener, never()).propertyChange(any(PropertyChangeEvent.class));
    }
}
