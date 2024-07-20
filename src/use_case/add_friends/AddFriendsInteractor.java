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
            outputBoundary.sstFailView("User does not exist");
        }

    }
}
