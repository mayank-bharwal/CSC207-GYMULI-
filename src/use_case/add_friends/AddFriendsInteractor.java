package use_case.add_friends;

public class AddFriendsInteractor implements AddFriendsInputBoundary{

    final AddFriendsOutputBoundary outputBoundary;
    final AddFriendsUserDataAccessObject userDataAccessObject;

    public AddFriendsInteractor(AddFriendsOutputBoundary outputBoundary,
                                AddFriendsUserDataAccessObject userDataAccessObject) {

        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;

    }



    @Override
    public void execute(AddFriendsInputData addFriendsInputData) {

        String currentUser = addFriendsInputData.getCurrentUser();
        String friend  = addFriendsInputData.getFriend();

        if (!userDataAccessObject.userExists(friend)) {
            outputBoundary.setFailView("User does not exist");
        } else if(userDataAccessObject.isFriend(currentUser,friend)) {
            outputBoundary.setFailView("Friend Already Added.");
        }
        else {

            userDataAccessObject.addFriend(currentUser, friend);
            AddFriendsOutputData addFriendsOutputData = new AddFriendsOutputData(currentUser,friend, false);
            outputBoundary.setPassView(addFriendsOutputData);

        }

    }
}
