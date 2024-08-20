package views;

import entity.User;
import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link ProfileView} class.
 * This class tests the functionality of the ProfileView, ensuring it properly updates its display
 * based on changes to the underlying data and view model.
 */
class ProfileViewTest {

    private ProfileView profileView;
    private ViewModelManager viewModelManager;
    private User currentUser;
    private User viewedUser;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        currentUser = mock(User.class);
        viewedUser = mock(User.class);

        when(currentUser.getUsername()).thenReturn("currentUser");
        when(currentUser.getBio()).thenReturn("Bio of currentUser");
        when(currentUser.getProgramOfStudy()).thenReturn("Computer Science");
        when(currentUser.getAge()).thenReturn(21);
        when(currentUser.getFriends()).thenReturn(mock(java.util.List.class));

        when(viewedUser.getUsername()).thenReturn("viewedUser");
        when(viewedUser.getBio()).thenReturn("Bio of viewedUser");
        when(viewedUser.getProgramOfStudy()).thenReturn("Mathematics");
        when(viewedUser.getAge()).thenReturn(22);
        when(viewedUser.getFriends()).thenReturn(mock(java.util.List.class));

        profileView = new ProfileView(viewModelManager);

        when(viewModelManager.getCurrentUser()).thenReturn(currentUser);
        when(viewModelManager.getViewedUser()).thenReturn(viewedUser);
    }

    /**
     * Tests the method.
     * This test ensures that the user information is updated when the "activeView" property changes.
     */
    @Test
    void propertyChange_activeView() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "activeView", null, ProfileView.viewName);
        profileView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
        verify(viewModelManager, atLeastOnce()).getViewedUser();
    }

    /**
     * Tests the method.
     * This test ensures that the user information is updated when the "viewedUser" property changes.
     */
    @Test
    void propertyChange_viewedUser() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "viewedUser", null, viewedUser);
        profileView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getViewedUser();
    }
}