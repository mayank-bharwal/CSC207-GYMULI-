package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInputData;

public class AddFriendsController {
    /**
     * Controller for handling user adding friends
     */
    private final AddFriendsInputBoundary addFriendsInputBoundary;

    /**
     * Constructor for AddFriends
     *
     * @param addFriendsInputBoundary
     */
    public AddFriendsController(AddFriendsInputBoundary addFriendsInputBoundary) {
        this.addFriendsInputBoundary = addFriendsInputBoundary;
    }

    /**
     * executes the add friends process
     *
     * @param currentUser
     * @param friend
     */
    public void add(String currentUser, String friend) {
        AddFriendsInputData addFriendsInputData = new AddFriendsInputData(currentUser, friend);
        addFriendsInputBoundary.execute(addFriendsInputData);
    }

}
