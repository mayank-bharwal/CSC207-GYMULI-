package entity;

import java.time.LocalDateTime;

public class MessageFactory {
    public Message createMessage(String chatName, String sender, String reciever, String message, LocalDateTime time) {
        return new Message(chatName, sender, reciever, message, time);
    }
}
