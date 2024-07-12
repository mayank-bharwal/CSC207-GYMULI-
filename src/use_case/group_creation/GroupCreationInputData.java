package use_case.group_creation;

import entity.Group;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupCreationInputData {
    final private String groupName;
    final private ArrayList<User> users;
    final private String bio;
    final private String image;
    final private LocalDateTime dateCreated;

    public GroupCreationInputData(String groupName, List<User> users, String bio, String image,
                                  LocalDateTime dateCreated) {
        this.groupName = groupName;
        this.users = new ArrayList<>(users);
        this.bio = bio;
        this.image = image;
        this.dateCreated = LocalDateTime.now();
    }
    public String getGroupName() {return groupName;}
    public ArrayList<User> getUsers() {return users;}
    public String getBio() {return bio;}
    public String getImage() {return image;}
    public LocalDateTime getDateCreated() {return dateCreated;}
}
