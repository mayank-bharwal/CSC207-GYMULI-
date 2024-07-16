package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface GroupFactory {
    Group createGroup(String groupName, ArrayList<User> users, String bio, String image,
                      LocalDateTime dateCreated);
    }
