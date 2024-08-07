package interface_adapter.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_friends.AddFriendsOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AddFriendsPresenter class.
 */
class AddFriendsPresenterTest {

    private AddFriendsPresenter addFriendsPresenter;
    private AddFriendsViewModel addFriendsViewModel;

    /**
     * Sets up the test environment before each test method.
     * Initializes the mocks and the AddFriendsPresenter instance.
     */
    @BeforeEach
    void setUp() {
        addFriendsViewModel = mock(AddFriendsViewModel.class);
        addFriendsPresenter = new AddFriendsPresenter(addFriendsViewModel);
    }

    /**
     * Tests the setPassView method.
     * Verifies that the state and property changes are correctly handled when a friend is successfully added.
     */
    @Test
    void setPassView() {
        String currentUser = "currentUser";
        String friend = "friend";
        boolean useCaseFailed = false;
        AddFriendsOutputData outputData = new AddFriendsOutputData(currentUser, friend, useCaseFailed);

        AddFriendsState initialState = new AddFriendsState();
        when(addFriendsViewModel.getState()).thenReturn(initialState);

        addFriendsPresenter.setPassView(outputData);

        ArgumentCaptor<AddFriendsState> captor = ArgumentCaptor.forClass(AddFriendsState.class);
        verify(addFriendsViewModel, times(1)).setState(captor.capture());
        AddFriendsState capturedState = captor.getValue();

        assertEquals(currentUser, capturedState.getCurrentUser());
        assertEquals(friend, capturedState.getFriend());
        assertEquals(null, capturedState.getError());

        verify(addFriendsViewModel).firePropertyChanged(eq("friendsList"), isNull(), eq("Friend successfully added!"));
    }

    /**
     * Tests the setFailView method.
     * Verifies that the state and property changes are correctly handled when adding a friend fails.
     */
    @Test
    void setFailView() {
        String error = "An error occurred";
        AddFriendsState initialState = new AddFriendsState();
        when(addFriendsViewModel.getState()).thenReturn(initialState);

        addFriendsPresenter.setFailView(error);

        ArgumentCaptor<AddFriendsState> captor = ArgumentCaptor.forClass(AddFriendsState.class);
        verify(addFriendsViewModel, times(1)).setState(captor.capture());
        AddFriendsState capturedState = captor.getValue();

        assertEquals(error, capturedState.getError());

        verify(addFriendsViewModel).firePropertyChanged(eq("generalError"), isNull(), eq(error));
    }
}