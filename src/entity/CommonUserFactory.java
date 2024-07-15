package entity;
import java.time.LocalDateTime;
import java.util.List;

public class CommonUserFactory implements UserFactory {

    @Override
    public User createUser(String name, String password, String bio, Integer age, String program, List<String> interest
            , List<String> friends, LocalDateTime date) {
        return new CommonUser(name, password, bio, age, program, interest, friends, date);
    }
}
