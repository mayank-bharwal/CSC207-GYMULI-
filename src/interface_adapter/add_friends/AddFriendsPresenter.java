package interface_adapter.add_friends;


import interface_adapter.ViewModelManager;
import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.add_friends.AddFriendsOutputData;

public class AddFriendsPresenter implements AddFriendsOutputBoundary {

    private final AddFriendsViewModel addFriendsViewModel;
    private final ViewModelManager viewModelManager;

    public AddFriendsPresenter(AddFriendsViewModel addFriendsViewModel, ViewModelManager viewModelManager){
        this.addFriendsViewModel = addFriendsViewModel;
        this.viewModelManager = viewModelManager;
    }

    @Override
    public void setPassView(AddFriendsOutputData outputData) {

    }

    @Override
    public void setFailView(String msg) {

    }
}
