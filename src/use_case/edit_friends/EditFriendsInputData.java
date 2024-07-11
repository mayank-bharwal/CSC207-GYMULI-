package use_case.edit_friends;

import entity.User;

public class EditFriendsInputData {
    final private User currentUser;
    final private User addition;
    final private User deletion;
    public EditFriendsInputData(User currenUser, User addition, User deletion) {
        this.currentUser = currenUser;
        this.addition = addition;
        this.deletion = deletion;
    }

    public User getAddition() {
        return addition;
    }
    public User getDeletion() {
        return deletion;
    }
    public User getCurrenUser() {
        return currentUser;
    }
}
