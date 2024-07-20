package use_case.add_friends;

import use_case.edit_friends.EditFriendsOutputData;

public interface AddFriendsOutputBoundary {

    void setPassView(EditFriendsOutputData outputData);

    void sstFailView(String msg);


}
