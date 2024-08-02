package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import entity.Message;
import entity.User;
import java.time.LocalDateTime;

/**
 * Controller for handling sending a message.
 */
public class SendMessageController {
    private final SendMessageInputBoundary sendMessageInteractor;

    /**
     * Constructor for sending a message
     * @param sendMessageInteractor    interactor for sending message
     */
    public SendMessageController(SendMessageInputBoundary sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    /**
     * execute the sending message process
     * @param chatName   name of chat
     * @param message    content of message
     * @param time       timestamp of when message was sent
     * @param sender     user sending message
     * @param receiver   the user receiving message
     */
    public void execute(String chatName, String message, LocalDateTime time, String sender, String receiver){
        SendMessageInputData inputData = new SendMessageInputData(chatName, message, LocalDateTime.now(), sender, receiver);
        sendMessageInteractor.execute(inputData);
    }
}
