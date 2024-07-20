package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatFactory {
    /**
     *
     * @param chatName
     * @param users
     * @param noOfMembers
     * @param allmessages
     * @return
     */
    public Chat createChat(String chatName, ArrayList<String> users, Integer noOfMembers, ArrayList<Message> allmessages, LocalDateTime time) {
        return new Chat(chatName, users, noOfMembers, allmessages, time);
    }
}
