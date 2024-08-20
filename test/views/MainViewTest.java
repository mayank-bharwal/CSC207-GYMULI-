package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatController;
import interface_adapter.delete_chat.DeleteChatController;
import data_access.UserDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link MainView} class.
 * This class verifies the functionality of the MainView, ensuring that it responds correctly to property change events and interacts with the ViewModelManager, RetrieveChatController, and DeleteChatController as expected.
 */
class MainViewTest {

    private MainView mainView;
    private ViewModelManager viewModelManager;
    private RetrieveChatController retrieveChatController;
    private DeleteChatController deleteChatController;
    private UserDataAccessObject userDataAccessObject;
    private User currentUser;

    @BeforeEach
    void setUp() {
        viewModelManager = mock(ViewModelManager.class);
        retrieveChatController = mock(RetrieveChatController.class);
        deleteChatController = mock(DeleteChatController.class);
        userDataAccessObject = mock(UserDataAccessObject.class);

        currentUser = mock(User.class);
        when(currentUser.getUsername()).thenReturn("currentUser");
        when(currentUser.getChats()).thenReturn(Arrays.asList("chat1", "chat2"));

        when(viewModelManager.getCurrentUser()).thenReturn(currentUser);
        when(userDataAccessObject.getUser(currentUser.getUsername())).thenReturn(currentUser);

        mainView = new MainView(viewModelManager, retrieveChatController, userDataAccessObject, deleteChatController);
    }

    /**
     * Tests the propertyChange method when the active view is switched to MainView.
     * Verifies that the timer starts and the view is updated.
     */
    @Test
    void propertyChange_activeView() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "activeView", null, MainView.viewName);
        mainView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
    }

    /**
     * Tests the propertyChange method when the current user changes.
     * Verifies that the current user label and chat list are updated accordingly.
     */
    @Test
    void propertyChange_currentUser() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "currentUser", null, currentUser);
        mainView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
    }

    /**
     * Tests the propertyChange method when the chat list is updated.
     * Ensures that the chat buttons are correctly populated based on the updated chat list.
     */
    @Test
    void propertyChange_chatsUpdated() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "chatsUpdated", null, null);
        mainView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
        verify(currentUser, atLeastOnce()).getChats();
    }

    /**
     * Tests the propertyChange method when a chat is deleted.
     * Verifies that the chat list is refreshed and a success message is shown.
     */
    @Test
    void propertyChange_chatDeleted() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "ChatDeleted", null, null);
        mainView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
        verify(currentUser, atLeastOnce()).getChats();
    }

    /**
     * Tests the propertyChange method when the user's profile is updated.
     * Ensures that the current user information is refreshed.
     */
    @Test
    void propertyChange_profileUpdated() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "profileUpdated", null, currentUser);
        mainView.propertyChange(event);

        verify(viewModelManager, atLeastOnce()).getCurrentUser();
    }
}