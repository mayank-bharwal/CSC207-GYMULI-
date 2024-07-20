package use_case.add_friends;

import use_case.edit_friends.EditFriendsOutputData;

public interface AddFriendsOutputBoundary {

    void setPassView(AddFriendsOutputData outputData);

    void setFailView(String msg);


}
