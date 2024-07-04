package use_case.account_creation;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class AccountCreationInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String bio;
    final private String programOfStudy;
    final private String image;
    final private List<String> interests;
    final private List<User> friends;


    public AccountCreationInputData(String username, String password, String repeatPassword, String bio, String programOfStudy, String image, List<String> interests, ArrayList<User> friends) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.image = image;
        this.interests = interests;
        this.friends = friends;
    }
    // need to decide access modifiers for each of the following
    //mainly during implementation of other programs
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getRepeatPassword() {
        return repeatPassword;
    }
    String getBio() {
        return bio;
    }
    String getProgramOfStudy() {
        return programOfStudy;
    }
    String getImage() {
        return image;
    }
    List<String> getInterests() {
        return interests;
    }
    List<User> getFriends() {
        return friends;
    }
}
