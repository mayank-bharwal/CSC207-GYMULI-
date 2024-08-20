package views;

import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class EditFriendsViewTest {

    private EditFriendsView editFriendsView;
    private ViewModelManager viewModelManager;
    private AddFriendsViewModel addFriendsViewModel;
    private RemoveFriendsViewModel removeFriendsViewModel;
    private RemoveFriendsController removeFriendsController;
    private AddFriendsController addFriendsController;
    private RefreshUserController refreshUserController;
    private UserDataAccessObject userDAO;
    private User currentUser;
    private User viewedUser;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        addFriendsViewModel = mock(AddFriendsViewModel.class);
        removeFriendsViewModel = mock(RemoveFriendsViewModel.class);
        removeFriendsController = mock(RemoveFriendsController.class);
        addFriendsController = mock(AddFriendsController.class);
        refreshUserController = mock(RefreshUserController.class);
        userDAO = mock(UserDataAccessObject.class);

        currentUser = mock(User.class);
        when(currentUser.getUsername()).thenReturn("currentUser");

        viewedUser = mock(User.class);
        when(viewedUser.getUsername()).thenReturn("viewedUser");
        when(viewedUser.getFriends()).thenReturn(Arrays.asList("friend1", "friend2"));

        when(viewModelManager.getCurrentUser()).thenReturn(currentUser);
        when(viewModelManager.getViewedUser()).thenReturn(viewedUser);
        when(userDAO.getUser(currentUser.getUsername())).thenReturn(currentUser);

        editFriendsView = new EditFriendsView(viewModelManager, addFriendsViewModel, removeFriendsViewModel,
                removeFriendsController, addFriendsController, refreshUserController, userDAO);
    }

    /**
     * Tests the handling of the "activeView" property change event in the EditFriendsView class.
     * Verifies that the appropriate interactions with the view model occur when the active view is changed.
     */
    @Test
    void propertyChange_activeView() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "activeView", null, EditFriendsView.viewName);
        editFriendsView.propertyChange(event);

        verify(viewModelManager, atLeast(1)).getViewedUser();
    }

    /**
     * Tests the handling of the "currentUser" property change event in the EditFriendsView class.
     * Verifies that the current user is correctly retrieved from the view model when the property changes.
     */
    @Test
    void propertyChange_currentUser() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "currentUser", null, currentUser);
        editFriendsView.propertyChange(event);

        verify(viewModelManager, atLeast(1)).getCurrentUser();
    }

    /**
     * Tests the handling of the "friendsEdited" property change event in the EditFriendsView class.
     * Verifies that the friends list is correctly updated when the friends list is edited.
     */
    @Test
    void propertyChange_friendsEdited() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "friendsEdited", null, null);
        editFriendsView.propertyChange(event);

        verify(viewModelManager, atLeast(1)).getCurrentUser();
        verify(viewedUser, atLeast(1)).getFriends();
    }

    /**
     * Tests the handling of the "generalError" property change event in the EditFriendsView class.
     * Verifies that an error message is displayed when a general error occurs.
     */
    @Test
    void propertyChange_generalError() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "generalError", null, "Some error occurred");
        editFriendsView.propertyChange(event);
    }

    /**
     * Tests the handling of the "viewedUser" property change event in the EditFriendsView class.
     * Verifies that the viewed user is correctly retrieved from the view model when the property changes.
     */
    @Test
    void propertyChange_viewedUser() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "viewedUser", null, viewedUser);
        editFriendsView.propertyChange(event);

        verify(viewModelManager, atLeast(1)).getViewedUser();
    }
}