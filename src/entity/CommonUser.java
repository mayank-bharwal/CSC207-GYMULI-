package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * The CommonUser class represents a user with a username, password, bio, program of study, age, interests, friends, chats, and the date the account was created.
 */
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
     * Constructs a new CommonUser with the specified username, password, bio, age, program of study, interests, friends, chats, and date created.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param bio            the bio of the user
     * @param age            the age of the user
     * @param programOfStudy the program of study of the user
     * @param interests      the list of interests of the user
     * @param friends        the list of friends of the user
     * @param chats          the list of chats of the user
     * @param dateCreated    the date the user account was created
     */
    CommonUser(String username, String password, String bio, Integer age, String programOfStudy,
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


    /**
     * Gets the Username of the user
     *
     * @return the username of the user
     */
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
        this.chats = new ArrayList<>(chats);
    }

    @Override
    public void getChats(List<String> chats) { this.chats = chats; }
    @Override
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }

}
