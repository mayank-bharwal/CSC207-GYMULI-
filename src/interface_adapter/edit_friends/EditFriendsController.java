package interface_adapter.edit_friends;

import use_case.edit_friends.EditFriendsInputBoundary;
import use_case.edit_friends.EditFriendsInputData;
import entity.User;

public class EditFriendsController {
    final EditFriendsInputBoundary editFriendsCaseInteractor;

    public EditFriendsController(EditFriendsInputBoundary editFriendsCaseInteractor) {
        this.editFriendsCaseInteractor = editFriendsCaseInteractor;
    }

    public void execute(User user1, User user2, User user3){
        EditFriendsInputData editFriendsInputData = new EditFriendsInputData(user1, user2, user3);

        editFriendsCaseInteractor.execute(editFriendsInputData);
    }
}
