package use_case.account_creation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountCreationInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String programOfStudy;
    final private List<String> interests;
    final private String bio;
    final private LocalDateTime time;
    final private Integer age;

    /**
     *
     * @param username - username of user
     * @param password - password
     * @param repeatPassword - verifies password
     * @param programOfStudy - program the user is studying
     * @param interests - user's interests
     * @param bio - user's bio
     * @param time - time of creation
     * @param age - age of user
     */


    public AccountCreationInputData(String username, String password, String repeatPassword, String programOfStudy, List<String> interests, String bio, LocalDateTime time, Integer age) {

        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.programOfStudy = programOfStudy;
        this.interests = interests;
        this.bio = bio;
        this.time = time;
        this.age = age;
    }

    /**
     *
     * @return the necessary fields input by user
     */
    // need to decide access modifiers for each of the following
    //mainly during implementation of other programs
    //I (Liban) made it public for now so I could finish my tests
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public String getProgramOfStudy() {
        return programOfStudy;
    }
    public List<String> getInterests() {
        return interests;
    }
    public String getBio() {return bio; }
    public LocalDateTime getTime() {return time;}
    public Integer getAge() {return age;}
}
