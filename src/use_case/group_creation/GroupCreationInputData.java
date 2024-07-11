package use_case.group_creation;

import entity.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationInputData {
    final private String groupName;
    final private ArrayList<User> users;
    final private String bio;
    final private String image;

    public GroupCreationInputData(String groupName, List<User> users, String bio, String image) {
        this.groupName = groupName;
        this.users = new ArrayList<>(users);
        this.bio = bio;
        this.image = image;
    }
    public String getGroupName() {return groupName;}
    public ArrayList<User> getUsers() {return users;}
    public String getBio() {return bio;}
    public String getImage() {return image;}
}
