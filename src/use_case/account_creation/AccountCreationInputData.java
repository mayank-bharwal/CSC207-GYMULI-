package use_case.account_creation;

import java.util.ArrayList;
import java.util.List;

public class AccountCreationInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String programOfStudy;
    final private List<String> interests;


    public AccountCreationInputData(String username, String password, String repeatPassword, String programOfStudy, List<String> interests) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.programOfStudy = programOfStudy;
        this.interests = interests;
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
}
