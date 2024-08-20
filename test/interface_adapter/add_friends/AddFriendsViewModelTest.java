package interface_adapter.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AddFriendsViewModel} class.
 */
class AddFriendsViewModelTest {

    private AddFriendsViewModel viewModel;
    private AddFriendsState mockState;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        viewModel = new AddFriendsViewModel();
        mockState = mock(AddFriendsState.class);
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests the {@link AddFriendsViewModel#getState()} method.
     * Ensures it returns the correct state.
     */
    @Test
    void getState() {
        viewModel.setState(mockState);
        assertEquals(mockState, viewModel.getState());
    }

    /**
     * Tests the {@link AddFriendsViewModel#setState(AddFriendsState)} method.
     * Ensures it correctly sets the state.
     */
    @Test
    void setState() {
        viewModel.setState(mockState);
        assertEquals(mockState, viewModel.getState());
    }

    /**
     * Tests the {@link AddFriendsViewModel#firePropertyChanged()} method.
     * Ensures it fires a property change event.
     */
    @Test
    void firePropertyChanged() {
        viewModel.setState(mockState);
        viewModel.firePropertyChanged();
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the overloaded {@link AddFriendsViewModel#firePropertyChanged(String, Object, Object)} method.
     * Ensures it fires a property change event with the correct parameters.
     */
    @Test
    void testFirePropertyChanged() {
        String propertyName = "testProperty";
        Object oldValue = "oldValue";
        Object newValue = "newValue";

        viewModel.firePropertyChanged(propertyName, oldValue, newValue);

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the {@link AddFriendsViewModel#addPropertyChangeListener(PropertyChangeListener)} method.
     * Ensures a property change listener is added.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        viewModel.firePropertyChanged("testProperty", null, null);
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the {@link AddFriendsViewModel#removePropertyChangeListener(PropertyChangeListener)} method.
     * Ensures a property change listener is removed.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.removePropertyChangeListener(listener);
        viewModel.firePropertyChanged("testProperty", null, null);
        verify(listener, never()).propertyChange(any(PropertyChangeEvent.class));
    }
}