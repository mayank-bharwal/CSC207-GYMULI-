package use_case.add_friends;

/**
 * Interactor class that handles the process of adding a friend.
 * It implements the {@link AddFriendsInputBoundary} interface and coordinates
 * the interaction between the input data, user data access, and output boundaries.
 */
public class AddFriendsInteractor implements AddFriendsInputBoundary{

    final AddFriendsOutputBoundary outputBoundary;
    final AddFriendsUserDataAccessObject userDataAccessObject;

    /**
     * Constructs an {@code AddFriendsInteractor} with the specified output boundary
     * and user data access object.
     *
     * @param outputBoundary       The boundary to handle the output of this interactor.
     * @param userDataAccessObject The data access object to interact with user data.
     */
    public AddFriendsInteractor(AddFriendsOutputBoundary outputBoundary,
                                AddFriendsUserDataAccessObject userDataAccessObject) {

        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;

    }


    /**
     * Executes the process of adding a friend based on the provided input data.
     * <p>
     * This method checks if the friend exists and if they are already a friend of
     * the current user. If the friend does not exist or is already added, it sets
     * a failure view through the output boundary. Otherwise, it adds the friend
     * and sets a success view.
     *
     * @param addFriendsInputData The input data containing the current user and friend information.
     */
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
