package use_case.group_creation;

public interface GroupCreationInputBoundary {
    void execute(GroupCreationInputBoundary inputData);
    void change(GroupCreationInputData inputData);
}
