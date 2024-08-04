package use_case.add_friends;

public interface AddFriendsOutputBoundary {

    void setPassView(AddFriendsOutputData outputData);

    void setFailView(String msg);


}
