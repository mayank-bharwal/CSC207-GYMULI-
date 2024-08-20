package use_case.make_chat;

import java.time.LocalDateTime;

/**
 * This class represents the input data required for creating a new chat session.
 * It contains the necessary information such as the chat name, the users involved,
 * and the time the chat was created.
 */
public class MakeChatInputData {

    private final String User_1;
    private final String User_2;
    private final String chatName;
    private final LocalDateTime time;

    /**
     * Constructs a new instance of {@code MakeChatInputData} with the specified chat name,
     * users, and time of creation.
     *
     * @param chatName the name of the chat.
     * @param user1    the username or ID of the first user involved in the chat.
     * @param user2    the username or ID of the second user involved in the chat.
     * @param time     the time at which the chat is created.
     */
    public MakeChatInputData(String chatName, String user1, String user2, LocalDateTime time) {
        this.chatName = chatName;
        this.User_1 = user1;
        this.User_2 = user2;
        this.time = time;
    }

    /**
     * Returns the name of the chat.
     *
     * @return the chat name.
     */
    public String getChatName() {
        return chatName;
    }

    /**
     * Returns the username or ID of the first user involved in the chat.
     *
     * @return the first user's username or ID.
     */
    public String getUser_1() {
        return User_1;
    }

    /**
     * Returns the username or ID of the second user involved in the chat.
     *
     * @return the second user's username or ID.
     */
    public String getUser_2() {
        return User_2;
    }

    /**
     * Returns the time at which the chat was created.
     *
     * @return the creation time of the chat.
     */
    public LocalDateTime getTime() {
        return time;
    }
}