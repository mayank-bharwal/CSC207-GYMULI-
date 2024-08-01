package entity;

import java.time.LocalDateTime;
import java.util.List;
/**
 * The UserFactory interface is responsible for creating instances of the User class.
 */
public interface UserFactory {
    /**
     * Creates a new User with:
     * @param name          the username of the user
     * @param password       the password of the user
     * @param bio            the bio of the user
     * @param age            the age of the user
     * @param program       the program of study of the user
     * @param interest      the list of interests of the user
     * @param friends        the list of friends of the user
     * @param chats          the list of chats of the user
     * @param date          the date the user account was created
     */
    User createUser(String name, String password, String bio, Integer age, String program, List<String> interest
            , List<String> friends, List<String> chats, LocalDateTime date);
}


