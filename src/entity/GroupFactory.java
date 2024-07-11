package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupFactory {
    public Group createGroup(String groupName, ArrayList<User> users, String bio, String image,
                             LocalDateTime dateCreated){
        return new Group(groupName, users, bio, image, dateCreated);
    }
}
