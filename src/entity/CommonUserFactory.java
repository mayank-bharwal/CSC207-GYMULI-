package entity;
import java.time.LocalDateTime;
import java.util.List;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid
     * @param name
     * @param password
     * @param bio
     * @param age
     * @param program
     * @param interest
     * @param friends
     * @param date
     * @return
     */

    @Override
    public User createUser(String name, String password, String bio, Integer age, String program, List<String> interest
            , List<String> friends, LocalDateTime date) {
        return new CommonUser(name, password, bio, age, program, interest, friends, date);
    }
}
