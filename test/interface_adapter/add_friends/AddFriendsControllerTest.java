package interface_adapter.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the AddFriendsController class.
 */
class AddFriendsControllerTest {
    private AddFriendsController addFriendsController;
    private AddFriendsInputBoundary mockAddFriendsInputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockAddFriendsInputBoundary = Mockito.mock(AddFriendsInputBoundary.class);
        addFriendsController = new AddFriendsController(mockAddFriendsInputBoundary);
    }

    /**
     * Tests the add method of AddFriendsController.
     * It verifies that the add method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void testAdd() {
        String currentUser = "Alice";
        String friend = "Bob";

        addFriendsController.add(currentUser, friend);

        ArgumentCaptor<AddFriendsInputData> captor = ArgumentCaptor.forClass(AddFriendsInputData.class);
        verify(mockAddFriendsInputBoundary).execute(captor.capture());
        AddFriendsInputData capturedData = captor.getValue();

        assertEquals(currentUser, capturedData.getCurrentUser());
        assertEquals(friend, capturedData.getFriend());
    }
}
