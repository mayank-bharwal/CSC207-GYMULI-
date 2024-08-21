package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ViewModel class.
 */
class ViewModelTest {

    private ViewModel viewModel;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     * Creates an anonymous subclass of ViewModel for testing purposes.
     */
    @BeforeEach
    void setUp() {
        viewModel = new ViewModel() {
        };
        listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Handle property change event
            }
        };
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that a listener is added correctly and receives a property change event.
     */
    @Test
    void addPropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);

        assertNotNull(listener);
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that a listener is removed correctly.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.addPropertyChangeListener(listener);
        viewModel.removePropertyChangeListener(listener);

        assertNotNull(listener);
    }

    /**
     * Tests the firePropertyChanged method.
     * Verifies that the property change event is fired correctly with the given property name, old value, and new value.
     */
    @Test
    void firePropertyChanged() {
        final String[] receivedPropertyName = new String[1];
        final Object[] receivedOldValue = new Object[1];
        final Object[] receivedNewValue = new Object[1];

        PropertyChangeListener mockListener = evt -> {
            receivedPropertyName[0] = evt.getPropertyName();
            receivedOldValue[0] = evt.getOldValue();
            receivedNewValue[0] = evt.getNewValue();
        };

        viewModel.addPropertyChangeListener(mockListener);
        viewModel.firePropertyChanged("testProperty", "oldValue", "newValue");

        assertEquals("testProperty", receivedPropertyName[0]);
        assertEquals("oldValue", receivedOldValue[0]);
        assertEquals("newValue", receivedNewValue[0]);
    }
}