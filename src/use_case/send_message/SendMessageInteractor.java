package use_case.send_message;

import entity.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class SendMessageInteractor implements SendMessageInputBoundary  {

    final private SendMessageUserDataAccessInterface userDataAccessObject;
    final private SendMessageOutputBoundary userPresenter;
    final private MessageFactory messageFactory;

    /**
     *
     * @param userDataAccessObject
     * @param userPresenter
     * @param messageFactory
     */

    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                            SendMessageOutputBoundary userPresenter,
                            MessageFactory messageFactory) {

        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.messageFactory = messageFactory;

    }

    /**
     * Interactor for sending a message
     * @param sendMessageInputData
     *
     */
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {

        String message = userDataAccessObject.filter(sendMessageInputData.getMessage());
        String chatName = sendMessageInputData.getChatName();
        String receiver = sendMessageInputData.getReceiver();
        String sender = sendMessageInputData.getSender();
        LocalDateTime now = LocalDateTime.now();


        if (Objects.equals(message, "")) {
            userPresenter.prepareFailView("Chat field is empty");
        } else {
            Message msg = messageFactory.createMessage(chatName, sender, receiver, message, now);
            userDataAccessObject.saveMessage(msg);
            SendMessageOutputData sendmessageOutputData = new SendMessageOutputData(message, now, false, chatName);
            userPresenter.prepareSuccessView(sendmessageOutputData);
        }

    }

}
