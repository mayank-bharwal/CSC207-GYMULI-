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
    private List<String> chats = new ArrayList<String>();
    private LocalDateTime dateCreated;

    /**
     *Requires: password is valid
     * @param username
     * @param password
     * @param bio
     * @param age
     * @param programOfStudy
     * @param interests
     * @param friends
     * @param chats
     * @param dateCreated
     */


    CommonUser(String username, String password, String bio, Integer age,String programOfStudy,
               List<String> interests, List<String> friends, List<String> chats, LocalDateTime dateCreated) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.age = age;
        this.interests = interests;
        this.friends = friends;
        this.chats = chats;
        this.dateCreated = dateCreated;

    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getBio() {
        return bio;
    }
    @Override
    public String getProgramOfStudy() {
        return programOfStudy;
    }
    @Override
    public Integer getAge() {
        return age;
    }
    @Override
    public List<String> getInterests() {
        return interests;
    }
    @Override
    public List<String> getFriends() {
        return friends;
    }
    @Override
    public List<String> getChats() { return chats; }
    @Override
    public LocalDateTime getDateCreated() { return dateCreated; }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }
    @Override
    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy = programOfStudy;
    }
    @Override
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
    @Override
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public void setChats(List<String> chats) {

    }

    @Override
    public void getChats(List<String> chats) { this.chats = chats; }
    @Override
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }

}
