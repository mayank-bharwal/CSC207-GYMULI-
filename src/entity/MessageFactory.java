package entity;

import java.time.LocalDateTime;
/**
 * The Message class is responsible for creating instances of the Message class.
 */
public class MessageFactory {
    /**
     * @param chatName      Name of chatroom
     * @param sender        user who is sending message
     * @param reciever     user who is receiving message
     * @param message       content of the message
     * @param time          timestamp message is sent
     * @return a new Message instance
     */
    public Message createMessage(String chatName, String sender, String reciever, String message, LocalDateTime time) {
        return new Message(chatName, sender, reciever, message, time);
    }
}
