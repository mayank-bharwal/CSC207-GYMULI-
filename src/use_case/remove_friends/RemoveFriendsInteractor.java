package use_case.remove_friends;

import entity.User;
import use_case.delete_chat.DeleteChatOutputData;

public class RemoveFriendsInteractor implements RemoveFriendsInputBoundary{
    final RemoveFriendsUserDataAccessInterface removeFriendsDAO;
    final RemoveFriendsOutputBoundary friendsPresenter;


    public RemoveFriendsInteractor(RemoveFriendsUserDataAccessInterface removeFriendsDAO, RemoveFriendsOutputBoundary friendsPresenter) {
        this.removeFriendsDAO = removeFriendsDAO;
        this.friendsPresenter = friendsPresenter;
    }


    @Override
    public void removeFriend(RemoveFriendsInputData inputData) {
        if (!removeFriendsDAO.isFriend(inputData.getUser1(), inputData.getUser2())) {
            friendsPresenter.setFailView("You have no such friend");
        } else {

            removeFriendsDAO.remove(inputData.getUser1(), inputData.getUser2());
            RemoveFriendsOutputData outputData = new RemoveFriendsOutputData(inputData.getUser1(), inputData.getUser2(), false);
            friendsPresenter.setPassView(outputData);
        }

    }
}
