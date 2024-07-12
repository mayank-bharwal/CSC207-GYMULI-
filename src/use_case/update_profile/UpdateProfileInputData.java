package use_case.update_profile;

import java.util.List;

public class UpdateProfileInputData {
    private final String username;
    private final String password;
    private final String bio;
    private final String programOfStudy;
    private final String profilePicture;
    private final List<String> interests;

    public UpdateProfileInputData(String username, String password, String bio, String programOfStudy,
                                  String profilePicture, List<String> interests, List<String> friends) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.profilePicture = profilePicture;
        this.interests = interests;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public List<String> getInterests() {
        return interests;
    }

}
