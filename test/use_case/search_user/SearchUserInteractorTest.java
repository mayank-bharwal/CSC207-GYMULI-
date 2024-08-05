package use_case.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SearchUserInteractor class.
 */
class SearchUserInteractorTest {

    private SearchUserInteractor interactor;
    private SearchUserOutputBoundary outputBoundary;
    private SearchUserDataAccessInterface searchUserDAO;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = mock(SearchUserOutputBoundary.class);
        searchUserDAO = mock(SearchUserDataAccessInterface.class);
        interactor = new SearchUserInteractor(outputBoundary, searchUserDAO);
    }

    /**
     * Tests the execute method with a user that exists.
     * Verifies that the success screen is shown.
     */
    @Test
    void execute_userExists() {
        SearchUserInputData inputData = new SearchUserInputData("existingUser");

        when(searchUserDAO.userExists("existingUser")).thenReturn(true);

        interactor.execute(inputData);

        ArgumentCaptor<SearchUserOutputData> captor = ArgumentCaptor.forClass(SearchUserOutputData.class);
        verify(outputBoundary).showSuccessScreen(captor.capture());
        assertEquals("existingUser", captor.getValue().getUsername());
    }
}