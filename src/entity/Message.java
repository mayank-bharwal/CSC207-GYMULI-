package entity;

public class Message {
     private User sender;
     private User receiver;
     private String message;
     private Object media;


      public Message(User sender, User receiver, String message, Object media) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.media = media;
    }

    public User getSender() {
         return sender;
    }
    public User getReceiver() {
         return receiver;
    }
    public String getMessage() {
         return message;
    }
    public Object getMedia() {
          return media;
    }
    public void setSender(User sender) {
         this.sender = sender;
    }
    public void setReceiver(User receiver) {
         this.receiver = receiver;
    }
    public void setMessage(String message) {
          this.message = message;
    }
    public void setMedia(Object media) {
          this.media = media;
    }
}
