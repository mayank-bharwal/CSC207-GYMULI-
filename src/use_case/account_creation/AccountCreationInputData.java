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
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getRepeatPassword() {
        return repeatPassword;
    }
    String getProgramOfStudy() {
        return programOfStudy;
    }
    List<String> getInterests() {
        return interests;
    }
    String getBio() {return bio; }
    LocalDateTime getTime() {return time;}
    Integer getAge() {return age;}
}
