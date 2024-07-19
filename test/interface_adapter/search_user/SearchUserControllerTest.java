package interface_adapter.search_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class SearchUserControllerTest {

    private SearchUserController searchUserController;
    private SearchUserInputBoundary mockSearchUserInputBoundary;

    @BeforeEach
    void setUp() {
        mockSearchUserInputBoundary = Mockito.mock(SearchUserInputBoundary.class);
        searchUserController = new SearchUserController(mockSearchUserInputBoundary);
    }

    @Test
    void testSearchUser() {
        String username = "testUser";
        ArgumentCaptor<SearchUserInputData> captor = ArgumentCaptor.forClass(SearchUserInputData.class);

        searchUserController.search_user(username);

        verify(mockSearchUserInputBoundary).execute(captor.capture());
        SearchUserInputData capturedInputData = captor.getValue();

        assertEquals(username, capturedInputData.getUsername());
    }
}