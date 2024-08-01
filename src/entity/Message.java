package entity;
// import jdk.vm.ci.meta.Local;

import java.time.Instant;
import java.time.LocalDateTime;
/**
 * The Message class represents a message with a chatroom, sender, receiver, a specified content, and a timestamp.
 */
public class Message {

    private String chatName;
    private String sender;
    private String receiver;
    private String message;
    final private LocalDateTime time;


    /**
     * constructs a new message within the specified chatroom, sender, receiver, message content, and timestamp
     * @param chatName      Name of chatroom
     * @param sender        user who is sending message
     * @param receiver      user who is receiving message
     * @param message       content of the message
     * @param time          timestamp message is sent
     */
      public Message(String chatName, String sender, String receiver, String message, LocalDateTime time) {
          this.chatName = chatName;
          this.sender = sender;
          this.receiver = receiver;
          this.message = message;
          this.time = LocalDateTime.now();
    }

    /**
     * Gets the name of the chat.
     *
     * @return the name of the chat
     */
    public String getChatName() {
          return chatName;
    }
    public String getSender() {
          return sender;
    }
    public String getReceiver() {
         return receiver;
    }
    public String getMessage() {
         return message;
    }
    public LocalDateTime getTime() { return time;}

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    public void setSender(String sender) {
         this.sender = sender;
    }
    public void setReceiver(String receiver) {
         this.receiver = receiver;
    }
    public void setMessage(String message) {
          this.message = message;
    }

}
