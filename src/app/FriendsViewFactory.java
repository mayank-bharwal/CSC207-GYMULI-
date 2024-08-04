package app;

import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.remove_friends.RemoveFriendsController;
import views.EditFriendsView;

public class FriendsViewFactory{
    public static EditFriendsView create(ViewModelManager viewModelManager, AddFriendsViewModel addFriendsViewModel,
                                         RemoveFriendsController removeFriendsController, AddFriendsController addFriendsController){
        return new EditFriendsView(viewModelManager, addFriendsViewModel, removeFriendsController, addFriendsController);

    }
}
