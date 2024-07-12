package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private ArrayList<User> users;
    private String bio;
    private String image;
    private LocalDateTime dateCreated;

    public Group(String groupName, ArrayList<User> users, String bio, String image, LocalDateTime dateCreated) {
        this.groupName = groupName;
        this.users = users;
        this.bio = bio;
        this.image = image;
        this.dateCreated = dateCreated;
    }
    public String getGroupName() { return groupName; }
    public ArrayList<User> getUsers() { return users; }
    public String getBio() { return bio; }
    public String getImage() { return image; }
    public LocalDateTime getDateCreated() { return dateCreated; }

    public void setGroupName(String groupName) { this.groupName = groupName; }
    public void setUsers(ArrayList<User> users) { this.users = users; }
    public void setBio(String bio) { this.bio = bio; }
    public void setImage(String image) { this.image = image; }
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }
}
