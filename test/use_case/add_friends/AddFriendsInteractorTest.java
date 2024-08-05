package use_case.add_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for AddFriendsInteractor.
 */
class AddFriendsInteractorTest {

    private AddFriendsOutputBoundary outputBoundary;
    private AddFriendsUserDataAccessObject userDataAccessObject;
    private AddFriendsInteractor interactor;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = mock(AddFriendsOutputBoundary.class);
        userDataAccessObject = mock(AddFriendsUserDataAccessObject.class);
        interactor = new AddFriendsInteractor(outputBoundary, userDataAccessObject);
    }

    /**
     * Tests the execute method when the friend user does not exist.
     * Ensures that the appropriate failure view is set.
     */
    @Test
    void execute_withNonExistentUser_setsFailView() {
        AddFriendsInputData inputData = new AddFriendsInputData("currentUser", "nonExistentUser");

        when(userDataAccessObject.userExists("nonExistentUser")).thenReturn(false);

        interactor.execute(inputData);

        verify(outputBoundary).setFailView("User does not exist");
        verify(userDataAccessObject, never()).addFriend(anyString(), anyString());
    }

    /**
     * Tests the execute method when the friend user is already a friend.
     * Ensures that the appropriate failure view is set.
     */
    @Test
    void execute_withExistingFriend_setsFailView() {
        AddFriendsInputData inputData = new AddFriendsInputData("currentUser", "existingFriend");

        when(userDataAccessObject.userExists("existingFriend")).thenReturn(true);
        when(userDataAccessObject.isFriend("currentUser", "existingFriend")).thenReturn(true);

        interactor.execute(inputData);

        verify(outputBoundary).setFailView("Friend Already Added.");
        verify(userDataAccessObject, never()).addFriend(anyString(), anyString());
    }

    /**
     * Tests the execute method with valid input data.
     * Ensures that the friend is added and the pass view is set.
     */
    @Test
    void execute_withValidInput_addsFriendAndSetsPassView() {
        AddFriendsInputData inputData = new AddFriendsInputData("currentUser", "newFriend");

        when(userDataAccessObject.userExists("newFriend")).thenReturn(true);
        when(userDataAccessObject.isFriend("currentUser", "newFriend")).thenReturn(false);

        interactor.execute(inputData);

        verify(userDataAccessObject).addFriend("currentUser", "newFriend");

        ArgumentCaptor<AddFriendsOutputData> captor = ArgumentCaptor.forClass(AddFriendsOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());

        AddFriendsOutputData outputData = captor.getValue();
        assertEquals("currentUser", outputData.getCurrentUser());
        assertEquals("newFriend", outputData.getFriend());
    }
}