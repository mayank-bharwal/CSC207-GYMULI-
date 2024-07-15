package entity;

public class MessageFactory {
    public Message createMessage(String chatName, String sender, String reciever, String message) {
        return new Message(chatName, sender, reciever, message);
    }
}
