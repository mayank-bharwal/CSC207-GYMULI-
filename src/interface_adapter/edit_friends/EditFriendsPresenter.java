package interface_adapter.edit_friends;

import use_case.edit_friends.EditFriendsOutputBoundary;
import use_case.edit_friends.EditFriendsOutputData;

public class EditFriendsPresenter implements EditFriendsOutputBoundary {

    private final EditFriendsViewModel editFriendsViewModel;

    public EditFriendsPresenter(EditFriendsViewModel editFriendsViewModel) {
        this.editFriendsViewModel = editFriendsViewModel;
    }

    @Override
    public void setPassView(EditFriendsOutputData outputData) {

    }

    @Override
    public void setFailView(String Error) {

    }



}
