package interface_adapter.account_creation;

import java.util.ArrayList;
import java.util.List;

public class SignUpState {

    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String programOfStudy = "";
    private String programOfStudyError = null;
    private String bio = "";
    private String bioError = null;
    private Integer age;
    private String ageError = null;
    private String interest1 = "";
    private String interest2 = "";
    private String interest3 = "";

    public SignUpState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy = programOfStudy;
    }

    public String getProgramOfStudyError() {
        return programOfStudyError;
    }

    public void setProgramOfStudyError(String programOfStudyError) {
        this.programOfStudyError = programOfStudyError;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBioError() {
        return bioError;
    }

    public void setBioError(String bioError) {
        this.bioError = bioError;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgeError() {
        return ageError;
    }

    public void setAgeError(String ageError) {
        this.ageError = ageError;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public List<String> getInterests() {
        List<String> interests = new ArrayList<>();
        interests.add(interest1);
        interests.add(interest2);
        interests.add(interest3);
        return interests;
    }

}
