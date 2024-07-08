package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String bio;
    private String programOfStudy;
    private String profilePicture;
    private List<String> interests = new ArrayList<String>();
    private List<String> friends = new ArrayList<String>();


    User(String username, String password, String bio, String programOfStudy, String profilePicture,
         List<String> interests, List<String> friends) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.profilePicture = profilePicture;
        this.interests = interests;
        this.friends = friends;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public List<String> getInterests() {
        return interests;
    }

    public List<String> getFriends() {
        return friends;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy = programOfStudy;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

}
