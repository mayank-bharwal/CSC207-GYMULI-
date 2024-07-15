package use_case.update_profile;
//input data
import java.util.ArrayList;
import java.util.List;

public class UpdateProfileInputData {
    private final String username;
    private final String password;
    private final String bio;
    private final String programOfStudy;
    private final Integer age;
    private List<String> interests = new ArrayList<String>();

    public UpdateProfileInputData(String username, String password, String bio, String programOfStudy, Integer age,
                                  List<String> interests) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.programOfStudy = programOfStudy;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public List<String> getInterests() {
        return interests;
    }

}
