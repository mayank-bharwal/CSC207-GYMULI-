package app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.EditFriendsView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Unit test for the FriendsViewFactory class.
 * This test ensures that the FriendsViewFactory#create(ViewModelManager, AddFriendsViewModel, RemoveFriendsViewModel,
 * RemoveFriendsController, AddFriendsController, RefreshUserController, UserDataAccessObject)
 * method correctly creates a EditFriendsView object when provided with valid dependencies.
 */
class FriendsViewFactoryTest {

    private ViewModelManager viewModelManager;
    private AddFriendsViewModel addFriendsViewModel;
    private RemoveFriendsViewModel removeFriendsViewModel;
    private RemoveFriendsController removeFriendsController;
    private AddFriendsController addFriendsController;
    private RefreshUserController refreshUserController;
    private UserDataAccessObject userDAO;

    /**
     * Sets up the test environment by mocking the necessary dependencies.
     * Mocks include ViewModelManager, AddFriendsViewModel, RemoveFriendsViewModel,
     * RemoveFriendsController, AddFriendsController, RefreshUserController, and UserDataAccessObject.
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
    }

    /**
     * Tests that the FriendsViewFactory#create(ViewModelManager, AddFriendsViewModel, RemoveFriendsViewModel, RemoveFriendsController, AddFriendsController, RefreshUserController, UserDataAccessObject)
     * method successfully creates a non-null EditFriendsView instance when valid dependencies are provided.
     */
    @Test
    void create() {
        EditFriendsView editFriendsView = FriendsViewFactory.create(viewModelManager, addFriendsViewModel, removeFriendsViewModel,
                removeFriendsController, addFriendsController, refreshUserController, userDAO);

        assertNotNull(editFriendsView, "EditFriendsView should not be null");
    }
}