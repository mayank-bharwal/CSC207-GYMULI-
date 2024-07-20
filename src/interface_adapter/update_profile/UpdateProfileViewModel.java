package interface_adapter.update_profile;

import interface_adapter.ViewModel;

import java.util.List;

public class UpdateProfileViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Update Profile";
    public static final String CURRENT_USERNAME_LABEL = "Enter current username";
    public static final String CURRENT_PASSWORD_LABEL = "Enter current password";
    public static final String USERNAME_LABEL = " Update Username";
    public static final String PASSWORD_LABEL = " Update Password";
    public static final String AGE_LABEL = " Update Age";
    public static final String BIO_LABEL = " Update Bio";
    public static final String PROGRAM_OF_STUDY_LABEL = "Program of Study";
    public static final String INTEREST1_LABEL = "Interest 1";
    public static final String INTEREST2_LABEL = "Interest 2";
    public static final String INTEREST3_LABEL = "Interest 3";

    public static final String UPDATE_BUTTON_LABEL = "Update";
    public static final String CANCEL_UPDATE_BUTTON_LABEL = "Cancel";

    private String currentUsername;
    private String currentPassword;
    private String username;
    private String password;
    private Integer age;
    private String bio;
    private String programOfStudy;
    private List<String> interests;
    private String error;

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
        firePropertyChanged("currentUsername", username, currentUsername);
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        firePropertyChanged("currentPassword", password, currentPassword);
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy = programOfStudy;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
