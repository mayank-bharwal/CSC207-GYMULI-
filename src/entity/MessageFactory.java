package entity;

import java.time.LocalDateTime;

public class MessageFactory {
    /**
     *
     * @param chatName
     * @param sender
     * @param reciever
     * @param message
     * @param time
     * @return
     */
    public Message createMessage(String chatName, String sender, String reciever, String message, LocalDateTime time) {
        return new Message(chatName, sender, reciever, message, time);
    }
}
