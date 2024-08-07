package interface_adapter.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.search_user.SearchUserOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SearchUserPresenter class.
 */
class SearchUserPresenterTest {

    private SearchUserPresenter searchUserPresenter;
    private SearchUserViewModel searchUserViewModel;

    /**
     * Sets up the test environment before each test method.
     * Initializes the mocks and the SearchUserPresenter instance.
     */
    @BeforeEach
    void setUp() {
        searchUserViewModel = mock(SearchUserViewModel.class);
        searchUserPresenter = new SearchUserPresenter(searchUserViewModel);
    }

    /**
     * Tests the showSuccessScreen method.
     * Verifies that the state is correctly updated when a user is found.
     */
    @Test
    void showSuccessScreen() {
        String username = "testUser";
        SearchUserOutputData outputData = new SearchUserOutputData(username);

        searchUserPresenter.showSuccessScreen(outputData);

        ArgumentCaptor<SearchUserState> captor = ArgumentCaptor.forClass(SearchUserState.class);
        verify(searchUserViewModel).setState(captor.capture());
        SearchUserState capturedState = captor.getValue();

        assertEquals(username, capturedState.getUsername());
    }

    /**
     * Tests the showFailureScreen method.
     * Verifies that the state is correctly updated when a user is not found.
     */
    @Test
    void showFailureScreen() {
        String error = "User not found";

        searchUserPresenter.showFailureScreen(error);

        ArgumentCaptor<SearchUserState> captor = ArgumentCaptor.forClass(SearchUserState.class);
        verify(searchUserViewModel).setState(captor.capture());
        SearchUserState capturedState = captor.getValue();

        assertEquals(error, capturedState.getUsernameError());
    }
}