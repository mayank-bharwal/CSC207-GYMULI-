package entity;

import java.util.ArrayList;

public class ChatFactory {
    public Chat createChat(String chatName, ArrayList<User> users, Integer noOfMembers, ArrayList<Message> allmessages) {
        return new Chat(chatName, users, noOfMembers, allmessages);
    }
}
