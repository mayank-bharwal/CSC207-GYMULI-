package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User {
    private String username;
    private String password;
    private String bio;
    private String programOfStudy;
    private Integer age;
    private List<String> interests = new ArrayList<String>();
    private List<String> friends = new ArrayList<String>();
    private LocalDateTime dateCreated;


    CommonUser(String username, String password, String bio, String programOfStudy,Integer age,
               List<String> interests, List<String> friends, LocalDateTime dateCreated) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.age = age;
        this.interests = interests;
        this.friends = friends;
        this.dateCreated = dateCreated;

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

    public Integer getAge() {
        return age;
    }

    public List<String> getInterests() {
        return interests;
    }

    public List<String> getFriends() {
        return friends;
    }

    public LocalDateTime getDateCreated() { return dateCreated; }


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
    }\

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }

}
