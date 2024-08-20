package interface_adapter.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link SearchUserViewModel} class.
 */
class SearchUserViewModelTest {

    private SearchUserViewModel viewModel;
    private SearchUserState state;
    private PropertyChangeListener listener;

    /**
     * Sets up the test environment before each test method.
     * Initializes the view model, state, and mocks dependencies.
     */
    @BeforeEach
    void setUp() {
        viewModel = new SearchUserViewModel();
        state = new SearchUserState();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests the {@link SearchUserViewModel#getState()} method.
     * Ensures it returns the correct state.
     */
    @Test
    void getState() {
        assertEquals(viewModel.state, viewModel.getState());
    }

    /**
     * Tests the {@link SearchUserViewModel#setState(SearchUserState)} method.
     * Ensures it correctly sets the state and fires a property change event.
     */
    @Test
    void setState() {
        SearchUserState newState = new SearchUserState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());

        verify(listener).propertyChange(any());
    }

    /**
     * Tests the {@link SearchUserViewModel#addPropertyChangeListener(PropertyChangeListener)} method.
     * Ensures that a property change listener can be added and that it receives property change events.
     */
    @Test
    void addPropertyChangeListener() {
        reset(listener);

        SearchUserState newState = new SearchUserState();
        viewModel.setState(newState);

        verify(listener, times(1)).propertyChange(any());
    }

    /**
     * Tests the {@link SearchUserViewModel#removePropertyChangeListener(PropertyChangeListener)} method.
     * Ensures that a property change listener can be removed and that it no longer receives property change events.
     */
    @Test
    void removePropertyChangeListener() {
        viewModel.removePropertyChangeListener(listener);
        SearchUserState newState = new SearchUserState();
        viewModel.setState(newState);

        verify(listener, never()).propertyChange(any());
    }

    /**
     * Tests the {@link SearchUserViewModel#firePropertyChanged(String, Object, Object)} method.
     * Ensures it correctly fires a property change event.
     */
    @Test
    void firePropertyChanged() {
        String propertyName = "state";
        SearchUserState oldState = new SearchUserState();
        SearchUserState newState = new SearchUserState();

        viewModel.firePropertyChanged(propertyName, oldState, newState);

        verify(listener).propertyChange(any());
    }
}