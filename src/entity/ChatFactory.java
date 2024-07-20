package entity;

import java.util.ArrayList;

public class ChatFactory {
    /**
     *
     * @param chatName
     * @param users
     * @param noOfMembers
     * @param allmessages
     * @return
     */
    public Chat createChat(String chatName, ArrayList<String> users, Integer noOfMembers, ArrayList<Message> allmessages) {
        return new Chat(chatName, users, noOfMembers, allmessages);
    }
}
