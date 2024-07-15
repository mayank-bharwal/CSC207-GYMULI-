package interface_adapter.edit_friends;

import use_case.edit_friends.EditFriendsInputBoundary;
import use_case.edit_friends.EditFriendsInputData;

public class EditFriendsController {
    final EditFriendsInputBoundary editFriendsCaseInteractor;

    public EditFriendsController(EditFriendsInputBoundary editFriendsCaseInteractor) {
        this.editFriendsCaseInteractor = editFriendsCaseInteractor;
    }

    public void execute(String user1, String user2, String user3){
       // EditFriendsInputData editFriendsInputData = new EditFriendsInputData(user1, user2, user3);

        // editFriendsCaseInteractor.execute(editFriendsInputData);
    }
}
