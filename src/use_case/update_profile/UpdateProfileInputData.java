package use_case.update_profile;
//input data
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the input data required for updating a user's profile.
 * This includes current login credentials and the new profile data such as
 * username, password, bio, program of study, age, and interests.
 */

public class UpdateProfileInputData {

    private final String currentUsername;
    private final String currentPassword;
    private final String username;
    private final String password;
    private final String bio;
    private final String programOfStudy;
    private final Integer age;
    private List<String> interests = new ArrayList<String>();

    /**
     * Constructs a new UpdateProfileInputData with the specified information.
     *
     * @param currentUsername  The current username of the user.
     * @param currentPassword  The current password of the user.
     * @param username         The new username to update.
     * @param password         The new password to update.
     * @param bio              The new bio of the user.
     * @param programOfStudy   The updated program of study of the user.
     * @param age              The new age of the user.
     * @param interests        The list of updated interests of the user.
     */

    public UpdateProfileInputData(String currentUsername, String currentPassword, String username, String password, String bio, String programOfStudy, Integer age,
                                  List<String> interests) {
        this.currentUsername = currentUsername;
        this.currentPassword = currentPassword;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.age = age;
        this.interests = interests;
    }

    /** These are the getter and setter methods*/

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getInterests() {
        return interests;
    }

}
