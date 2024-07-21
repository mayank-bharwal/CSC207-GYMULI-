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

    /**
     *
     * @param ChatName
     * @param message
     * @param now
     * @param sender
     * @param receiver
     */
    public SendMessageInputData(String ChatName, String message, LocalDateTime now, String sender, String receiver) {

        this.message = message;
        //this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.ChatName = ChatName;

    }

    String getMessage() {return message;}
    //Time getDate() {return date;}
    String getSender() {return sender;}
    String getReceiver() {return receiver;}
    String getChatName() {return ChatName;}

    /**
     * @returns the requested information
     */

}
