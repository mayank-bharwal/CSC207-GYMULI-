package use_case.retrieve_message;

public class RetrieveMessageInputData {
    final private String sender;
    final private String reciever;
    final private String message;

    public RetrieveMessageInputData(String sender, String reciever, String message) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    String getSender() {
        return sender;
    }
    String getReciever() {
        return reciever;
    }
    String getMessage() {
        return message;
    }
}
