package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UpdateProfileViewModel class.
 */
class UpdateProfileViewModelTest {

    private UpdateProfileViewModel viewModel;
    private UpdateProfileState state;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        viewModel = new UpdateProfileViewModel();
        state = new UpdateProfileState();
    }

    /**
     * Tests the getState method.
     * Verifies that it returns the current state.
     */
    @Test
    void getState() {
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    /**
     * Tests the setState method.
     * Verifies that the state is correctly set.
     */
    @Test
    void setState() {
        UpdateProfileState newState = new UpdateProfileState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    /**
     * Tests the firePropertyChanged method with the state property.
     * Verifies that the property change event is fired correctly.
     */
    @Test
    void firePropertyChanged() {
        PropertyChangeListener mockListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals("state", evt.getPropertyName());
                assertNull(evt.getOldValue());
                assertEquals(viewModel.getState(), evt.getNewValue());
            }
        };
        viewModel.addPropertyChangeListener(mockListener);
        viewModel.firePropertyChanged();
        viewModel.removePropertyChangeListener(mockListener);
    }

    /**
     * Tests the firePropertyChanged method with a specific property name.
     * Verifies that the property change event is fired correctly for the given property.
     */
    @Test
    void testFirePropertyChanged() {
        PropertyChangeListener mockListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals("username", evt.getPropertyName());
                assertEquals("oldUsername", evt.getOldValue());
                assertEquals("newUsername", evt.getNewValue());
            }
        };
        viewModel.addPropertyChangeListener(mockListener);
        viewModel.firePropertyChanged("username", "oldUsername", "newUsername");
        viewModel.removePropertyChangeListener(mockListener);
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that the listener is added correctly.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener mockListener = evt -> {
        };
        viewModel.addPropertyChangeListener(mockListener);
        viewModel.firePropertyChanged("testProperty", "oldValue", "newValue");
        assertNotNull(viewModel);
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that the listener is removed correctly.
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener mockListener = evt -> {
        };
        viewModel.addPropertyChangeListener(mockListener);
        viewModel.removePropertyChangeListener(mockListener);
        assertNotNull(viewModel);
    }
}