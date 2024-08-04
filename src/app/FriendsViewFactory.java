package app;

import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.remove_friends.RemoveFriendsController;
import views.EditFriendsView;

public class FriendsViewFactory{
    public static EditFriendsView create(ViewModelManager viewModelManager, RemoveFriendsController removeFriendsController
    , AddFriendsController addFriendsController){
        return new EditFriendsView(viewModelManager, removeFriendsController, addFriendsController);

    }
}
