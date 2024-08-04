package use_case.remove_friends;

public interface RemoveFriendsOutputBoundary {

    void setPassView(RemoveFriendsOutputData user);

    void setFailView(String error);
}
