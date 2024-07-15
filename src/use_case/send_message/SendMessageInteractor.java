package use_case.send_message;

import entity.Chat;
import entity.Message;
import entity.User;

import java.time.LocalDateTime;

public class SendMessageInteractor  {
    final SendMessageUserDataAccessInterface userDataAccessObject;
    final SendMessageOutputBoundary userPresenter;
    Message message;

    public SendMessageInteractor(SendMessageUserDataAccessInterface signupDataAccessInterface,
                            SendMessageOutputBoundary signupOutputBoundary,
                            Message message) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.message = message;
    }

    @Override
    public void execute(SendMessageInputData SendMessageInputData) {
        Message message = SendMessageInputData.getMessage();
        User receiver = message.getReceiver();
        User sender = message.getSender();
        LocalDateTime now = LocalDateTime.now();
        Message message = Message.create(SendMessageInputData.getMessage(), SendMessageInputData.getSender(), SendMessageInputData.getReceiver, now);
        SendMessageUserDataAccessInterface.save(message);
        SendMessageOutputData sendmessageOutputData = new SendMessageOutputData(Message.getMessage(), now.toString(), false);
        userPresenter.prepareSuccessView(sendmessageOutputData);
    }

}
