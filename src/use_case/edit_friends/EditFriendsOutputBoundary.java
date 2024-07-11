package use_case.edit_friends;

public interface EditFriendsOutputBoundary {
    void setPassView(EditFriendsOutputData outputData);
    void setFailView(String Error);
}
