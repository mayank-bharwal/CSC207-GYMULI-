package use_case.update_profile;
//output data
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

/**
 * Represents the output data for the Update Profile use case.
 * This includes the updated user information such as username, password, bio,
 * program of study, age, and interests, as well as the status of the use case.
 */

public class UpdateProfileOutputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String programOfStudy;
    private final Integer age;
    private final List<String> interests;
    private boolean useCaseFailed;

    /**
     * Constructs a new UpdateProfileOutputData object with the provided user details and use case status.
     *
     * @param username       The updated username of the user.
     * @param password       The updated password of the user.
     * @param bio            The updated bio of the user.
     * @param programOfStudy The updated program of study of the user.
     * @param age            The updated age of the user.
     * @param interests      The updated list of interests of the user.
     * @param useCaseFailed  Indicates if the update profile use case failed.
     */

    public UpdateProfileOutputData(String username, String password, String bio, String programOfStudy,
                                   Integer age, List<String> interests, boolean useCaseFailed) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.age = age;
        this.interests = interests;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public String getProgramOfStudy() {
        return programOfStudy;
    }

    public List<String> getInterests() {
        return interests;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}
