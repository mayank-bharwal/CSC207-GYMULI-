package entity;

public class Message {
     private User sender;
     private User receiver;
     private String message;


      public Message(User sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
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
    public void setSender(User sender) {
         this.sender = sender;
    }
    public void setReceiver(User receiver) {
         this.receiver = receiver;
    }
    public void setMessage(String message) {
          this.message = message;
    }
}
