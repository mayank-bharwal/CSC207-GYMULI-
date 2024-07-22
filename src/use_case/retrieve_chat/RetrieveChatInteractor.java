package use_case.retrieve_chat;

import entity.Chat;
import java.util.ArrayList;

public class RetrieveChatInteractor implements RetrieveChatInputBoundary {
    private final RetrieveChatOutputBoundary presenter;
    private final RetrieveChatUserDataAccessInterface chatDataAccessObject;

    public RetrieveChatInteractor(RetrieveChatOutputBoundary presenter, RetrieveChatUserDataAccessInterface chatDataAccessObject) {
        this.presenter = presenter;
        this.chatDataAccessObject = chatDataAccessObject;
    }

    @Override
    public void retrieveChat(RetrieveChatInputData inputData) {
        Chat chat = chatDataAccessObject.getChat(inputData.getChatName());
        RetrieveChatOutputData outputData = new RetrieveChatOutputData(
                chat.getChatName(),
                new ArrayList<>(chat.getUsers()),
                chat.getNoOfMembers(),
                new ArrayList<>(chat.getAllmessages()),
                chat.getTime(),
                false
        );
        presenter.presentChat(outputData);
    }
}


