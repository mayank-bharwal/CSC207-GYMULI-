package interface_adapter.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the functionality of the SearchUserController class.
 */
public class SearchUserControllerTest {

    private SearchUserController searchUserController;
    private SearchUserInputBoundary mockSearchUserInputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockSearchUserInputBoundary = Mockito.mock(SearchUserInputBoundary.class);
        searchUserController = new SearchUserController(mockSearchUserInputBoundary);
    }

    /**
     * Tests the search_user method of SearchUserController.
     * It verifies that the method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void testSearchUser() {
        String username = "Jasmine";
        ArgumentCaptor<SearchUserInputData> captor = ArgumentCaptor.forClass(SearchUserInputData.class);

        searchUserController.search_user(username);

        verify(mockSearchUserInputBoundary).execute(captor.capture());
        SearchUserInputData capturedInputData = captor.getValue();

        assertEquals(username, capturedInputData.getUsername());
    }
}