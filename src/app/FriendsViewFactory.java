package app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsViewModel;
import views.EditFriendsView;

public class FriendsViewFactory{
    public static EditFriendsView create(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel,
                                         RemoveFriendsViewModel removeFriendsViewModel, RemoveFriendsController removeFriendsController,
                                         AddFriendsController addFriendsController, RefreshUserController refreshUserController, UserDataAccessObject userDAO){
        return new EditFriendsView(viewModelManager, addFriendsViewModel, removeFriendsViewModel, removeFriendsController, addFriendsController, refreshUserController, userDAO);

    }
}
