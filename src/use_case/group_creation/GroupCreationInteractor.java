package use_case.group_creation;

mport use_case.group_creation.GroupCreationUserDataAccessInterface;
import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;
import java.util.Collections;

public class GroupCreationInteractor implements GroupCreationInputBoundary {
    final GroupCreationUserDataAccessInterface groupCreationUserDataAccessObject;
    final GroupCreationOutputBoundary groupPresenter;
    final GroupFactory groupFactory;

    public GroupCreationInteractor(GroupCreationUserDataAccessInterface groupCreationUserDataAccessObject,
                                   GroupCreationOutputBoundary groupPresenter, GroupFactory groupFactory) {
        this.groupCreationUserDataAccessObject = groupCreationUserDataAccessObject;
        this.groupPresenter = groupPresenter;
        this.groupFactory = groupFactory;
    }

    @Override
    public void execute(GroupCreationInputData inputData) {
        if (GroupCreationUserDataAccessObject.GroupExists(inputData.getGroupName())) {
            groupPresenter.setFailView("Group already exists");
        } else if (!inputData.getPassword().equals(inputData.getRepeatPassword())) {
            accountPresenter.setFailView("Passwords are not equal");
        } else if (inputData.getUsername().equals("")
                || inputData.getPassword().equals("")
                || inputData.getRepeatPassword().equals("")
                || inputData.getProgramOfStudy().equals("")
                || inputData.getInterests().isEmpty()) {
            accountPresenter.setFailView("Please fill in all fields");
        } else {
            LocalDateTime date = LocalDateTime.now();
            User user = UserFactory.createUser(inputData.getUsername(), inputData.getPassword(),
                    "", inputData.getProgramOfStudy(), "", inputData.getInterests(), Collections.emptyList(),
                    date);
        }
    }
}
