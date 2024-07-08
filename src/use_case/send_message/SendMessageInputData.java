package use_case.send_message;

import entity.User;
import entity.Message;
import entity.Chat;

import java.sql.Time;

public class SendMessageInputData {
    private Message message;
    private Time date;
    //private Boolean sent_status;
    private User sender;
    private User receiver;

    public SendMessageInputData(Message message, Time date, User sender, User receiver) {
        this.message = message;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    Message getMessage() {return message;}
    Time getDate() {return date;}
    User getSender() {return sender;}
    User getReceiver() {return receiver;}



}
