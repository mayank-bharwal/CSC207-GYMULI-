package interface_adapter;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ViewModelManager class.
 */
class ViewModelManagerTest {

    private ViewModelManager viewModelManager;
    private PropertyChangeListener listener;
    private User mockUser;

    /**
     * Sets up the test environment before each test method.
     * Initializes the ViewModelManager and mocks a User and PropertyChangeListener.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = new ViewModelManager();
        listener = mock(PropertyChangeListener.class);
        mockUser = mock(User.class);
        viewModelManager.addPropertyChangeListener(listener);
    }

    /**
     * Tests the setActiveView method.
     * Verifies that the active view is set correctly and triggers a property change event.
     */
    @Test
    void setActiveView() {
        String newView = "newView";
        viewModelManager.setActiveView(newView);

        assertEquals(newView, viewModelManager.getActiveView());
        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the getActiveView method.
     * Verifies that the active view is retrieved correctly.
     */
    @Test
    void getActiveView() {
        String view = "testView";
        viewModelManager.setActiveView(view);

        assertEquals(view, viewModelManager.getActiveView());
    }

    /**
     * Tests the setCurrentUser method.
     * Verifies that the current user is set correctly and triggers a property change event.
     */
    @Test
    void setCurrentUser() {
        viewModelManager.setCurrentUser(mockUser);

        assertEquals(mockUser, viewModelManager.getCurrentUser());
        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the getCurrentUser method.
     * Verifies that the current user is retrieved correctly.
     */
    @Test
    void getCurrentUser() {
        viewModelManager.setCurrentUser(mockUser);

        assertEquals(mockUser, viewModelManager.getCurrentUser());
    }

    /**
     * Tests the setViewedUser method.
     * Verifies that the viewed user is set correctly and triggers a property change event.
     */
    @Test
    void setViewedUser() {
        viewModelManager.setViewedUser(mockUser);

        assertEquals(mockUser, viewModelManager.getViewedUser());
        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the getViewedUser method.
     * Verifies that the viewed user is retrieved correctly.
     */
    @Test
    void getViewedUser() {
        viewModelManager.setViewedUser(mockUser);

        assertEquals(mockUser, viewModelManager.getViewedUser());
    }

    /**
     * Tests the clearViewedUser method.
     * Verifies that the viewed user is cleared correctly and triggers a property change event.
     */
    @Test
    void clearViewedUser() {
        viewModelManager.setViewedUser(mockUser);
        viewModelManager.clearViewedUser();

        assertNull(viewModelManager.getViewedUser());
        verify(listener, times(2)).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the addPropertyChangeListener method.
     * Verifies that a property change listener is added successfully.
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModelManager.addPropertyChangeListener(newListener);

        viewModelManager.setActiveView("testView");

        verify(newListener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the removePropertyChangeListener method.
     * Verifies that a property change listener is removed successfully.
     */
    @Test
    void removePropertyChangeListener() {
        viewModelManager.removePropertyChangeListener(listener);

        viewModelManager.setActiveView("testView");

        verify(listener, never()).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the firePropertyChanged method with a property name, old value, and new value.
     * Verifies that the property change event is fired correctly.
     */
    @Test
    void firePropertyChanged() {
        String oldValue = "oldValue";
        String newValue = "newValue";

        viewModelManager.firePropertyChanged("testProperty", oldValue, newValue);

        verify(listener).propertyChange(any(PropertyChangeEvent.class));
    }

    /**
     * Tests the firePropertyChanged method with no arguments.
     * Currently, this method does nothing.
     */
    @Test
    void testFirePropertyChanged() {
        viewModelManager.firePropertyChanged();
    }
}