package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import entity.Message;

import java.time.LocalDateTime;

public class SendMessageController {
    final SendMessageInputBoundary sendMessageUseCaseInteractor;

    public SendMessageController(SendMessageInputBoundary sendMessageCaseInteractor) {
        this.sendMessageUseCaseInteractor = sendMessageUseCaseInteractor;
    }

    public void execute(String message, String sender, String receiver){
        SendMessageInputData sendMessageInputData = new Message(chatName, sender, receiver, message, LocalDateTime.now());
        sendMessageUseCaseInteractor.sendMessageInputData(message);

        sendMessageUseCaseInteractor.execute(sendMessageInputData);
    }
}
