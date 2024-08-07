package interface_adapter.add_friends;


import interface_adapter.ViewModelManager;
import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.add_friends.AddFriendsOutputData;
/**
 * The AddFriends class handles the presentation logic for adding friends.
 * It updates the view model based on the result of the adding friends process.
 */
public class AddFriendsPresenter implements AddFriendsOutputBoundary {
    private final AddFriendsViewModel addFriendsViewModel;
    private final ViewModelManager viewModelManager;

    /**
     * Constructs a new AddFriendsPresenter with the specified view model manager and add friends view model.
     * @param addFriendsViewModel the addFriendsViewModel
     * @param viewModelManager the view model manager
     */
    public AddFriendsPresenter(AddFriendsViewModel addFriendsViewModel, ViewModelManager viewModelManager){
        this.addFriendsViewModel = addFriendsViewModel;
        this.viewModelManager = viewModelManager;
    }
    /**
     * Sets the pass view for successful adding friends.
     * @param outputData output data containing add friends information
     */

    @Override
    public void setPassView(AddFriendsOutputData outputData) {

    }
    /**
     * Sets the fail view for unsuccessful adding friends.
     * @param msg the error message
     */

    @Override
    public void setFailView(String msg) {

    }
}
