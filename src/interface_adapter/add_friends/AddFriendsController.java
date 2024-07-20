package interface_adapter.add_friends;

import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInputData;

public class AddFriendsController {

    private final AddFriendsInputBoundary addFriendsInputBoundary;

    public AddFriendsController(AddFriendsInputBoundary addFriendsInputBoundary){
        this.addFriendsInputBoundary = addFriendsInputBoundary;
    }

    public void add(String currentUser, String friend){
        AddFriendsInputData addFriendsInputData = new AddFriendsInputData(currentUser, friend);
        addFriendsInputBoundary.execute(addFriendsInputData);
    }

}
