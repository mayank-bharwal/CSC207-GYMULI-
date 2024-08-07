package use_case.update_profile;
//output data
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

public class UpdateProfileOutputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String programOfStudy;
    private final Integer age;
    private final List<String> interests;
    private boolean useCaseFailed;

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
