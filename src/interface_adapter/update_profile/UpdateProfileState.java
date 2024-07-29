package interface_adapter.update_profile;

public class UpdateProfileState {

    private String currentUsername = "";
    private String currentPassword = "";
    private String username = "";
    private String password = "";
    private String bio = "";
    private Integer age = null;
    private String programOfStudy;
    private String interest1 = "";
    private String interest2 = "";
    private String interest3 = "";
    private String Error = null;

    public UpdateProfileState(UpdateProfileState copy){

        currentUsername = copy.currentUsername;
        currentPassword = copy.password;
        username = copy.username;
        password = copy.password;
        bio = copy.bio;
        programOfStudy = copy.programOfStudy;
        age = copy.age;
        interest1 = copy.interest1;
        interest2 = copy.interest2;
        interest3 = copy.interest3;
        Error = copy.Error;

    }

    public UpdateProfileState() {};

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy = programOfStudy;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }
}

