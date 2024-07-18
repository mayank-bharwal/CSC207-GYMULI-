package use_case.send_message;

import entity.User;
import entity.Message;
import entity.Chat;

import java.sql.Time;
import java.time.LocalDateTime;

public class SendMessageInputData {
    private String ChatName;
    private String message;
    final private String sender;
    final private String receiver;
    //final private Time date;
    //private Boolean sent_status;

    public SendMessageInputData(String ChatName, String message, LocalDateTime now, String sender, String receiver) {
        this.message = message;
        //this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.ChatName = ChatName;

    }

    public SendMessageInputData(String chatName, String message, LocalDateTime now, String sender, String receiver, String sender1, String receiver1) {
        this.sender = sender1;
        this.receiver = receiver1;
    }

    String getMessage() {return message;}
    //Time getDate() {return date;}
    String getSender() {return sender;}
    String getReceiver() {return receiver;}
    String getChatName() {return ChatName;}



}
