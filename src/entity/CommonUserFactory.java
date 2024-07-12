package entity;
import java.time.LocalDateTime;
import java.util.List;

public class CommonUserFactory implements UserFactory {

    public User create(String name, String password, String bio, String program, Integer age, List<String> interest
            , List<String> friends, LocalDateTime date) {
        return new CommonUser(name, password, bio, program, age, interest, friends, date);
    }
}
