package interface_adapter.update_profile;

import interface_adapter.ViewModel;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.*;

public class UpdateProfileViewModel extends ViewModel {
    UpdateProfileState updateProfileState = new UpdateProfileState();
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

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public UpdateProfileState getState() {
        return updateProfileState;
    }

    public void setUpdateProfileState(UpdateProfileState updateProfileState) {
        this.updateProfileState = updateProfileState;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.updateProfileState);
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    //the following is for testing

    private String currentUsername;
    private String currentPassword;
    private String username;
    private String password;
    private Integer age;
    private String bio;
    private String programOfStudy;
    private List<String> interests;
    private String error;

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        firePropertyChanged("username", oldUsername, username);
    }

    public void setBio(String bio) {
        String oldBio = this.bio;
        this.bio = bio;
        firePropertyChanged("bio", oldBio, bio);
    }

    public void setAge(Integer age) {
        Integer oldAge = this.age;
        this.age = age;
        firePropertyChanged("age", oldAge, age);
    }

    public void setProgramOfStudy(String programOfStudy) {
        String oldProgramOfStudy = this.programOfStudy;
        this.programOfStudy = programOfStudy;
        firePropertyChanged("programOfStudy", oldProgramOfStudy, programOfStudy);
    }

    public void setInterests(List<String> interests) {
        List<String> oldInterests = this.interests;
        this.interests = interests;
        firePropertyChanged("interests", oldInterests, interests);
    }

    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        firePropertyChanged("error", oldError, error);
    }
}
