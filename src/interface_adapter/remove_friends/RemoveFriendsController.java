package interface_adapter.remove_friends;

import entity.User;
import interface_adapter.ViewModelManager;
import use_case.remove_friends.RemoveFriendsInputBoundary;
import use_case.remove_friends.RemoveFriendsInputData;

public class RemoveFriendsController {
    private final RemoveFriendsInputBoundary removeFriendsInputBoundary;
    private final ViewModelManager viewModelManager;

    public RemoveFriendsController(RemoveFriendsInputBoundary removeFriendsInputBoundary, ViewModelManager viewModelManager) {
        this.removeFriendsInputBoundary = removeFriendsInputBoundary;
        this.viewModelManager = viewModelManager;
    }

    public void removeFriends(String currUser, String friendUser) {
        RemoveFriendsInputData inputData = new RemoveFriendsInputData(currUser, friendUser);
        removeFriendsInputBoundary.removeFriend(inputData);

        User currentUser =  viewModelManager.getCurrentUser();
        viewModelManager.setCurrentUser(currentUser);

        viewModelManager.firePropertyChanged("friendRemoved", null, null);
    }
}

