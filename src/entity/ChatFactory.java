package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The ChatFactory class is responsible for creating instances of the Chat class.
 */
public class ChatFactory {
    /**
     * Creates a new Chat with the specified name, users, number of members, messages, and timestamp.
     *
     * @param chatName    the name of the chat
     * @param users       the list of users in the chat
     * @param noOfMembers the number of members in the chat
     * @param allmessages the list of messages in the chat
     * @param time        the time the chat was created
     * @return a new Chat instance
     */
    public Chat createChat(String chatName, ArrayList<String> users, Integer noOfMembers, ArrayList<Message> allmessages, LocalDateTime time) {
        return new Chat(chatName, users, noOfMembers, allmessages, time);
    }
}
