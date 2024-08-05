package use_case.remove_friends;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for RemoveFriendsInteractor.
 */
class RemoveFriendsInteractorTest {

    private RemoveFriendsInteractor interactor;
    private RemoveFriendsOutputBoundary outputBoundary;
    private RemoveFriendsUserDataAccessInterface removeFriendsDAO;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        outputBoundary = mock(RemoveFriendsOutputBoundary.class);
        removeFriendsDAO = mock(RemoveFriendsUserDataAccessInterface.class);
        interactor = new RemoveFriendsInteractor(removeFriendsDAO, outputBoundary);
    }

    /**
     * Tests the removeFriend method when the friend removal is successful.
     */
    @Test
    void removeFriend_success() {
        RemoveFriendsInputData inputData = new RemoveFriendsInputData("User1", "User2");

        when(removeFriendsDAO.isFriend("User1", "User2")).thenReturn(true);

        interactor.removeFriend(inputData);

        verify(removeFriendsDAO).remove("User1", "User2");

        ArgumentCaptor<RemoveFriendsOutputData> captor = ArgumentCaptor.forClass(RemoveFriendsOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());

        RemoveFriendsOutputData outputData = captor.getValue();
        assertEquals("User1", outputData.getCurrentUser());
        assertEquals("User2", outputData.getFriendRemoved());
        assertFalse(outputData.isFailView());
    }

    /**
     * Tests the removeFriend method when the users are not friends.
     */
    @Test
    void removeFriend_failure_usersNotFriends() {
        RemoveFriendsInputData inputData = new RemoveFriendsInputData("User1", "User2");

        when(removeFriendsDAO.isFriend("User1", "User2")).thenReturn(false);

        interactor.removeFriend(inputData);

        verify(outputBoundary).setFailView("You have no such friend");
        verify(removeFriendsDAO, never()).remove(anyString(), anyString());
    }
}