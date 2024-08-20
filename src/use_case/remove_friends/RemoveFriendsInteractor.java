package use_case.remove_friends;

import entity.User;
import use_case.delete_chat.DeleteChatOutputData;

/**
 * The interactor class responsible for handling the business logic of removing a friend.
 * It implements the {@link RemoveFriendsInputBoundary} interface and interacts with the data
 * access layer and the output boundary to process friend removal requests.
 */
public class RemoveFriendsInteractor implements RemoveFriendsInputBoundary {

    private final RemoveFriendsUserDataAccessInterface removeFriendsDAO;
    private final RemoveFriendsOutputBoundary friendsPresenter;

    /**
     * Constructs a new {@code RemoveFriendsInteractor} with the specified data access interface
     * and output boundary.
     *
     * @param removeFriendsDAO  the data access interface used to interact with user-related data
     *                          and perform friend removal operations.
     * @param friendsPresenter  the output boundary used to present the result of the friend removal process.
     */
    public RemoveFriendsInteractor(RemoveFriendsUserDataAccessInterface removeFriendsDAO,
                                   RemoveFriendsOutputBoundary friendsPresenter) {
        this.removeFriendsDAO = removeFriendsDAO;
        this.friendsPresenter = friendsPresenter;
    }

    /**
     * Handles the process of removing a friend based on the provided input data.
     * It verifies if the users are friends and performs the removal if they are,
     * or reports an error if they are not friends.
     *
     * @param inputData the data required to remove a friend, including the usernames of the users involved.
     */
    @Override
    public void removeFriend(RemoveFriendsInputData inputData) {
        // Check if the users are friends
        if (!removeFriendsDAO.isFriend(inputData.getUser1(), inputData.getUser2())) {
            // Present failure view if the users are not friends
            friendsPresenter.setFailView("You have no such friend");
        } else {
            // Remove the friend and present success view
            removeFriendsDAO.remove(inputData.getUser1(), inputData.getUser2());
            RemoveFriendsOutputData outputData = new RemoveFriendsOutputData(inputData.getUser1(), inputData.getUser2(), false);
            friendsPresenter.setPassView(outputData);
        }
    }
}