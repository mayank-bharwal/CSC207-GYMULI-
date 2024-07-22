package entity;
// import jdk.vm.ci.meta.Local;

import java.time.Instant;
import java.time.LocalDateTime;

public class Message {

    private String chatName;
    private String sender;
    private String receiver;
    private String message;
    final private LocalDateTime time;

    /**
     *
     * @param chatName
     * @param sender
     * @param receiver
     * @param message
     * @param time
     */


      public Message(String chatName, String sender, String receiver, String message, LocalDateTime time) {
          this.chatName = chatName;
          this.sender = sender;
          this.receiver = receiver;
          this.message = message;
          this.time = LocalDateTime.now();
    }
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
