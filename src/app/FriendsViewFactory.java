package app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;
import views.EditFriendsView;

/**
 * Factory class for creating instances of {@link EditFriendsView}.
 */
public class FriendsViewFactory{
    /**
     * Creates an instance of {@link EditFriendsView}.
     *
     * @param viewModelManager     Manages the ViewModels used in the application.
     * @param addFriendsViewModel  ViewModel responsible for adding friends.
     * @param removeFriendsViewModel ViewModel responsible for removing friends.
     * @param removeFriendsController Controller for handling friend removal logic.
     * @param addFriendsController  Controller for handling friend addition logic.
     * @param refreshUserController Controller for refreshing user data.
     * @param userDAO              Data Access Object for user-related data operations.
     * @return A new instance of {@link EditFriendsView}.
     */
    public static EditFriendsView create(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel,
                                         RemoveFriendsViewModel removeFriendsViewModel, RemoveFriendsController removeFriendsController,
                                         AddFriendsController addFriendsController, RefreshUserController refreshUserController, UserDataAccessObject userDAO){
        return new EditFriendsView(viewModelManager, addFriendsViewModel, removeFriendsViewModel, removeFriendsController, addFriendsController, refreshUserController, userDAO);

    }
}
