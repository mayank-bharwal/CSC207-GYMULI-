package use_case.account_creation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data class representing the input data required for account creation.
 * It includes user details such as username, password, program of study, interests, bio, and more.
 */
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
     * Constructs an {@code AccountCreationInputData} object with the specified user details.
     *
     * @param username        The username for the new account.
     * @param password        The password for the new account.
     * @param repeatPassword  The repeated password for verification.
     * @param programOfStudy  The user's program of study.
     * @param interests       A list of the user's interests.
     * @param bio             A brief biography of the user.
     * @param time            The time when the account is being created.
     * @param age             The user's age.
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


    // need to decide access modifiers for each of the following
    //mainly during implementation of other programs
    //I (Liban) made it public for now so I could finish my tests

    /**
     * Returns the username for the new account.
     *
     * @return The username.
     */
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
