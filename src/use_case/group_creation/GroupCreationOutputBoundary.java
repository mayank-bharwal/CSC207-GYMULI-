package use_case.group_creation;

public interface GroupCreationOutputBoundary {
    void setPassView(GroupCreationOutputData Group);

    void setFailView(String error);
}
